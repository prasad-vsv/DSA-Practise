package arrays;

import java.util.*;

public class SearchInIncreasingThenDecreasingElements {
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
		// this question is similar to search in sorted rotated array
		// find the pivot first and then break the array into 2 parts. left part
		// will be in increasing order and right side will be decreasing order
		int arr[] = { 30, 40, 50, 20, 10 };
		int key = 10;
		/*for(int i=0; i<; ++i){
			
		}*/
		System.out.println(search(arr, key));
	}

	private static int search(int[] in, int key) {
		int pivotIndex = findPivot(in, 0, in.length - 1);
		if (pivotIndex == -1) {
			// either it is totally increasing or totally decreasing
			if (in[0] > in[in.length - 1]) {
				return binarySearchDecreasing(in, 0, in.length - 1, key);
			} else {
				return binarySearch(in, 0, in.length - 1, key);
			}
		}
		// check if pivot is the element
		if (in[pivotIndex] == key) {
			return pivotIndex;
		}
		//left part will be increasing order and right part will be in decreasing order
		if(in[0]<key && in[pivotIndex-1] > key){
			return binarySearch(in,0,pivotIndex-1,key);
		}
		else{
			//that means the element must exist in the right part or -1 if it doesnt
			return binarySearch(in, pivotIndex+1,in.length-1,key);
		}
	}
	
	private static int findPivot(int[] in, int start, int end){
		if(end <start){
			return -1;
		}
		int mid = (start+end)/2;
		if((in[mid] >in[mid+1]) && (in[mid]<in[mid-1])&& (in[mid+1] > in[end]) && (in[start] <in[mid-1])){
			//this can happen only when mid is pivot. right part is in descending order, left part is in ascending order
			return mid;
		}
		//now if the left part is in ascending order, then the pivot exists in the right side
		if(in[mid]>in[mid-1] && in[start]<in[mid-1] ){
			return findPivot(in,mid+1,end);
		}
		return findPivot(in,start,mid-1);
		
	}

	private static int binarySearch(int[] in, int start, int end, int key) {
		if (end < start) {
			return -1; // element doesnt exist
		}
		int mid = (start + end) / 2;
		if (key == in[mid]) {
			return mid;
		}
		if (in[mid] > key) {
			// search in the left part
			return binarySearch(in, start, mid - 1, key);
		}
		return binarySearch(in, mid + 1, end, key);
	}

	private static int binarySearchDecreasing(int[] in, int start, int end,
			int key) {
		if (end < start) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (key == in[mid]) {
			return mid;
		}
		if (in[mid] < key) {
			// search in the left part
			return binarySearchDecreasing(in, start, mid - 1, key);
		}
		return binarySearchDecreasing(in, mid + 1, end, key);
	}
}
