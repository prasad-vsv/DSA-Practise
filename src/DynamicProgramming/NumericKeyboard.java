package DynamicProgramming;

import java.util.Scanner;

public class NumericKeyboard {
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
		int size = getNextInt();
		int[][] matrix = new int[size+1][10];
		int[][] adj = {{8},{2,4},{1,3,5},{2,6},
						{1,5,7},{2,4,6,8},{3,5,9},{4,8},
							{0,5,7,9},{6,8}};
		for(int i=0; i<=size; ++i){
			for(int j=0; j<10; ++j){
				if(i==0){
					matrix[i][j] = 0;
					continue;
				}
				if(i==1){
					matrix[i][j] = 1;
					continue;
				}
				int[] adjacentIndexes = adj[j];
//				matrix[i][j] = 1; //because same number can be pressed size number of times and we should also count it
				//that will be counted because we are considering the same number in 'matrix[i][j] += matrix[i-1][j];' and that in turn would use the same number in its previous step
				for(int k=0; k<adjacentIndexes.length; ++k){
					int index = adjacentIndexes[k];
					matrix[i][j] += matrix[i-1][index];
				}
				matrix[i][j] += matrix[i-1][j]; //counting self in the sub problem also => 112,115 etc:
			}
		}
		int sum = 0;
		for(int i=0; i<adj.length; ++i){
			sum += matrix[size][i];
		}
		System.out.println(sum);
	}
}
