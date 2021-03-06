package arrays;

import java.util.*;

public class SearchInSortedRotatedArray {
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
//		int arr[] = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
		int arr[]={30, 40, 50, 20, 10};
		int key = 10;
		// there are 2 ways to go about it. one is to find pivot and then break
		// the array into two parts and do binary search on both
		// other approach is to go with binary search directly and then at every
		// step see if the part in which the sub array is sorted
		/*for(int i=0; i<=10; ++i){
			System.out.println("key is "+i + " and index is : "+pivotedBinarySearch(arr, i));
		}*/
		System.out.println(pivotedBinarySearch(arr, key));
		

	}

	private static int pivotedBinarySearch(int[] in,int key){
		int pivotIndex = findPivot(in,0,in.length-1);
		if(pivotIndex == -1){
			//that means that pivot is not there, that means simply do binary search on the entire array
			return binarySearch(in,0,in.length-1,  key);
		}
		if(in[pivotIndex] == key){
			return in[pivotIndex];
		}
		//now break the array into to parts, to the left of the pivot and to the right
		if(key >= in[0] && key <=in[pivotIndex-1]){
			//that means that the element in the left side of pivot
			return binarySearch(in,0,pivotIndex-1,key);
		}else{
			return binarySearch(in,pivotIndex+1,in.length-1,key);
		}
		
	}

	private static int findPivot(int[] in, int start, int end) {
		// this is essentially modified binary search. Instead of searching for
		// a key, we search for pivot
		// the idea is that pivot is the place at which there is a break in the
		// increasing order that starts from start
		// it can be last index of the increasing sequence or the first one
		// after the break. lets take the first one;
		if(end<start){
			return -1; //that means that there is no pivot
		}
		int mid = (start+end)/2;
		if(in[mid] > in[mid+1]){
			//this happens only if mid is pivot.i.e, the last element in the increasing sequence starting from start
			return mid;
		}
		if(in[mid]>in[start] ){
			//that means that the left part is sorted. so search in right part
			return findPivot(in,mid+1,end);
		}
		return findPivot(in,start,mid-1);
	}

	private static int binarySearch(int[] in, int start, int end, int key) {
		if (end < start) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (key == in[mid]) {
			return mid;
		}

		if (key > in[mid]) {
			return binarySearch(in, mid + 1, end, key);
		}
		return binarySearch(in, start, mid - 1, key);
	}
}
