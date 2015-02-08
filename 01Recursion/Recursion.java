public class Recursion implements hw1 {
	
	public static void main(String[]args) {
		Recursion var = new Recursion();
		System.out.println(var.sqrt(2));
	}
	
	public String name() { 
		return "Saric,Elias"; 
	}
	
	public int fact(int n) { 
		if (n > 1) {
			return n * fact(n-1);
		}
		return 1;
	}
	
	public int fib(int n) {
		return fibHelper(n, 0, 1);
	}
	
	public int fibHelper(int n, int prev1, int prev2) {
		if (n < 1) {
			return prev1;
		}
		return fibHelper(n-1, prev2, prev1+prev2);
	}
	public double sqrt(double n) {
		return sqrtHelper(n, n/10);
	}
	
	public double sqrtHelper(double n, double guess) {
		if (guess*guess < n*1.00001 && guess*guess > n*0.99999 ) {
			return guess;
		}
		return sqrtHelper(n, ( n / guess + guess) / 2);
	}
}