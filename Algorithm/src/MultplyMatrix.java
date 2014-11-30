import java.util.Arrays;

/**
 * The algorithm use a divide and conquer method to calculate a product matrix
 * of two input matrix. The produce matrix is consisted by elements that in the
 * position of (i, j), it is the sum of product of the elements in the row of 
 * x[i] and the column of y[j]. 
 */

public class MultplyMatrix {

	public static void main(String[] args) {
		int[][] x = {{1, 2, 3, 4},
					{1, 2, 3, 4}, 
					{1, 2, 3, 4}, 
					{1, 2, 3, 4}};
		
		int[][] y = {{1, 2, 3, 4},
					{1, 2, 3, 4}, 
					{1, 2, 3, 4}, 
					{1, 2, 3, 4}};
		
		int[][] z = multiply(x, y);
		for (int i = 0; i < z.length; i++) {
			System.out.println(Arrays.toString(z[i]));
		}		
	}
	
	private static int[][] multiply(int[][] x, int[][] y) {
		if (x.length == 1) {
			int[][] z = new int[1][1];
			z[0][0] = x[0][0] * y[0][0];
			return z;
		}
		int n = x.length;
		int[][] z = new int[n][n];
		
		int[][] a = split(x, 0 , 0, n / 2);
		int[][] b = split(x, 0, n / 2, n / 2);		
		int[][] c = split(x, n / 2, 0, n / 2);
		int[][] d = split(x, n / 2, n / 2, n / 2);		
		int[][] e = split(y, 0 , 0, n / 2);
		int[][] f = split(y, 0, n / 2, n / 2);
		int[][] g = split(y, n / 2, 0, n / 2);
		int[][] h = split(y, n / 2, n / 2, n / 2);

		int[][] ae = multiply(a, e);
		int[][] bg = multiply(b, g);
		int[][] af = multiply(a, f);
		int[][] bh = multiply(b, h);
		int[][] ce = multiply(c, e);
		int[][] dg = multiply(d, g);
		int[][] cf = multiply(c, f);
		int[][] dh = multiply(d, h);
		
		int[][] topLeft = add(ae, bg);
		int[][] topRight = add(af, bh);
		int[][] bottomLeft = add(ce, dg);
		int[][] bottomRight = add(cf, dh);
		
		
		join(z, topLeft, 0, 0);
		join(z, topRight, 0, n / 2);
		join(z, bottomLeft, n / 2, 0);
		join(z, bottomRight, n / 2, n / 2);
				
		return z;		
	}
	
	private static int[][] split(int[][] p, int x, int y, int len) {
		int[][] c = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				c[i][j] = p[i + x][j + y];
			}
		}
		return c;
	}
	
	private static int[][] add(int[][] x, int[][] y) {
		int n = x.length;
		int[][] r = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				r[i][j] = x[i][j] + y[i][j];
			}
		}
		return r;
	}
	
	private static void join(int[][] p, int[][] c, int x, int y) {
		int n = c.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				p[x + i][y + j] = c[i][j];
			}
		}
	}
}
