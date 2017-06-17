package DynamicProgramming;

import java.util.*;

public class SubSetSum {
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
		// http://www.geeksforgeeks.org/dynamic-programming-subset-sum-problem/
		int sum =1;
		int[] set = {3, 34, 4, 12, 5, 2};
		boolean[][] dp = new boolean[sum+1][set.length+1];
		
		for(int i=0; i<=sum;++i){
			for(int j=0; j<=set.length;++j){
				//base case: if sum==0, we can achieve this by null set
				if(i==0){
					dp[i][j] = true;
					continue;
				}
				//if set is empty, we cannot achieve this.
				if(j==0 ){
					dp[i][j] = false;
					continue;
				}
				//The idea is that there are only two things that we can do with every option
				// Case A: don't consider the option: in which case sum remains same, but options become one less i.e, dp[i][j-1]
				//Case B: consider the option: in which case, the sum reduces by the option's value and the options become one less. Remember, this is  a set which means that every option can be considered only once.
				//i.e, dp[i-currentValue][j-1]
				boolean a = dp[i][j-1];
				boolean b = false;
				if(i-set[j-1] >= 0){
					b = dp[i-set[j-1]][j-1];
				}
				
				dp[i][j] = a||b;
			}
		}
		System.out.println(dp[sum][set.length-1]);
	}
}
