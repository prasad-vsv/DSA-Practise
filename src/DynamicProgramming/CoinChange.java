package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CoinChange {
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

	public static int max(int i, int j) {
		if (i > j) {
			return i;
		}
		return j;
	}

	public static String max(String s1, String s2) {
		if (s1.length() > s2.length()) {
			return s1;
		}
		return s2;
	}

	public static int max(int i, int j, int k) {
		if (i > j) {
			if (i > k) {
				return i;
			} else {
				return k;
			}
		}
		if (j > k) {
			return j;
		} else {
			return k;
		}

	}

	public static String max(String s1, String s2, String s3) {
		int i = s1.length(), j = s2.length(), k = s3.length();
		if (i > j) {
			if (i > k) {
				return s1;
			} else {
				return s3;
			}
		}
		if (j > k) {
			return s2;
		} else {
			return s3;
		}
	}

	public static int min(int i, int j) {
		if (i < j) {
			return i;
		}
		return j;
	}

	public static String min(String s1, String s2) {
		if (s1.length() < s2.length()) {
			return s1;
		}
		return s2;
	}

	public static int min(int i, int j, int k) {
		if (i < j) {
			if (i < k) {
				return i;
			} else {
				return k;
			}
		}
		if (j < k) {
			return j;
		} else {
			return k;
		}

	}

	public static String min(String s1, String s2, String s3) {
		int i = s1.length(), j = s2.length(), k = s3.length();
		if (i < j) {
			if (i < k) {
				return s1;
			} else {
				return s3;
			}
		}
		if (j < k) {
			return s2;
		} else {
			return s3;
		}
	}

	public static void main(String[] args) {
		//this is a variation of the NumberOfWaysToReachNScore sum
		//for questions where duplicates cannot be considered as different possibilities, move with this approach
		//if duplicates can be considered as different possibilites, then simply go with fibonacci kind of approach
		int score = getNextInt();
		int[] answers = new int[score+1];
		
		//base conditions
		answers[0] = 1;// there is one way to achieve 0 points. By getting none of the points
		//dont follow one for loop method. this will lead to repetitions.	
		
		/*for(int i=0; i<=score ;++i){
				if(i==3 || i==5){
					answers[i] = 1;
					continue;
				}
				if(i==10){
					answers[i] = 2;// 10 points directly or 5 and 5
					continue;
				}
				if((i-3) > 0){
					answers[i] += answers[i-3];
				}
				if((i-5) > 0){
					answers[i] += answers[i-5];
				}
				if((i-10)>0){
					answers[i] += answers[i-10];
				}
			}*/
		//METHOD 1:
		//instead follow incremental for loop => one for loop for each value. 
		//The idea here is that when forloop for small value starts, values for larger values(which will later be filled in the for loop for larger values) will be zero or empty
		//hence avoiding the repetitions
		for(int i=1; i<=score; ++i){
			answers[i] += answers[i-1];
		}
		for(int i=2; i<=score; ++i){
			answers[i] += answers[i-2];
		}
		for(int i=3; i<=score; ++i){
			answers[i] += answers[i-3];
		}
		System.out.println(answers[score]);
		
		//METHOD 2
		//using a matrix instead of adding and putting back in the same array for higher values(like 5 and 10)
		int[][] matrix = new int[score+1][3]; //because 3 possible points exist
		int[] values = {1,2,3};
		//the idea again is that, there are 2 possibilities. either consider at least one instance of the current value[matrix[n-values[m]][m]] or consider no instance of it [matrix[n][m-1]]
		for(int i=0; i<=score;++i){
			for(int j=0; j<values.length;++j){
				if(i==0){
					matrix[i][j] = 1; //considering no values 
					continue;
				}
				if(j>0){ //j cannot be 0 as we are looking for j-1
					matrix[i][j] += matrix[i][j-1];
				}
				if(i-values[j]>=0){ //i-(values[j]) can be 0 as that is the base condition that we are considering
					matrix[i][j] += matrix[i-values[j]][j] ;
				}
				 
			}
		}
		System.out.println(matrix[score][values.length-1]);
	}

}
