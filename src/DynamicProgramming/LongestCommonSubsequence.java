package DynamicProgramming;

import java.util.Scanner;

public class LongestCommonSubsequence {
	static Scanner s = new Scanner(System.in);

	public static int getNextInt() {
		return Integer.parseInt(s.nextLine());
	}

	public static long getNextLong() {
		return Long.parseLong(s.nextLine());
	} 

	public static int[] getNextIntegerArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		int[] input = new int[tokens.length];
		for (int i = 0; i < tokens.length; ++i) {
			input[i] = Integer.parseInt(tokens[i]);
		}
		return input;
	}

	public static long[] getNextLongArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		long[] input = new long[tokens.length];
		for (int i = 0; i < tokens.length; ++i) {
			input[i] = Long.parseLong(tokens[i]);
		}
		return input;
	}

	public static String getNextLine() {
		return s.nextLine();
	}

	public static String[] getNextStringArray() {
		String line = s.nextLine();
		String[] tokens = line.split(" ");
		return tokens;
	}

	public static void printArray(Object[] objs) {
		for (Object o : objs) {
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		//the idea is that either last letters of both strings will be equal or it is max(LCS(S1[0...n-1],S2), LCS(S1, S2[0...n-1]))
		//we should use DP to solve this and we can store the state of smaller sub problems using a 2 X 2 matrix with each cell,M[i][j] denoting the LCS(i,j) and the value can be a string that is the result of LCS
		String one = getNextLine();
		String two = getNextLine();
		final char[] ONE_CHARS = one.toCharArray();
		final char[] TWO_CHARS = two.toCharArray();
		final int ONE_LENGTH = one.length();
		final int TWO_LENGTH = two.length();
		String[][] store = new String[one.length()+1][two.length()+1];
		store[0][0]="";
		//base statements
		for(int i=0; i< one.length()+1;++i){
			store[i][0] = "";
		}
		
		for(int i=0; i< two.length()+1;++i){
			store[0][i] = "";
		}
		if(ONE_CHARS[0] == TWO_CHARS[0]){
			store[1][1] = ONE_CHARS[0]+"";
		}else{
			store[1][1] = "";
		}
		//populating others using DP
		
		for(int i=0; i<ONE_LENGTH;++i){
			for(int j=0; j< TWO_LENGTH; ++j){
				if(ONE_CHARS[i] == TWO_CHARS[j]){
					store[i+1][j+1] = store[i][j] + ONE_CHARS[i];
				}else{
					//finding and using the max of store[i][j+1] and store[i+1][j]
					if(store[i][j+1].length() > store[i+1][j].length()){
						store[i+1][j+1] = store[i][j+1];
					}else{
						store[i+1][j+1] = store[i+1][j];
					}
				}
			}
		}
		
		
		
		System.out.println(store[ONE_LENGTH][TWO_LENGTH]);
	}
}








