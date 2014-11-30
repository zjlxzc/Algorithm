import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;
/**
 * This algorithm implement karger's mincut of graph
 * Mincut means split the graph into two parts such that the cross
 * edges between these two parts is the minimum
 * The algorithm only have a 1/n2 possibility of success, but compares
 * to the total possible of 2 to the n, its better
 *
 */

public class MinCut {

	public static void main(String[] args) throws FileNotFoundException {
		
		int count = 0;
		int min = Integer.MAX_VALUE;
		while (count < 100) {
			File file = new File("kargerMinCut.txt");
			//File file = new File("minCutTest");
			Scanner scan = new Scanner(file);
			Hashtable<Integer, Vertex> table = new Hashtable<Integer, Vertex>();
			ArrayList<Vertex> vList = new ArrayList<Vertex>();
			while (scan.hasNext()) {
				String[] str = scan.nextLine().split("\\W");
				int vId = Integer.parseInt(str[0]);
				Vertex leadV = table.get(vId);
				if (leadV == null) {
					leadV = new Vertex();
					table.put(vId, leadV);
				}
				for (int i = 1; i < str.length; i++) {
					int curId = Integer.parseInt(str[i]);
					Vertex curV = table.get(curId);
					if (curV == null) {
						curV = new Vertex();
						table.put(curId, curV);
					}
					leadV.neighbors.add(curV);				
				}
				vList.add(leadV);
			}
			scan.close();

			Random rand = new Random();
			
			// stop when there are only 2 vertices in the graph
			while (vList.size() > 2) {
				
				// randomly pick two connected vertices
				Vertex v1 = vList.get(rand.nextInt(vList.size()));
				Vertex v2 = v1.neighbors.get(rand.nextInt(v1.neighbors.size()));
				
				// for every vertex in v2
				for (Vertex v : v2.neighbors) {
					if (v != v1) {	// remove self-cycle
						v1.neighbors.add(v);	// add to the v1's neighbor
						v.neighbors.add(v1);	// also need add the opposite direction
					}
					int j = 0;
					while (j < v.neighbors.size()) {	// remove v2 from its neighbors
						if (v.neighbors.get(j) == v2) {
							v.neighbors.remove(j);
						} else {
							j++;
						}
					}
				}
				vList.remove(v2);
			}
			System.out.println("The mincut is: " + vList.get(0).neighbors.size());
			min = Math.min(min, vList.get(0).neighbors.size());
			count++;	// run enough times to get the guarantee ans
		}			
		System.out.println(min);
	}
	
	
	private static class Vertex {
		public ArrayList<Vertex> neighbors;
		public Vertex() {
			this.neighbors = new ArrayList<Vertex>();
		}
	}
}
