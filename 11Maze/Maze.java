import java.util.*;
import java.io.*;
public class Maze {
    private static final String clear = "\033[2J";
    private static final String hide = "\033[?25l";
    private static final String show = "\033[?25h";
    private String go(int x,int y){
		return ("\033[" + x + ";" + y + "H");
    }
    private static final int DFS = 0;
    private static final int BFS = 1;
    private static final int BEST = 2;
    private static final int ASTAR = 3;
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
		    return new Coordinate(j, i);
		}
	    }
	}
	return null;
    }
    private Coordinate findEnd() {
	for (int i = 0; i < array.length; i++) {
	    for (int j = 0; j < array[0].length; j++) {
		if (array[i][j] == 'E') {
		    return new Coordinate(j, i);
		}
	    }
	}
	return null;
    }
    public boolean solve(boolean animate, int mode) {
		Frontier front = new Frontier(mode);
		front.enqueue(findStart());
		while (!front.isEmpty()) {
			//System.out.println(front);
			Coordinate removed = front.dequeue();
			//System.out.println(front);
			//System.out.println(removed);
			if (array[removed.getY()][removed.getX()] == 'E') {
			while (removed != null) {
				array[removed.getY()][removed.getX()] = '@';
				removed = removed.getPrevious();
				if (animate) {
					try {
						Thread.sleep(50);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					System.out.println(clear + hide + go(0,0) + this + show);
				}
			}
			return true;
			}
			array[removed.getY()][removed.getX()] = 'X';
			//System.out.println(front);
			if (array[removed.getY()+1][removed.getX()] != '#' && array[removed.getY()+1][removed.getX()] != 'X') {
				front.enqueue(new Coordinate(removed.getX(), removed.getY()+1, removed));
			}
			//System.out.println(front);
			if (array[removed.getY()-1][removed.getX()] != '#' && array[removed.getY()-1][removed.getX()] != 'X') {
				front.enqueue(new Coordinate(removed.getX(), removed.getY()-1, removed));
			}
			if (array[removed.getY()][removed.getX()+1] != '#' && array[removed.getY()][removed.getX()+1] != 'X') {
				front.enqueue(new Coordinate(removed.getX()+1, removed.getY(), removed));
			}
			if (array[removed.getY()][removed.getX()-1] != '#' && array[removed.getY()][removed.getX()-1] != 'X') {
				front.enqueue(new Coordinate(removed.getX()-1, removed.getY(), removed));
			}
			if (animate) {
				try {
					Thread.sleep(50);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				System.out.println(clear + hide + go(0,0) + this + "\n" + front + show);
			}
			//System.out.println("----------------------");
		}
		return false;
    }
    public boolean solveBFS(boolean animate) {
		return solve(animate, BFS);
    }
    public boolean solveDFS(boolean animate) {
		return solve(animate, DFS);
    }
	public boolean solveBest(boolean animate) {
		return solve(animate, BEST);
	}
	public boolean solveAStar(boolean animate) {
		return solve(animate, ASTAR);
	}
    public boolean solveBFS() {return solveBFS(false);}
    public boolean solveDFS() {return solveDFS(false);}
	public boolean solveBest() {return solveBest(false);}
	public boolean solveAStar() {return solveAStar(false);}
	
	private int distanceToEnd(Coordinate cor) {
		Coordinate end = findEnd();
		return Math.abs(cor.getX() - end.getX()) + Math.abs(cor.getY() - end.getY());
	}
	
    private class Coordinate {
		private int x;
		private int y;
		private int steps;
		private Coordinate previous;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
			previous = null;
			steps = 0;
		}
		public Coordinate(int x, int y, Coordinate previous) {
			this.x = x;
			this.y = y;
			this.previous = previous;
			steps = previous.getSteps() + 1;
		}
		public String toString() {
			return "(" + y + ", " + x + ")";
		}
		public int getX() {return x;}
		public int getY() {return y;}
		public int getSteps() {return steps;}
		public Coordinate getPrevious() {return previous;}
    }
    private class Frontier{
		private MyDeque<Coordinate> queue = new MyDeque<Coordinate>();
		private int mode;
		public Frontier(int mode) {
			this.mode = mode;
		}
		public String toString() {
			System.out.println(queue);
			return "";
		}
		public Coordinate enqueue(Coordinate cor) {
			if (mode == 0 || mode == 1) {
				queue.addFirst(cor);
			}
			if (mode == 2) {
				queue.add(cor, distanceToEnd(cor));
			}
			if (mode == 3) {
				queue.add(cor, distanceToEnd(cor) + cor.getSteps());
			}
			return cor;
		}
		public Coordinate dequeue() {
			if (mode == 0) {
				return queue.removeFirst();
			}
			if (mode == 1) {
				return queue.removeLast();
			}
			if (mode == 2 || mode == 3) {
				return queue.removeSmallest();
			}
			return null;
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
		maze.solveAStar(true);
    }
}