package Week1.day2;

import java.util.Arrays;

public class SecondLargest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = { 3, 2, 11, 4, 6, 7 };

		Arrays.sort(data);
		for (int i = 0; i < data.length; i++) {
			System.out.println("Sorted Values are:" + data[i]);

		}
		int length = data.length;
		System.out.println("Second largest value is:" + data[length - 2]);

	}

}
