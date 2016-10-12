package DynamicProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WeightedJobScheduling {
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
		int numOfJobs = getNextInt();
		List<Job> jobs = new ArrayList<>();
		int[] profits = new int[numOfJobs];
//		Job[] jobs = new Job[numOfJobs];
		for(int i=0; i<numOfJobs; ++i){
			jobs.add(new Job(getNextIntegerArray()));
		}
		//sort all the jobs based on the start time.
		Collections.sort(jobs, new JobComparator());
		//using bottom up approach, build answers 
		for(int i=0; i<numOfJobs; ++i){
			if(i==0){
				profits[i] = jobs.get(0).profit;
				continue;
			}
			//search for all the jobs before this job that meets the below 2 conditions
			//1. the end time of that job should be lesser than the start time of the current job
			//2. calculate the profits till that point and sum it with the current profit to see if that is the max profit that can be made
			int maxProfit = Integer.MIN_VALUE;
			Job currentJob = jobs.get(i);
			for(int j=0; j<i;++j){
				if(jobs.get(j).end <= currentJob.start){
					int profitCache = profits[j];
					int currentProfit = currentJob.profit;
					if((profitCache + currentProfit)> maxProfit){
						maxProfit = (profitCache + currentProfit);
					}
				}else{
					continue;
				}
			}
			profits[i] = maxProfit;
		}
		int globalMaxProfit = Integer.MIN_VALUE;
		for(int i=0; i<profits.length; ++i){
			if(globalMaxProfit < profits[i]){
				globalMaxProfit = profits[i];
			}
		}
		System.out.println(globalMaxProfit);
	}
}

class Job{
	int start;
	int end;
	int profit;
	public Job(int[] params){
		start = params[0];
		end = params[1];
		profit = params[2];
	}
	@Override
	public String toString() {
		return "start:" + start + " end:" + end + " profit:"+ profit;
	}
	
}

class JobComparator implements Comparator<Job>{
	//comparator implementation to sort based on start times of Jobs
	@Override
	public int compare(Job j1, Job j2) {
		if(j1.start > j2.start){
			return 1;
		}
		return -1;
	}
	
} 