package Week1.day2;

public class FindInterSection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr1[] = { 3, 2, 11, 4, 6, 7 };
		int arr2[] = { 1, 8, 2, 4, 9, 7 };
//String s1="";
//int count=0;

		for (int i = 0; i < arr1.length; i++) {

			for (int j = 0; j < arr2.length; j++) {

				if (arr1[i] == arr2[j]) {
					System.out.println("Common values in Both the arrays are:" + arr1[i]);
					// count++;

				}

			}
		}

	}
}
