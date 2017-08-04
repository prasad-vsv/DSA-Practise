package DynamicProgramming;

import java.util.*;

public class MaximumLengthChainOfPairs {
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
		//http://www.geeksforgeeks.org/dynamic-programming-set-20-maximum-length-chain-of-pairs/
		// This is a variation of Longest Increasing Subsequence
		Pair arr[] = new Pair[] {new Pair(5,24), new Pair(39,60), new Pair(15,28),
                new Pair (27, 40), new Pair(50, 90)};
		//sort only if the order of pairs in the final set doesnt matter
		Arrays.sort(arr,new PairComparator());
		
		int[] dp = new int[arr.length];
		//base case: first element can always be considered as a unique set
		dp[0] = 1;
		
		for(int i=1; i<arr.length;++i){
			Pair p = arr[i];
			int max = Integer.MIN_VALUE;
			for(int j=0; j<i;++j){
				Pair temp = arr[j];
				if(temp.b < p.a && max < dp[j] ){
					max = dp[j];
				}
			}
			dp[i] = max + 1; //considering the current pair
		}
		System.out.println(dp[arr.length-1]);
	}
}

class Pair{
	Integer a;
	Integer b;
	
	public Pair(int _a, int _b){
		a = _a;
		b = _b;
	}
	
	public String toString(){
		return a+":" +b;
	}
}

class PairComparator implements Comparator<Pair>{

	@Override
	public int compare(Pair o1, Pair o2) {
		return o1.a.compareTo(o2.a);
	}
	
}