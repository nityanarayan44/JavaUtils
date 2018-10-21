package Examples;

public class Complexity {

	static int a=1, t=64, n=100;
	static int counterB =0, counterC = 0, inp = 0;
	
	
	public static void main(String[] args) {
		
		for (int p = t; p > 0; p >>= 1) {
			System.out.println("\n P= " + p);
			for(int q = t; q > p; q >>= 1) {
				counterB++;
				System.out.println("\n, Q=" + q + "________ >>>");
		        for(int i = 0; i < n- q; ++i) {
		            if ((i & p) != 0) {
		                //sort2(a, i+p, i+q);
		            		counterC++;
						System.out.print(", i = " + i + "");
		            }
		        }
		    }
			//System.out.println("CounterB = " + counterB + ", CounterC = " + counterC);
			counterB = 0;
			counterC = 0;
		}
		
	}
	
	public static void mammin(String[] args) {
		// TODO Auto-generated method stub

		
		
		for (int p = t; p > 0; p >>= 1) {
			 for (int i = 0; i < n - p; ++i) {
				 if ((i & p) != 0) {
					 counterC++;
					 inp = (i & p); 
					 //System.out.println("-- Sort-inner:: , a, i, i+p");
				 }
			 }
			 //  a, p,,,,,,
			 System.out.println(">>> Outer:: Value P="+p+", Value I= "+(n-p)+",  i & p = "+inp+", And Inner loop Count = " + counterC);
			 counterC = 0;
			 inp = 0;
		}
	}

	// outer = 7
	// inner = 
}
