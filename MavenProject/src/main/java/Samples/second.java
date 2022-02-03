package Samples;

import java.util.Arrays;

public class second {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data = { 3, 2, 11, 4, 6, 7 };
	Arrays.sort(data);
	int length = data.length;
	System.out.println("Second Largest:"+data[length-2]);
	

	}

}
