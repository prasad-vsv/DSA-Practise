package DynamicProgramming;

import java.util.Scanner;

public class MaxProfitOnShares {
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
		// http://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-twice/
		//idea is to solve the sum in 2 iterations.
		//1. figure out the maximum profit that can be made at every index . we are to find start index such that we make maximum profit by selling at current index.
		//2. in second iteration, at every end index, we chose every start index before that and figure out the profit. we sum this with max profit that can be made before this start index, which we get directly from previous step
		
		int[] prices = getNextIntegerArray();
		int[] maxProfit = new int[prices.length];
		int[] ans = new int[prices.length];
		for(int i=0; i<maxProfit.length; ++i){
			if(i==0){
				maxProfit[0] = 0;
				continue;
			}
			int max = Integer.MIN_VALUE;
			//in the first iteration we consider only one stock. we consider each index as the index where we sell the stock and see how to make maximum profit
			for(int j=0; j<i; ++j){
				//let us assume that we are selling the stock at current index, i. Now, we iteratively check for all indexes assuming that they are the index where we buying the stock
				int profit = prices[i] - prices[j];
				if(profit > 0 && max < profit){
					max = profit;
				}
			}
			if(max>0){
				maxProfit[i] = max;
			}
			
		}
		
		//now in the second iteration, lets consider each index and while considering the start index, lets see how much profit we can make before the start index
		for(int i=0; i<maxProfit.length; ++i){
			int max = Integer.MIN_VALUE;
			for(int j=0;j<i;++j){
				int profit = prices[i] - prices[j];
				int finalProfit = (profit>0 ?profit:0); //will be summed up with max profit that can be made before this starting index.
				
				int temp = Integer.MIN_VALUE;
				for(int k=0;k<j;++k){
					if(maxProfit[k]> temp){
						temp = maxProfit[k];
					}
				}
				finalProfit += temp;
				if(max < finalProfit){
					max = finalProfit;
				}
			}
			if(max>0){
				ans[i] = max;
			}
		}
		
		System.out.println(ans[prices.length -1]);
	}
}
















