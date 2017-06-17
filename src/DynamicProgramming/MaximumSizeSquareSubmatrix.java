package DynamicProgramming;

import java.util.*;

public class MaximumSizeSquareSubmatrix {
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
		// http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
		int[][] matrix = {{0, 1, 1, 0, 1}, 
                {1, 1, 0, 1, 0}, 
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
		//my intuition says that a square matrix can be extended by looking at its top left diagonal element and checking if the current row and current column support to extend it by one level
		// unfortunately this is wrong approach as we are only considering the top left diagonal element's possibility and not the ones that we could use with top or left elements..
		//see the below alternative approach
		int[][] cache = new int[matrix.length][matrix[0].length];
		System.out.println(dp(matrix,cache));
		
		
		//here cache[i][j] denotes the maximum size of sub matrix that can be formed ending at matrix[i][j]
		//base cases, cache should be filled with 1, when matrix has 1. as every element can be considered as submatrix
		int max = Integer.MIN_VALUE;
		for(int i=0; i<matrix.length;++i){
			for(int j=0; j<matrix[0].length;++j){
				if(matrix[i][j] == 0){
					cache[i][j] =0;
					continue; // the matrix cannot anyways end here
				}
				if(matrix[i][j] == 1){
					cache[i][j] = 1; //atleast the current element can be considered as a submatrix
					if(max<cache[i][j]){
						max = cache[i][j];
					}
					if(i == 0 || j== 0){
						continue; //the next case will not be a valid one
					}
					
				}
				if(matrix[i-1][j-1] >0 ){
					int cum = matrix[i-1][j-1];
					boolean valid = true;
					for(int k=1 ;k<=cum ; ++k){
						if(matrix[i-k][j] == 0 || matrix[i][j-k] == 0){
							valid = false;
							break;
						}
					}
					if(valid){
						cache[i][j] = cum+1 ; 
						if(max<cache[i][j]){
							max = cache[i][j];
						}
					}
				}
			}
		}
//		System.out.println(max);
	}

	private static int dp(int[][] matrix, int[][] cache) {
		// The only elements that we could reach from a given current element matrix[i][j] are matrix[i-1][j-1], matrix[i][j-1] and matrix[i-1][j].
		//so we should consider all the 3 possibilities and then take the min of them as that would limit the overall size anyways.
		//base case : if an element is o, then the cache value is also 0. If the element is 1, the cache value is atleast 1.
		// also the first row and first column can be directly filled using above logic as sqaure matrix is possible only using the current element.
		
		for(int i=0; i<matrix.length; ++i){
			cache[i][0] = matrix[i][0];
		}
		for(int i=0; i<matrix[0].length; ++i){
			cache[0][i] = matrix[0][i];
		}
		int max = Integer.MIN_VALUE;
		
		for(int i=1; i<matrix.length;++i){
			for(int j=1; j<matrix[0].length;++j){
				if(matrix[i][j] == 0){
					cache[i][j] = 0;
					continue;
				}
				 
				cache[i][j] = min(cache[i-1][j-1], cache[i][j-1], cache[i-1][j]) + 1; //+1 is added because we are taking the least from 3 options and so others will be 1s w.r.t the requirement and considering the current element, we should add 1
				max = max(cache[i][j],max);

			}
		}
		return max;
	}
}
