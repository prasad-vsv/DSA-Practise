package arrays;

import java.util.*;

public class TourAllStations {
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
		PetrolPump[] p = {new PetrolPump(4,6),new PetrolPump(6,5),new PetrolPump(7,3),new PetrolPump(4,5)};
		System.out.println(tourAllStations(p));
	}
	
	private static int tourAllStations(PetrolPump[] p){
		if(p.length == 1){
			if((p[0].petrol - p[0].distance) > 0){
				return 0;
			}else{
				return -1;
			}
		}
		//implement queue using two pointers, start and end on the same array
		int start = 0;
		int end = 1;
		int cur = p[start].petrol - p[start].distance;
		//while to tour doesnt end or petrol is not finished
		while((start<end) || (cur <0)){
			
			while(cur< 0){
				//remove the start and make the start as the next one
				cur -= p[start].petrol - p[start].distance;
				start = (start+1)%p.length; //make the next one as start
			}
			
			//keep adding the new station's net petrol
			cur += p[end].petrol -p[end].distance;
			end = (end+1)%p.length;
		}
		return start;
	}
}

class PetrolPump{
	int petrol;
	int distance;
	public PetrolPump(int a , int b){
		petrol=a;
		distance = b;
	}
}
