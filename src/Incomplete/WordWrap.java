package Incomplete;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordWrap {
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
		int[] wordLength = getNextIntegerArray();
		int length = getNextInt();
		
		//calculate the totalLength of string
		int totalLength = 0;
		for(int i=0; i<wordLength.length; ++i){
			totalLength += wordLength[i];
		}
		
		//the idea is to consider each word start as a potential place for putting a line break 
		//lets build cost cache for each length before this word using bottom up approach of DP and so the final cost would be this cost + cost of the words after this line break
		int[] cache = new int[totalLength +1];
		for(int i=0; i<=totalLength;++i){
			if(i==0){
				cache[i] =0;
				continue;
			}
			//extracting the subset of numbers that matter at this size level
			List<Integer> temp = new ArrayList<>();
			int tempSize =0;
			for(int j=0; j< wordLength.length; ++j){
				if(tempSize+wordLength[j] <= i){
					temp.add(wordLength[j]);
//					temp.add(1); //adding a space after each word
					tempSize += wordLength[j];
				}else{
					if(i-tempSize >0){
						temp.add(i-tempSize);
					}
					break;
				}
			}
			
			//if total length of string is less than one line size, then everything should be considered in one go
			
			
			//calculate the sum of lengths before this element
			int fullLength = 0;
			int fullWords = 0;
			for(int k=0; k<temp.size(); ++k){
				fullLength += temp.get(k);
				++fullWords;
			}
			if(fullLength < length){
				cache[i] = (int)Math.pow(length-fullLength-fullWords+1, 3);
			}
			else{
				int min = Integer.MAX_VALUE;
				for(int j=temp.size()-1; j>=0;){ 
					//assume that at this point we are inserting a line break and then calculate the cost
					int lengthAfter =0;
					for(int k=j; k<temp.size(); ++k){
						lengthAfter += temp.get(k);
					}
					if(lengthAfter > length ){
						break;
					}
					int spacesInLastLine = length - lengthAfter;
					//calculate the sum of lengths before this element
					int lengthBefore = 0;
					int numberOfWordsBefore = 0;
					for(int k=0; k<j; ++k){
						lengthBefore += temp.get(k);
						++numberOfWordsBefore;
					}
					//cost till this length before
					int costBefore =0;
					if(lengthBefore-numberOfWordsBefore >0){
						costBefore =+ cache[lengthBefore-numberOfWordsBefore];
					}
					int totalCost = costBefore + (int)Math.pow(spacesInLastLine, 3);
					if(min > totalCost){
						min = totalCost;
					}
					j= j-temp.get(j); //decreasing by size of one word
				}
				cache[i] = min;
			}
			
		}
		System.out.println(cache[totalLength]);
	}
}

class LineBreak{
	List<Integer> index;
	int spacesBefore;
	
	public LineBreak(){
		index = new ArrayList<>();
		spacesBefore = 0;
	}
	
	@Override
	public String toString() {
		return "index: " +index + "; spaces before:" + spacesBefore;
	}
	
}















