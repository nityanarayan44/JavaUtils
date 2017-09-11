import org.nng.utils.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UnitConversion uc = new UnitConversion();
		
		System.out.println("input to HMS: ");
		// 198 = 0.3.18
		// 3698= 1.1.38
		// 7298= 2.1.38
		//System.out.println( uc.secondsToHMSTime(198) );
		//System.out.println( uc.secondsToHMSTime(7298) );
		long a = 999999999;
		System.out.println( uc.secondsToHMSTime(a) );
	}

}
