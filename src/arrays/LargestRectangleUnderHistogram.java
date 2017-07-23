package arrays;

import java.util.*;

public class LargestRectangleUnderHistogram {
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
		int hist[] = {6, 2, 5, 4, 5, 1, 6};
		System.out.println("method1:" + method1(hist));
		System.out.println("method2:" + method2(hist));
	}
	
	private static int method2(int[] hist){
		//this method would use stack to keep track of local min on either side for an element and hence would work
		Stack<Integer> s = new Stack<>();
		int max = Integer.MIN_VALUE;
		for(int i=0; i<hist.length; ++i){
			if(s.isEmpty())
				s.push(hist[0]);
			else{
				if(hist[s.peek()]<hist[i]){
					s.push(i); //always put the greater element on the top so that when we pop, we know that the next element in the stack is the local min to the left of this element
				}else{
					while(!s.isEmpty() && hist[s.peek()]>hist[i] ){
						//pop
						int elem = hist[s.pop()];
						int leftIndex;
						if(s.isEmpty()){
							leftIndex = i;
						}else{
							leftIndex = s.peek();
						}
						int rightIndex = i;
						int total = elem*(rightIndex-leftIndex);
						max = Math.max(total,max);
					}
					s.push(i);
				}
			}
		}
		return max
				;
	}
	
	
	private static int method1(int[] hist){
		//this methos wouldnt work because this calculates the global min to the left and right of the current element, where as we need local mins
		//preprocess left and right mins
		int[] left = new int[hist.length];
		int[] right = new int[hist.length];
		left[0] = 0;
		for(int i=1; i<hist.length; ++i){
			if(hist[i]<hist[left[i-1]]){
				left[i] = i;
			}else{
				left[i] = left[i-1];
			}
		}
		right[hist.length-1] = hist.length-1;
		for(int i=hist.length-2; i>=0;--i){
			if(hist[i]<hist[right[i+1]]){
				right[i] = i;
			}else{
				right[i] = right[i+1];
			}
		}
		int max = Integer.MIN_VALUE;
		//now use left and right to calculate the actual area
		for(int i=0; i<hist.length; ++i){
			int leftIndex = left[i];
			int rightIndex = right[i];
			int totalWidth ;
			if(leftIndex == rightIndex){
				totalWidth = 1;
			}else{
				totalWidth = rightIndex - leftIndex;
			}
			int total = hist[i] * totalWidth;
			max = Math.max(max,total);
		}
		return max;
	}
}
