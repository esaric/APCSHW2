import java.util.*;
import java.io.*;

public class Maze {
    
    private static final String clear =  "\033[2J";
    private static final String hide =  "\033[?25l";
    private static final String show =  "\033[?25h";
    private static final int DFS = 0;
    private static final int BFS = 1;
    private char[][] array;
    

    public Maze() {}

    public Maze(String filename) {
		try {
			Scanner in = new Scanner(new File(filename));
			ArrayList<String> lines = new ArrayList<String>();
			while (in.hasNextLine()) {
				lines.add(in.nextLine());
			}
			array = new char[lines.size()][lines.get(0).length()];
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					array[i][j] = lines.get(i).charAt(j);
				}
			}
		}catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
    }

    public String toString() {
		String ans = "";
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
			ans += array[i][j] + " ";
			}
			ans += "\n";
		}
		return ans;
    }

    private Coordinate findStart() {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
			if (array[i][j] == 'S') {
				return new Coordinate(i, j, null);
			}
			}
		}
		return null;
    }

    public boolean solveBFS(boolean animate) {
		Frontier front = new Frontier();
		front.enqueue(findStart());
		while (!front.isEmpty()) {
			int y = front.peek().getY();
			int x = front.peek().getX();
			Coordinate removed = front.peek();
			if (array[y][x] == 'E') {
				while (removed != null) {
					array[removed.getY()][removed.getX()] = '@';
					removed = removed.getPrevious();
					if (animate) {
						try {
							Thread.sleep(200);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						System.out.println(this);
					}
				}
				return true;
			}
			array[y][x] = 'X';
			if (array[y+1][x] != '#' && array[y+1][x] != 'X') {
				front.enqueue(new Coordinate(x, y+1, front.peek()));
			}
			if (array[y-1][x] != '#' && array[y-1][x] != 'X') {
				front.enqueue(new Coordinate(x, y-1, front.peek()));
			}
			if (array[y][x+1] != '#' && array[y][x+1] != 'X') {
				front.enqueue(new Coordinate(x+1, y, front.peek()));
			}
			if (array[y][x-1] != '#' && array[y][x-1] != 'X') {
				front.enqueue(new Coordinate(x-1, y, front.peek()));
			}
			front.dequeue();
			if (animate) {
				try {
					Thread.sleep(200);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				System.out.println(this);
			}
		}
		return false;
    }
    public boolean solveDFS(boolean animate) {
		return true;
    }
    public boolean solveBFS() {return solveBFS(false);}
    public boolean solveDFS() {return solveDFS(false);}

    private class Coordinate {
	
		private int x;
		private int y;
		private Coordinate previous;

		public Coordinate(int x, int y, Coordinate previous) {
			this.x = x;
			this.y = y;
			this.previous = previous;
		}

		public String toString() {
			return "(" + x + ", " + y + ")"; 
		}
		
		public int getX() {return x;}
		public int getY() {return y;}
		public Coordinate getPrevious() {return previous;}
    }

    private class Frontier{
	
		private MyDeque<Coordinate> queue = new MyDeque<Coordinate>();
		
		public String toString() {
			System.out.println(queue);
			return "";
		}

		public Coordinate enqueue(Coordinate cor) {
			queue.addLast(cor);
			return cor;
		}
		public Coordinate dequeue() {
			return queue.removeFirst();
		}
		public Coordinate peek() {
			return queue.getFirst();
		}
		public boolean isEmpty() {
			return queue.getLength() == 0;
		}

    }

    public static void main(String[]args) {
		Maze maze = new Maze("maze.txt");
		maze.solveBFS(true);
    }
}