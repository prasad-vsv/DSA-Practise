package DynamicProgramming;

import java.util.Scanner;

public class EditDistance {
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
	
	public static int max(int i, int j){
		if(i>j){
			return i;
		}
		return j;
	}
	
	public static String max(String s1, String s2){
		if(s1.length() > s2.length()){
			return s1;
		}
		return s2;
	}
	
	public static int max(int i, int j, int k){
		if(i>j){
			if(i>k){
				return i;
			}else{
				return k;
			}
		}
		if(j>k){
			return j;
		}else{
			return k;
		}
		
	}
	
	public static String max(String s1, String s2, String s3){
		int i = s1.length(), j = s2.length(), k = s3.length();
		if(i>j){
			if(i>k){
				return s1;
			}else{
				return s3;
			}
		}
		if(j>k){
			return s2;
		}else{
			return s3;
		}
	}
	
	public static int min(int i, int j){
		if(i<j){
			return i;
		}
		return j;
	}
	
	public static String min(String s1, String s2){
		if(s1.length() < s2.length()){
			return s1;
		}
		return s2;
	}
	
	public static int min(int i, int j, int k){
		if(i<j){
			if(i<k){
				return i;
			}else{
				return k;
			}
		}
		if(j<k){
			return j;
		}else{
			return k;
		}
		
	}
	
	public static String min(String s1, String s2, String s3){
		int i = s1.length(), j = s2.length(), k = s3.length();
		if(i<j){
			if(i<k){
				return s1;
			}else{
				return s3;
			}
		}
		if(j<k){
			return s2;
		}else{
			return s3;
		}
	}

	public static void main(String[] args) {
		String s1 = getNextLine();
		String s2 = getNextLine();
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		
		int[][] moves = new int[s1.length()+1][s2.length()+1];
		//intializing moves
		//if one of the string is empty, the answer would be the length of the other string. that many inserts are required
		for(int i=0; i< s1.length()+1;++i){
			moves[i][0] = i;
		}
		for(int i=1; i< s2.length()+1;++i){
			moves[0][i] = i;
		}
		
		int maxlength = max(s1.length(),s2.length());

		
		
		for(int i=1; i< s1.length()+1;++i){
			for(int j=1; j<s2.length()+1;++j){
				if(c1[i-1] == c2[j-1]){
					moves[i][j] = moves[i-1][j-1];
				}else{
					moves[i][j] = min((moves[i-1][j-1]+1), moves[i-1][j] +1 , moves[i][j-1] +1);
				}
			}
		}
		
		System.out.println(moves[s1.length()][s2.length()]);
	}
}
