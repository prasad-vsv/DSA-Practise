package DynamicProgramming;

import java.util.Scanner;

public class MinimumNumberOfJumps {
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
		int[] values = getNextIntegerArray();
		int size = values.length;
		int[] answers = new int[size];
		for(int i=0; i<size; ++i){
			answers[i] = Integer.MIN_VALUE;
		}
		for(int i=size-1; i>=0; --i){
			//when we are already at the end of the array
			if(i==size-1){
				answers[i] = 0;
				continue;
			}
			int stepSize = values[i]; 
			//when it is not possible to move from that index
			if((stepSize == 0 )&& (i != (size-1))){
				answers[i] = Integer.MIN_VALUE; // that means that from this index we cannot move forward and we are using Integer.MAX_VALUE because it will be filtered out in the min function
				continue;
			}
			//normal cases
			if((i+stepSize) >= (size-1) ){
				answers[i] = 1;
			}else{
				int min = Integer.MAX_VALUE;
				boolean flag = false;
				for(int j=1; j<=stepSize && (i+j)<= (size-1) && answers[i+j] >0; ++j){ //answers[i+j] >0 this check is important because we are checking whether we can reach the end of the array from this element or not
					int temp = 1 + answers[i+j];
					if(temp < min){
						min = temp;
						flag = true;
					}
					
				}
				if(flag){
					answers[i] = min; // consider the answer only when we have actually found an answer
				}
				
			}
			
		}
		System.out.println(answers[0]); 
	}
}
