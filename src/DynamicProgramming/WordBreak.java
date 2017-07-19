package DynamicProgramming;

import java.util.*;

public class WordBreak {
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
		Set<String> dict = new HashSet<>();
		String dictionary[] = {"mobile","samsung","sam","sung","man","mango",
                "icecream","and","go","i","like","ice","cream"};
		for(String s : dictionary){
			dict.add(s);
		}
		System.out.println(wordBreak(dict,""));
	}
	
	private static boolean wordBreak(Set<String> dictionary, String in){
		//The idea is see if the input string is directly present in dictionary
		//if not consider breaking it and then check if both the parts belong in the dictionary
		if("".equals(in)){
			return true;
		}
		if(dictionary.contains(in)){
			return true;
		}
		boolean[][] dp = new boolean[in.length()][in.length()];
		//to figureout if a substring exists in the set, we need to follow top down dp approach to avoid recalculations
		//fixing the length
		for(int l=1; l<=in.length();++l){
			//fixing the starting point
			for(int s=0; s<=in.length()-l;++s){
				//fixing the end index based on start index and the lenght
				int e = s+l-1; //inclusive
				String sub = in.substring(s,e+1);
				//check if the string is directly present in the dictionary
				if(dictionary.contains(sub)){
					dp[s][e] = true;
				}else{
					//try to break the string at every possible index and check if both the parts exist in the dictionary
					for(int i=s+1; i<e; ++i){
						if(dp[s][i-1] && dp[i][e]){
							//both left and right are valid words
							dp[s][e] = true;
							break;
						}
					}
				}
			}
		}
		
		
		return dp[0][in.length()-1];
		
	}
}
