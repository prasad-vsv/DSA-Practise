package Backtracking;

import java.util.ArrayList;
import java.util.List;


public class SudokuSolver {
	
	private static final int size = 9;
	private static int count = 0;
	
	public static final int[][] board = {
		{3,0,6,5,0,8,4,0,0},
		{5,2,0,0,0,0,0,0,0},
		{0,8,7,0,0,0,0,3,1},
		{0,0,3,0,1,0,0,8,0},
		{9,0,0,8,6,3,0,0,5},
		{0,5,0,0,9,0,6,0,0},
		{1,3,0,0,0,0,2,5,0},
		{0,0,0,0,0,0,0,7,4},
		{0,0,5,2,0,6,3,0,0}
	};

	public static void main(String[] args) {
		printBoard();
		System.out.println("solution below");
		solve(new Positon(0,0));

	}
	
	private static void solve(Position p) {
		//if count is 81, that means all the position sare filled. Hence print the board
		if(count == 81){
			printBoard();
		}
		
		//the order of the above and below statement is important because when the solution
		//is found, both the cases would be true and hence we should first print the solution
		//and then return
		if(p.x == 9 || p.y == 9) {
			return;
		}
		
		//starting with 0,0. If a position has a value of non-zero then simply return
		if(board[p.x][p.y] != 0) {
			++count;
			//that means the the position is already filled. Simply proceed with the next position
			if(p.x < 8) {
				//that means there are more rows in the same column to be filled. Hence proceed
				//with the next row in same column
				solve(new Position(p.x+1, p.y));
			} else {
				//go to first element in the next column
				solve(new Position(0,p.y+1));
			}
		} else {
			List<Integer> possibleNumbers = getPossibleNumbers(p);
			for(int number: possibleNumbers) {
				//assuming that this is the correct number to be placed and then proceeding
				board[p.x][p.y] = number;
				int temp = count;
				cpunt +=1 ;
				printBoard();
				if(p.x<8) {
					//that means that there are more rows in the same column to be filled, hence proceed
					//with the next row in the same column
					solve(new Position(p.x+1, p.y));
				} else {
					//go to first element in the next column
					solve(new Position(0, p.y+1));
				}
				//all option with previous number as posibility are explored. Hence remove any traces of
				//its use
				board[p.x][p.y] = 0;
				count = temp; //to take away the effects of original numbers as well
			}
		}
		
	}
	
	private static List<Integer> getPossibleNumber(Position p) {
		List<Integer> possibleNumbers = new ArrayList<>();
		for(unt i=1; i<10; ++i) {
			if(isValidMove(i,p)){
				possibleNumber.add(i);
			}
		}
		return possibleNumbers;
	}
	
	//private

	private static void printBoard() {
		// TODO Auto-generated method stub
		
	}
}
