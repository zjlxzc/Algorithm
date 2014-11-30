import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


/**
 * This algorithm implements quick sort and calculate the times of comparisons
 * The first element in the array will be selected as pivot value
 * The partition method is exactly as what is described in the video of the online
 * course Algorithm, Design and Analysis Part I
 *
 */
public class QuickSort {
	private static long count = 0;
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("QuickSort.txt");
		Scanner scan = new Scanner(file);
		int[] unsorted = new int[10000];
		int k = 0;
		while (scan.hasNext()) {
			unsorted[k] = scan.nextInt();
			k++;
		}
		scan.close();
		quickSort(unsorted, 0, unsorted.length - 1);
		System.out.println(Arrays.toString(unsorted));
		System.out.println(count);
	}
	private static void quickSort(int[] unsorted, int start, int end) {
		if (start >= end) { // consider the corner case, if start = end - 1, and already sorted then the partition
			return;			// will return start, and quick sort will have (start, start - 1) situation
		}	
		//swap(unsorted, start, end);
		int i = partitionFirst(unsorted, start, end);
		//int i = partitionLast(unsorted, start, end);
		//int i = partitionMedianOfThree(unsorted, start, end);
		count += end - start;
		quickSort(unsorted, start, i - 1);
		quickSort(unsorted, i + 1, end);		
	}
	
	// always choose the first element as pivot
	private static int partitionFirst(int[] unsorted, int start, int end) {		
		int i = start + 1; 
		int j = i;
		while (j <= end) {
			if (unsorted[j] < unsorted[start]) {
				swap(unsorted, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}
		swap(unsorted, i - 1, start);
		return i - 1;
	}
	
	// always choose the last element as pivot
	private static int partitionLast(int[] unsorted, int start, int end) {		
		int i = start; 
		int j = i;
		while (j < end) {
			if (unsorted[j] < unsorted[end]) {
				swap(unsorted, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}
		swap(unsorted, i, end);
		return i;
	}
	
	// always choose median of three as pivot
	private static int partitionMedianOfThree(int[] unsorted, int start, int end) {	
		int mid = (end - start) / 2 + start;
		int max = Math.max(Math.max(unsorted[start], unsorted[end]), unsorted[mid]);
		int min = Math.min(Math.min(unsorted[start], unsorted[end]), unsorted[mid]);
		int median = unsorted[start] + unsorted[end] + unsorted[mid] - max - min;
		
		if (median == unsorted[end]) {
			swap(unsorted, start, end);
		} else if (median == unsorted[mid]){
			swap(unsorted, start, mid);
		}
		int ret = partitionFirst(unsorted, start, end);
		return ret;
	}
	
	private static int partitionMiddle(int[] unsorted, int start, int end) {
		int mid = start + (end - start) / 2;
		int i = start; 
		int j = i;
		while (j <= end) {
			if (i == mid) {
				i++;
				continue;
			}
			if (j == mid) {
				j++;
				continue;
			}
			if (unsorted[j] < unsorted[mid]) {
				swap(unsorted, i, j);
				i++;
				j++;
			} else {
				j++;
			}
		}
		swap(unsorted, i - 1, mid);
		return i - 1;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
}
