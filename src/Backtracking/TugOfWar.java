package Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TugOfWar {
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
		// solving http://www.geeksforgeeks.org/tug-of-war/
		int[] input = getNextIntegerArray();
		int size = input.length;
		boolean even = (size%2 == 0)? true:false;
		int[] temp;
		//intiializing the dataset
		Basket b1 = new Basket(input);
		Basket b2 = new Basket();
		//solving the probelm
		solve(b1 , b2, even);
		//printing the solution
		System.out.println("final solution:");
		System.out.println("b1: " + _b1 + " with sum: " + _b1.getSum());
		System.out.println("b2: " + _b2 + " with sum: " + _b2.getSum());
		System.out.println("And the difference is " + Math.abs(_b1.getSum() - _b2.getSum()) + " and the minDiff so far is " + minDiff);
	}
	
	static int minDiff = Integer.MAX_VALUE;
	static Basket _b1, _b2;
	
	private static void solve(Basket b1, Basket b2, boolean even) {
		if(even && (b1.getSize() == b2.getSize())){
			if(minDiff > Math.abs(b1.getSum() - b2.getSum())){
				minDiff = Math.abs(b1.getSum() - b2.getSum());
				_b1 = new Basket(b1.getIntegerArray());
				_b2 = new Basket(b2.getIntegerArray());
			}
			return;
		}
		if ((!even) && Math.abs(b1.getSize()- b2.getSize()) == 1){
			//that means that the total number is odd but we have reached to a solution
			if(minDiff > Math.abs(b1.getSum() - b2.getSum())){
				minDiff = Math.abs(b1.getSum() - b2.getSum());
				_b1 = new Basket(b1.getIntegerArray());
				_b2 = new Basket(b2.getIntegerArray());
			}
			return;
		}

		int[] possibleNumbers = getPossibleNumbers(b1);
		for(int number: possibleNumbers){
			//assume that this is the correct number to be moved to the new bucket, b2 and then proceed
			b1.removeIntFromBasket(number);
			b2.addIntToBasket(number);
			//proceed to further possibilities
			solve(b1,b2,even);
			//once all possibilities are explored, get back to the original state
			b2.removeIntFromBasket(number);
			b1.addIntToBasket(number);
		}
	}

	private static int[] getPossibleNumbers(Basket b1) {
		return b1.getIntegerArray();
	}
	
}

class Basket{
	private List<String> list = new ArrayList<>();
	private int sum =0;
	
	public int addIntToBasket(int _i){
		list.add(_i + "");
		sum += _i;
		return sum;
	}
	
	public boolean hasInt(int _i){
		return list.contains(_i+"");
	}
	
	public int removeIntFromBasket(int _i){
		list.remove(_i+"");
		sum -= _i;
		return sum;
	}
	
	public int getSum(){
		return sum;
	}
	
	public int getSize(){
		return list.size();
	}
	
	public Basket(){
		
	}
	
	public Basket(int[] input){
		for(int i:input){
			addIntToBasket(i);
		}
	}
	
	public int[] getIntegerArray(){
		int[] output = new int[list.size()];
		int index = 0;
		for(Object s : list.toArray()){
			if(s instanceof String){
				int i = Integer.parseInt((String) s);
				output[index++] = i;
			}
		}
		return output;
	}
	
	public Object[] getObjectArray(){
		return list.toArray();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for(Object o: list.toArray()){
			sb.append((String)o);
			sb.append(",");
		}
		
		return sb.toString();
	}
	
	
}

























