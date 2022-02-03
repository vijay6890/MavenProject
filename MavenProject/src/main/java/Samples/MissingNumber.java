package Samples;

public class MissingNumber {

	
	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 7, 6, 8 };
		int count=0;
		for (int i = 1; i < arr.length; i++) {
			if(i!=arr[i-1])
			{
				System.out.println(i);
			}
			
		
	}}
}
