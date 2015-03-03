public class MakeLake {

    private int R, C, E, N;
    
    private int[][] lake = {{28, 25, 20, 32, 34, 36}, 
			    {27, 25, 20, 20, 30, 34}, 
			    {24, 20, 20, 20, 20, 30}, 
			    {20, 20, 14, 14, 20, 20}};
    
    public MakeLake(int r, int c, int e, int n) {
	R = r;
	C = c;
	E = e;
	N = n;
    }

    public static void main(String[]args) {
	MakeLake lake = new MakeLake(4, 6, 22, 2);
	System.out.println(lake);
	lake.stompDig(1, 4, 4);
	System.out.println(lake);
	lake.stompDig(1, 1, 10);
	System.out.println(lake);
	lake.elevation();
	System.out.println(lake);
	System.out.println(lake.density());
    }

    public String toString() {
	String outString = "";
	for (int i = 0; i < R; i++) {
	    for (int j = 0; j < C; j++) {
		if (lake[i][j] < 10 && lake[i][j] > 0) {
		    outString += " ";
		}
		if (lake[i][j] <= 0) {
		    outString += "-- ";
		}else {
		    outString += lake[i][j] + " ";
		}
	    }
	    outString += "\n";
	}
	return outString;
    }

    public void stompDig(int row, int col, int down){
	int max = 0;
	for (int i = row - 1; i < row + 2; i++) {
	    for (int j = col - 1; j < col + 2; j++) {
		if (lake[i][j] > max) {
		    max = lake[i][j];
		}
	    }
	}
	int level = max - down;
	for (int i = row - 1; i < row + 2; i++) {
	    for (int j = col - 1; j < col + 2; j++) {
		if (lake[i][j] > level) {
		    lake[i][j] = level;
		}
	    }
	}
    }

    public void elevation() {
	for (int i = 0; i < R; i++) {
	    for (int j = 0; j < C; j++) {
		lake[i][j] = E - lake[i][j];
	    }
	}
    }

    public int density() {
	int sum = 0;
	for (int i = 0; i < R; i++) {
	    for (int j = 0; j < C; j++) {
		if (lake[i][j] > 0) {
		    sum += lake[i][j];
		}
	    }
	}
	return sum * 72 * 72;
    }
}
