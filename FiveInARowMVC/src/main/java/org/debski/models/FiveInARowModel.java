package org.debski.models;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FiveInARowModel {
	
	private static final int SIZE = 15;
	
	private char[][] board;
	private	char currentPlayer;
	public char getCurrentPlayer() {
		return this.currentPlayer;
	}

	public FiveInARowModel() {
		board = new char[SIZE][SIZE];
	}
	
	public String play(int x, int y) {
		x -= 1;
		y -= 1;
		checkAxis(x);
		checkAxis(y);
		selectPlayer();
		mark(x,y);
		return checkIfWin(x, y);
	}
	
	private void checkAxis(int axis) {
		if (axis > 14) throw new RuntimeException("Wspolrzedna przekracza rozmiar pola gry");
	}
	
	private void mark(int x, int y) {
		if(board[x][y] != 0) throw new RuntimeException("Probujesz zaznaczyc pole, ktore jest juz zaznaczone");
		board[x][y] = currentPlayer;
	}
	
	private void selectPlayer() {
		if (currentPlayer == 'X') currentPlayer = 'O';
		else currentPlayer = 'X';
	}
	
	private String checkIfWin(int row, int column) {
		//regex: .*(^|[^X])X{5}($|[^X]).*
		Pattern winPattern = Pattern.compile(".*(^|[^" + currentPlayer +"])" + currentPlayer + "{5}($|[^" + currentPlayer + "]).*");
		boolean isFull = true;
		
		String vertical = IntStream.range(0, SIZE).mapToObj(r -> String.valueOf((board[r][column]))).reduce(String::concat).get();	
		if (winPattern.matcher(vertical).matches()) return "Zwycieza " + currentPlayer;
		
		String horizontal = Stream.of(board[row]).map(e -> String.valueOf(e)).reduce(String::concat).get();
		if (winPattern.matcher(horizontal).matches()) return "Zwycieza " + currentPlayer;
		
		int startOffset = Math.min(column, row);
        int myColumn = column - startOffset;
        int myRow = row - startOffset;
        StringJoiner stringJoiner = new StringJoiner("");
        do {
            stringJoiner.add(String.valueOf(board[myRow++][myColumn++]));
        } while (myColumn < SIZE && myRow < SIZE);
        if (winPattern.matcher(stringJoiner.toString()).matches()) return "Zwycieza " + currentPlayer;
        
        startOffset = Math.min(column, SIZE - 1 - row);
        myColumn = column - startOffset; 
        myRow = row + startOffset;
        stringJoiner = new StringJoiner("");
        do {
            stringJoiner.add(String.valueOf(board[myRow--][myColumn++]));
        } while (myColumn < SIZE && myRow >= 0);
        if (winPattern.matcher(stringJoiner.toString()).matches()) return "Zwycieza " + currentPlayer;
        
        for(int i = 0; i < SIZE; i++) {
        	if (Arrays.binarySearch(board[i], '\u0000') < 0) return "Remis";
        }
        
        return "Brak zwyciezcy";
	}
}
