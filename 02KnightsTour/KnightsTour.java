public class KnightsTour {
	
	public int[][] board;
	public int size;
	
	public static void main(String[]args) {
		KnightsTour knight = new KnightsTour(5);
		System.out.println(knight);
	}
	
	public KnightsTour(int size) {
		board = new int[size][size];
		this.size = size;
	}
	
	public String name() {
		return "saric.elias";
	}
	
	public String toString() {
		String outString = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] < 100) {
					outString += " ";
				}
				if (board[i][j] < 10) {
					outString += " ";
				}
				outString += board[i][j];
			}
			outString += "\n";
		}
		return outString;
	}
	
	public boolean solve() {
		solve(0,0);
	}
	
	public boolean solve(int x, int y) {
		solve(x, y, 0);
	}
	
	public boolean solve(int x, int y, int currentMove) {
		return false;
	}
}