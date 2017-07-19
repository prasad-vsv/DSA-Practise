package strings;

import java.util.*;

public class RearrangeWithNoAdjacentCharacters {
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
		String in = getNextLine();
		Map<Character, Char> map = new HashMap<>();
		for(char c : in.toCharArray()){
			if(!map.containsKey(c)){
				map.put(c,new Char(c));
			}else{
				Char temp = map.get(c);
				temp.frequency++;
				map.put(c, temp);
			}
		}
		PriorityQueue<Char> pq  = new PriorityQueue<>(in.length(), new CharComparator());
		for(char c:map.keySet()){
			pq.add(map.get(c));
		}
		Char prev = null;
		StringBuilder sb = new StringBuilder("");
		while(!pq.isEmpty()){
			Char temp = pq.poll();
			if(temp.frequency == 0){
				//error case
				continue;
			}
			sb.append(temp.c);
			--temp.frequency;
			
			//add back prev and put it in pq before assigning temp to prev
			if(prev != null && prev.frequency >0){
				pq.add(prev);
			}			
			prev = temp;
		}
		if(sb.length() != in.length()){
			System.out.println("IMPOSSIBLE");
		}else{
			System.out.println(sb.toString());
		}
		
		
	}
}

class Char{
	char c;
	Integer frequency;
	
	public Char(char c){
		this.c = c;
		this.frequency = 1;
	}
	
	@Override
	public int hashCode(){
		return (c+"").hashCode();
	}
}

class CharComparator implements Comparator<Char>{

	@Override
	public int compare(Char o1, Char o2) {
		return o2.frequency.compareTo(o1.frequency);
	}
	
}