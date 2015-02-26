public class KnightsTour {
	
	private int[][] board;
	private int size;
	
	final static String clear =  "\033[2J";
	final static String hide =  "\033[?25l";
	final static String show =  "\033[?25h";
	
	/*public static void main(String[]args) {
		KnightsTour knight = new KnightsTour(5);
		knight.solve();
	}*/
	
	public KnightsTour(int size) {
		board = new int[size][size];
		this.size = size;
	}
	
	private String go(int x,int y){
		return ("\033[" + x + ";" + y + "H");
    }
 
    public void wait(int millis){
		try {
			Thread.sleep(millis);
		}
		catch (InterruptedException e) {
		}
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
		return solve(0,0);
	}
	
	public boolean solve(int x, int y) {
		return solve(x, y, 1);
	}
	
	public boolean solve(int x, int y, int currentMove) {
		
		/*System.out.println(this);
		wait(20);*/
		
		if (currentMove > size * size) {
			return true;
		}
		if (x >= size || x < 0 || y >= size || y < 0) {
			return false;
		}
		if (board[x][y] == 0) {
			board[x][y] = currentMove;
			return solve(x + 2, y + 1, currentMove + 1) ||
				   solve(x + 2, y - 1, currentMove + 1) ||
				   solve(x + 1, y + 2, currentMove + 1) ||
				   solve(x + 1, y - 2, currentMove + 1) ||
				   solve(x - 1, y + 2, currentMove + 1) ||
				   solve(x - 1, y - 2, currentMove + 1) ||
				   solve(x - 2, y + 1, currentMove + 1) ||
				   solve(x - 2, y - 1, currentMove + 1);
		}
		board[x][y] = 0;
		return false;
	}
}