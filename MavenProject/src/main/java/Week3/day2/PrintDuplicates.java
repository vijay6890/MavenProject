package Week3.day2;

import java.util.LinkedHashSet;
import java.util.Set;

public class PrintDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = { 14, 12, 13, 11, 15, 14, 18, 16, 17, 19, 18, 17, 20 };
		Set<Integer> dupli = new LinkedHashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (dupli.add(arr[i]) == false) {
				System.out.println(arr[i]);
			}

		}

	}

}
