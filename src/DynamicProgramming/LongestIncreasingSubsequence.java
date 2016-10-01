package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
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

	public static void main(String[] args) {
		int[] input = getNextIntegerArray();
		LIS[] output = new LIS[input.length];
		for(int i=0;i<input.length;++i){
			int context = input[i];
			if(i==0){
				output[0] = new LIS(0);
				output[0].elementsIncluded.add(input[0]);
			}else{
				int currentMax = -1;
				int index = -1;
				for(int j=0; j<i;++j){
					if(output[j].largestElement <= context && output[j].elementsIncluded.size() > currentMax){
						index = j;
						currentMax = output[j].elementsIncluded.size();
					}
				}
				LIS temp = output[i] = new LIS();
				temp.elementsIncluded.addAll(output[index].elementsIncluded);
				temp.elementsIncluded.add(context);
				temp.largestElement = context;
			}
		}
		int globalMax = -1;
		int index = -1;
		for(int k=0; k< output.length; ++k){
			if(output[k].elementsIncluded.size() > globalMax ){
				index = k;
				globalMax = output[k].elementsIncluded.size();
			}
		}
		System.out.println(output[index]);
	}
}

class LIS{
	List<Integer> elementsIncluded;
	int largestElement;
	public LIS(){
		elementsIncluded = new ArrayList<>();
		largestElement = -1;
	}
	public LIS(int largest){
		elementsIncluded = new ArrayList<>();
		largestElement = largest;
	}
	@Override
	public String toString() {
		String _out = "max :"+ largestElement+ "-----";
		StringBuilder out = new StringBuilder();
		for(Integer i : elementsIncluded){
			out.append(i).append(" ");
		}
		 
		_out += "elements:" +out.toString(); 
		return _out
				;
	}
	
	
}
