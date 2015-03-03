public class NQueens {
	
	private int[][] board;
	
	public NQueens(int size) {
		board = new int[size][size];
	}
	
	public String toString() {
		return "";
	}
	
	public String name() {
		return "saric.elias";
	}
	
	public boolean solve() {
		return solve(0);
	}
	
	public boolean solve(int x) {
		return solve(0, x);
	}
	
	public boolean solve(int r, int c) {
		return true;
	}
}