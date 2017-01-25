package Incomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class EqualChocolates {
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
		// TODO Auto-generated method stub
		// https://www.hackerrank.com/challenges/equal
		int testcases = getNextInt();
		long start = System.currentTimeMillis();
		for(int i =0; i<testcases; ++i){
			int numInterns = getNextInt();
			int[] currentDistribution = getNextIntegerArray();
//			System.out.println(normalizeGreedy(numInterns, currentDistribution));
			System.out.println(normalizeDynamic(numInterns, currentDistribution));
		}
		long end = System.currentTimeMillis();
		System.out.println("time taken = " + (end - start));
	}

	private static int normalizeDynamic(int numInterns,int[] currentDistribution) {
		int[] possibleAdditions = {1,2,5};
		//sort currentDistribution once. 
		Arrays.sort(currentDistribution);
		
		int[] cache = new int[numInterns];
//		int[] max = new int[numInterns];
//		max[0] = currentDistribution[0];
		
		//if there is only one intern to be considered then moves would be zero
		cache[0] = 0;
		
		//if there are two interns, 
		for(int i=1; i<numInterns; ++i){
			int diff = currentDistribution[i] - currentDistribution[i-1];
			int moves = 0;
			while(diff != 0){
				if(diff >=5){
					diff -= 5;
					for(int k=i+1; k<numInterns; ++k){
							currentDistribution[k] += 5;
					}
					++moves;
					continue;
				}
				if(diff >=2){
					diff -=2;
					for(int k=i+1; k<numInterns; ++k){
						currentDistribution[k] += 2;
					}
					++moves;
					continue;
				}
				if(diff >=1){
					diff -=1;
					for(int k=i+1; k<numInterns; ++k){
						currentDistribution[k] += 1;
					}
					++moves;
					continue;
				}
			}
			cache[i] = cache[i-1] + moves;
		}

		
		return cache[cache.length-1];
	}

	private static int normalizeGreedy(int numInterns, int[] currentDistribution) {
		
		int[] possibleAdditions = {1,2,5};
		//always pick the last element and try to add either 1 or 2 or 5, calculate the average and see if the average is closer to this element or not
		int moves = 0;
		while(true){
			//if all elements are same, then break.
			int sameNumber = currentDistribution[0];
			boolean allEqual = true;
			for(int i=0; i<currentDistribution.length; ++i){
				if(currentDistribution[i] == sameNumber){
					continue;
				}else{
					allEqual = false;
					break;
				}
			}
			if(allEqual){
				break;
			}
			//if all elements are not the same then proceed with sorting, calculating average and then normalizing it
			Arrays.sort(currentDistribution);
			int lastElement = currentDistribution[currentDistribution.length -1];
			int bestPossibleAddition = -1;
			float averageDiff = Float.MAX_VALUE;
			for(int i: possibleAdditions){
				//adding 
				for(int j=0; j<currentDistribution.length -1; ++j){
					currentDistribution[j] += i; 
				}
				//calculating the average
				float tempAverage = 0;
				for(int j=0; j<currentDistribution.length -1; ++j){
					tempAverage += currentDistribution[j]; 
				}
				tempAverage = tempAverage/(currentDistribution.length -1);
				tempAverage = Math.abs(tempAverage - lastElement) ;
				if(tempAverage < averageDiff){
					bestPossibleAddition = i;
					averageDiff = tempAverage;
				}
				//subtracting the number to take out the effect before checking for next step
				for(int j=0; j<currentDistribution.length -1; ++j){
					currentDistribution[j] -= i; 
				}
			
			}
			//after all possibilities are checked, now adding the best addition
			for(int i=0; i<currentDistribution.length -1; ++i){
				currentDistribution[i] += bestPossibleAddition;
			}
			
			
			++moves;
		}
	
		return moves;
	}
}
