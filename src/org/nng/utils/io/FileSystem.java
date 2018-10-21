/**
 * @author AshutoshMishra [NNG]
 * @written June 2018 
 * @desc Handles the various Operations on FileSystem [part of library v2]
 * @pattern Standard Java
 */

// Package
package org.nng.utils.io;

// Import
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// Class
public class FileSystem {

	// Globals
	public static String objectPath;

	// Functions

	// =================================
	// Function to find a path recursively
	// =================================
	/**
	 * Follow and extract the path recursively until a file is found
	 * @param path
	 * @param consumer
	 */
	public static void recursiveFind(Path path, Consumer<Path> consumer) {
		try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(path)) {
			StreamSupport.stream(newDirectoryStream.spliterator(), false).peek(p -> {
				consumer.accept(p);
				if (p.toFile().isDirectory()) {
					recursiveFind(p, consumer);
				}
			}).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// =================================
	// Function to find a path 
	// =================================
	/**
	 * Returns the full path of any file, from the current working directory
	 * @param fileName
	 * @return
	 */
	public static String fullPathOfFile(String fileName) {
		recursiveFind(Paths.get(System.getProperty("user.dir")), path -> {
			if (path.toFile().getName().toString().equals(fileName)) {
				objectPath = path.toAbsolutePath().toString();
			}
		});
		return objectPath;
	}

	// Write a CSV File with some data
	// =================================
	// Function to write a CSV File 
	// =================================
	public static void generateCSV(String filePath, String csvFileName, String csvHeaders, String[] csvData) {
		// Writer    
		try (Writer writer = new FileWriter(filePath + csvFileName)) {
			// Add CSV Header
			writer.append(csvHeaders).append(System.lineSeparator());
			
			// Add CSV Data
			for (String entry : csvData) {
				writer.append(entry).append(System.lineSeparator());	
			}
		 } catch (IOException ex) {
			 // Exception during CSV creation
			 ex.printStackTrace(System.err);
		 }
	}
	
	// TODO: Add Functions for another type of files
} // EOClass