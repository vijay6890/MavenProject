package Week3.day2;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MissingElementsList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] arr = { 1, 2, 3, 4, 7, 6, 8 };
		Set<Integer> missing = new LinkedHashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			missing.add(arr[i]);
		}
		for (int i = 1; i < arr.length; i++) {

			if (missing.contains(i) == false) {

				System.out.println(i);
				break;

			}
		}

	}

}
