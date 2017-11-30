/**
 * @package org.nng.utils
 * @author Ashutosh Mishra [@github: nityanarayan44]
 * @desc Image MetaData Set and Get
 * Currently Focused for PNG Type Image.
 * 
 */
package org.nng.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.w3c.dom.NodeList;

//import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
//import com.gargoylesoftware.htmlunit.javascript.host.dom.NodeList;

public class Image {

	/* 
	 * +============================================+
	 * | Global Instances							|
	 * | Objects and Variables						|
	 * +============================================+
	 */
		//Nothing here
	
	/* 
	 * +============================================+
	 * | Constructors								|
	 * +============================================+
	 */
		public Image() {
			// Do nothing
		}
		
	/* 
	 * +============================================+
	 * | Private Methods							|
	 * | Basic Utilities methods					|
	 * +============================================+
	 */	
		private static String createOutputName(final File file) {
		    String name = file.getName();
//		    int dotIndex = name.lastIndexOf('.');
//		    String baseName = name.substring(0, dotIndex);
//		    String extension = name.substring(dotIndex);
		    //return baseName + "_copy" + extension;
		    return name;
		}
		private static void addTextEntry(final IIOMetadata metadata, final String key, final String value) throws IIOInvalidTreeException {
	        IIOMetadataNode textEntry = new IIOMetadataNode("TextEntry");
	        textEntry.setAttribute("keyword", key);
	        textEntry.setAttribute("value", value);

	        IIOMetadataNode text = new IIOMetadataNode("Text");
	        text.appendChild(textEntry);

	        IIOMetadataNode root = new IIOMetadataNode(IIOMetadataFormatImpl.standardMetadataFormatName);
	        root.appendChild(text);

	        metadata.mergeTree(IIOMetadataFormatImpl.standardMetadataFormatName, root);
	    }

	    private static String getTextEntry(final IIOMetadata metadata, final String key) {
	        IIOMetadataNode root = (IIOMetadataNode) metadata.getAsTree(IIOMetadataFormatImpl.standardMetadataFormatName);
	        NodeList entries = root.getElementsByTagName("TextEntry");

	        for (int i = 0; i < entries.getLength(); i++) {
	            IIOMetadataNode node = (IIOMetadataNode) entries.item(i);
	            if (node.getAttribute("keyword").equals(key)) {
	                return node.getAttribute("value");
	            }
	        }

	        return null;
	    }
	    
	/* 
	 * +============================================+
	 * | Public Methods								|
	 * | Using the implemented Utilities methods	|
	 * +============================================+
	 */
		// set the MetaData Value 
		public static void setMetaData(String metaKey, String metaValue, String filePath) throws IOException {
		        
			//=== [Set the image file for which we are going to set and get MetaData]
			 	File in = new File(filePath);
		        File out = new File(in.getParent(), createOutputName(in));
		    //=== [Logout the output image path]
		        //System.out.println("Output path: " + out.getAbsolutePath());
		
		    //=== [Doing the Operation]
			    try (ImageInputStream input = ImageIO.createImageInputStream(in); ImageOutputStream output = ImageIO.createImageOutputStream(out)) {
			        //= Getting readers for Image
			    	Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
			        ImageReader reader = readers.next(); // TODO: Validate that there are readers
			
			        //= Setting an image for this reader
			        reader.setInput(input);
			        IIOImage image = reader.readAll(0, null);
			
			        //= Adding MetaData to this Image
			        addTextEntry(image.getMetadata(), metaKey, metaValue);
			
			        //= Writing the imageFile back
			        ImageWriter writer = ImageIO.getImageWriter(reader); // TODO: Validate that there are writers
			        writer.setOutput(output);
			        writer.write(image);
			    }
			//=== [Last]
			    return;
		}

		// get the MetaData Value 
		public static String getMetaData(String metaKey, String filePath) throws IOException {
		        
			//=== [Set the image file for which we are going to set and get MetaData]
			 	File in = new File(filePath);
		        //File out = new File(in.getParent(), createOutputName(in));
		    //=== [Logout the output image path]
		        //System.out.println("Output path: " + out.getAbsolutePath());
		
		    //=== [Doing the Operation]
		        String value = "";
		        try (ImageInputStream input = ImageIO.createImageInputStream(in)) {
			        //= [Getting reader for image]
		        	Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
			        ImageReader reader = readers.next(); // TODO: Validate that there are readers
			        //= [Set the Image file to read the MetaData]
			        reader.setInput(input);
			        value = getTextEntry(reader.getImageMetadata(0), metaKey);
			        //= [Logout the Value]
			        //System.out.println("value: " + value);
			    }
			//=== [Last]
			    return value;
		}

} /* EOClass*/
