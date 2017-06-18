package DynamicProgramming;

import java.util.*;

public class AssemblyLineScheduling {
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
		// http://www.geeksforgeeks.org/dynamic-programming-set-34-assembly-line-scheduling/
		//The idea is that every bogey in a line can be reached, either from previous bogey in the same line or from previous bogey in the other line
		//If it is coming from previous bogey in the same line, no additional cost is incured. If it is coming from previous bogey in the other line, then additional cost of transfer is incured..
		
		//iinputs are costs of manufacturing in each bogeys, transfer costs from one line to another'
		final int NUM_STATIONS= 4;
		final int NUM_LINES = 2;
		int a[][] = {{4, 5, 3, 2},
                {2, 10, 1, 4}};
		int t[][] = {{0, 7, 4, 5},
                {0, 9, 2, 8}}; 
		int e[] = {10, 12}, x[] = {18, 7}; //e stands for entry times for station one in lines and x stands for exit times for last station in both lines
		
		int[][] dp = new int[NUM_STATIONS][NUM_LINES];
		
		//base cases
		dp[0][0] = e[0] + a[0][0];
		dp[0][1] = e[1] + a[1][0];
		
		for(int i=1; i<NUM_STATIONS;++i){
				dp[i][0] = min (dp[i-1][0] + a[0][i], dp[i-1][1]+a[1][i] + t[0][i]); //t[0][i] stands for transfer cost to reach oth line, ith station from the other line
				dp[i][1] = min(dp[i-1][1]+a[1][i], dp[i-1][0] + a[0][i] + t[1][i]);
			
		}
		System.out.println(min(dp[NUM_STATIONS-1][0] + x[0] , dp[NUM_STATIONS-1][1] + x[1]));
	}
}

























