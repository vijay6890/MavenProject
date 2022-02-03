package Week3.day2;

import java.util.HashSet;
import java.util.Set;

public class ArrayInterSection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] data1 = { 2, 4, 6, 8, 11, 12, 14, 18, 24, 32 };
		int[] data2 = { 1, 4, 7, 9, 6 };
		Set<Integer> dups = new HashSet<Integer>();
		for (int i = 0; i < data2.length; i++) {
			dups.add(data2[i]);
		}
		for (int i = 0; i < data1.length; i++) {
			if (dups.contains(data1[i])) {
				System.out.println("Intersecting data" + data1[i]);
			}

		}

	}

}
