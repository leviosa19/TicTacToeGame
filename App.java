

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {

	static ArrayList<Integer> playerPositions = new ArrayList<>();
	static ArrayList<Integer> cpuPositions = new ArrayList<>();
	
	public static void main(String[] args) {

		char [][] gameBoard = {
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '},
								{'-', '+', '-', '+', '-'},
								{' ', '|', ' ', '|', ' '}
							};
		
		printGameBoard(gameBoard);
		
		
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter your placement (1-9): ");
			int playerPos = sc.nextInt();
			
			while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Position taken, enter correct position.");
				playerPos = sc.nextInt();
			}
			
			placePiece(gameBoard, playerPos, "player");

			String result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;

			while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1;
			}
			placePiece(gameBoard, cpuPos, "cpu");
			
			printGameBoard(gameBoard);
			
			result = checkWinner();
			if (result.length() > 0) {
				System.out.println(result);
				break;
			}
			
			
		}
	}
	
	public static void printGameBoard(char[][] gameBoard) {
		for (char[] row: gameBoard) {
			for (char c: row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placePiece(char[][] gameBoard, int pos, String user) {
		char symbol = ' ';
		if (user.equals("player")) {
			symbol = 'x';
			playerPositions.add(pos);
		}
		else {
			symbol = 'O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
			default:
				break;
		}
	}
	
	public static String checkWinner() {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		List<Integer> leftCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> rightCol = Arrays.asList(3, 6, 9);
		List<Integer> leftDig = Arrays.asList(1, 5, 9);
		List<Integer> rightDig = Arrays.asList(7, 5, 3);
		
		List<List> winning= new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(leftDig);
		winning.add(rightDig);
		
		for (List l: winning) {
			if (playerPositions.containsAll(l)) {
				return "Player Won";
			}
			else if (cpuPositions.containsAll(l)) {
				return "CPU Won";
			}
			else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "CAT";
			}
		}
		
		return "";
	}

}
