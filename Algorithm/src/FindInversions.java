import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * This algorithm uses a divide and conquer method to 
 * computer a number of inversions in an array.
 * Inversions means in a array, the item in the previous
 * slot is bigger than item in the later slot
 * The time complexity of brute-force is O(n2)
 * The time complexity of divide and conquer is O(nlogn)
 */
public class FindInversions {
	private static long count = 0;
	public static void main(String[] args) throws FileNotFoundException {		
		int[] input = new int[100000];
		File file = new File("IntegerArray.txt");
		Scanner scan = new Scanner(file);
		int i = 0;
		while (scan.hasNext()) {
			input[i] = scan.nextInt();
			i++;
		}
		scan.close();
		find(0, 99999, input);
		System.out.print(count);
	}
	
	private static int[] find(int start, int end, int[] input) {
		if (start == end) {
			int[] ret =  {input[start]};
			return ret;
		}
		int mid = start + (end - start) / 2;
		
		int left[] = find(start, mid, input);
		int right[] = find(mid + 1, end, input);
		
		int[] total = new int[left.length + right.length];
		int l = 0;
		int r = 0;
		int i = 0;
		while (l < left.length && r < right.length) {
			if (left[l] <= right[r]) {
				total[i] = left[l];
				l++;
			} else {
				total[i] = right[r];
				count += left.length - l;
				r++;
			}
			i++;
		}	
		if (l == left.length) {
			System.arraycopy(right, r, total, i, right.length - r);
		}
		if (r == right.length) {
			System.arraycopy(left, l, total, i, left.length - l);
		}
		return total;
	}
}
