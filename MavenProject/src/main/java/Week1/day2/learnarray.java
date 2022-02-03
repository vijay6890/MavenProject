package Week1.day2;

import java.util.Arrays;

public class learnarray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int arr[] = {100,49,398,34,1,267};
		int length =arr.length;
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
			
			}
		Arrays.sort(arr);
		for (int j = 0; j < arr.length; j++) {
			System.out.println(arr[j]);
			
			}
		System.out.println("Second largest number"+arr[length-2]);
		System.out.println("Second small number"+arr[1]);
		
	}
	

}
