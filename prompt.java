import java.util.InputMismatchException;
import java.util.Scanner;

public class prompt {
	public static void main (String [] args) {
		Scanner input = new Scanner (System.in);
		board b1 = new board();
		boolean hasExceptions = true;
		int n;
		
		b1.displayBoard();
		
		while (true) {
			hasExceptions = true;
			while (hasExceptions) {
				try {
					System.out.print("Drop a red disk at column (1-7):");
					n = input.nextInt();
					n -= 1;
					b1.add(n, "R");
					hasExceptions = false;
				}
				catch (IndexOutOfBoundsException e1) {
					System.out.println("Column is filled");
					input.nextLine();
				}
				catch (InputMismatchException e2) {
					System.out.println("Wrong input");
					input.nextLine();
				}
			}
			b1.displayBoard();
			if (b1.win("R")) {
				System.out.println ("The red player won");
				break;
			}
			if (b1.isDraw("R")) {
				System.out.println ("DRAW!!!");
				break;
			}
			hasExceptions = true;
			while (hasExceptions) {
				try {
					System.out.print("Drop a yellow disk at column (1-7): ");
					n = input.nextInt();
					n -= 1;
					b1.add(n, "Y");
					hasExceptions = false;
				}
				catch (IndexOutOfBoundsException e1) {
					System.out.println("Column is filled");
					input.nextLine();
				}
				catch (InputMismatchException e2) {
					System.out.println("Wrong input");
					input.nextLine();
				}
			}
			
			b1.displayBoard();
			if (b1.win("Y")) {
				System.out.println ("The yellow player won");
				break;
			}
			if (b1.isDraw("Y")) {
				System.out.println ("DRAW!!!");
				break;
			}
		}
		input.close();
	}
}

class board {
	public String [][] boardArray = new String [6][7];
	
	public board () {
		for (int i=0; i < boardArray.length; i++)
			for (int j=0; j < boardArray[i].length; j++)
				boardArray[i][j]=" ";
	}
	
	public void displayBoard() {
		for (int i=0; i < boardArray.length; i++)
			System.out.printf("|%s|%s|%s|%s|%s|%s|%s|\n", boardArray[i][0], boardArray[i][1], boardArray[i][2], boardArray[i][3], boardArray[i][4], boardArray[i][5], boardArray[i][6]);
		System.out.print("...............\n");
	}
	
	public void add (int n, String color) {
		int row = 5;
		boolean isEmpty = true;
		while (isEmpty) {
			if (boardArray[row][n].equals(" ")) {
				boardArray[row][n]=color;
				isEmpty=false;
			}
			else
				row--;
		}
	}
	
	public boolean win (String color) {
		for (int i=0; i < boardArray.length; i++)
			for (int j=0; j < boardArray[i].length-3; j++)
				if (boardArray[i][j].equals(color) && boardArray[i][j+1].equals(color) && boardArray[i][j+2].equals(color) && boardArray[i][j+3].equals(color))
					return true;
		for (int i=0; i < 7; i++)
			for (int j=0; j < 3; j++)
				if (boardArray[j][i].equals(color) && boardArray[j+1][i].equals(color) && boardArray[j+2][i].equals(color) && boardArray[j+3][i].equals(color))
					return true;	
		for (int i=0; i < 6; i++)
			for (int j=0; j < 7; j++) {
				if (j+3 >= 7 || i + 3 >= 6)
					continue;
				if (boardArray[i][j].equals(color) && boardArray[i+1][j+1].equals(color) && boardArray[i+2][j+2].equals(color) && boardArray[i+3][j+3].equals(color))
					return true;
			}
		
		for (int i=0; i < 6; i++)
			for (int j=0; j < 7; j++) {
				if ((j+3) >= 7 || (i-3) < 0)
					continue;
				if (boardArray[i][j].equals(color) && boardArray[i-1][j+1].equals(color) && boardArray[i-2][j+2].equals(color) && boardArray[i-3][j+3].equals(color))
					return true;
			}
				
		return false;
	}
	
	public boolean isDraw (String color) {
		String [][] draw = new String [4][7];
		for (int i = 0; i < draw.length; i ++)
			for (int j = 0; j < draw[i].length; j++)
				if (boardArray[i][j]!=" ")
					draw[i][j] = boardArray[i][j];
				else 
					draw[i][j] = color;
		
		for (int i=0; i < draw.length; i++)
			for (int j=0; j < draw[i].length-3; j++)
				if (draw[i][j].equals(color) && draw[i][j+1].equals(color) && draw[i][j+2].equals(color) && draw[i][j+3].equals(color))
					return false;
		for (int i=0; i < 7; i++)
			if (draw[0][i].equals(color) && draw[1][i].equals(color) && draw[2][i].equals(color) && draw[3][i].equals(color))
					return false;	
		for (int i=0; i < draw.length-3; i++)
			if (draw[0][i].equals(color) && draw[1][i+1].equals(color) && draw[2][i+2].equals(color) && draw[3][i+3].equals(color))
					return false;
		for (int i=3; i < draw.length; i++)
			if (draw[0][i].equals(color) && draw[1][i-1].equals(color) && draw[2][i-2].equals(color) && draw[3][i-3].equals(color))
					return false;
			
		return true;
	}

}
