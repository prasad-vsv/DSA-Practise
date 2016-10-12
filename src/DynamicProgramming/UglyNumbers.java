package DynamicProgramming;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UglyNumbers {
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
	
	public static void main(String[] a){
		int num = getNextInt();
		Set<Integer> cache = new HashSet<Integer>();
		int lastAdded = -1;
		cache.add(1);
		cache.add(2);
		cache.add(3);
		cache.add(4);
		cache.add(5);
		for(int i=6;cache.size()<num;++i){
			int div2 = i%2 == 0? i/2:-1;
			int div3 = i%3 == 0? i/3:-1;
			int div5 = i%5 == 0? i/5:-1;
			if(div2!= -1 && cache.contains(div2)){
				//that means that i is divisible by 2 and the dividend is a ugly number as well => it has no other factors that 2,3 or 5
				cache.add(i);
				lastAdded = i;
			}
			if(div3!= -1 && cache.contains(div3)){
				//that means that i is divisible by 2 and the dividend is a ugly number as well => it has no other factors that 2,3 or 5
				cache.add(i);
				lastAdded = i;
			}
			if(div5!= -1 && cache.contains(div5)){
				//that means that i is divisible by 2 and the dividend is a ugly number as well => it has no other factors that 2,3 or 5
				cache.add(i);
				lastAdded = i;
			}
		}
		System.out.println(lastAdded);
		
	}
}
