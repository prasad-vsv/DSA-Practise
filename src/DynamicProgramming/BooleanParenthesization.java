package DynamicProgramming;

import java.util.*;

public class BooleanParenthesization {
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
	
	private static boolean calc(int a, int b){
		boolean result=false;
		for(int i=a;i<b;++i){
			char ac = act[a];
			char next = act[a+1];
			boolean ab = (ac == 'T')? true:false;
			boolean nb = (next == 'T');
			char o = ops[i];
			switch(o){
			case '|':
				result= ab|nb;
				break;
			case '&':
				result = ab&nb;
				break;
			case '^':
				result = ab^nb;
			}
		}
		return result;
	}
	static String in = "T | T & F ^ T"; 
	static char[] act = {'T','T','F','T'};
	static char[] ops = {'|','&','^'};
	public static void main(String[] args) {
		//http://practice.geeksforgeeks.org/problems/boolean-parenthesization/0
		
		int len = 4;
		boolean[][] cache = new boolean[len][len];
		int[][] counts = new int[len][len];
		for(int i=0;i<len;++i){
			cache[i][i] = (act[i] == 'T');
			if(cache[i][i]){
				counts[i][i] = 1;
			}else{
				counts[i][i] = 0;
			}
		}
		for(int l=2;l<=act.length;++l){
			//l dictates the length of the substring to be considered
			
			for(int i=0; i<(act.length -l);++i){
				int count = 0;
				//i dictates the start index of the substring
				int j = i+l-1; //j dictates the end index of the substring
				for(int k = i; k<j;++k){
					//k spans through the substring bounded by i and j and breaks it into 2. i...k, k+1...j;
					//both will be directly available in the cache as they will be precalculated
					boolean left = cache[i][k];
					boolean right = cache[k+1][j];
					boolean res =false;
					char op = ops[i];
					switch(op){
					case '&':
						res = left & right;
						break;
					case '^':
						res = left ^ right;
						break;
					case '|':
						res = left | right;
						break;
					}
					if(res){
						count += counts[i][k] + counts[k+1][j];
					}
				}
				counts[i][j] = count;
			}
		}
		System.out.println(counts[0][len-1]);
	}
}
