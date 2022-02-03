package Week1.day2;

//import java.util.Iterator;

public class MissingNumbersInArray {

	int[] arr = { 1, 2, 3, 4, 7, 6, 8 };

	public void getMissingNo() {
		int length = arr.length;

		for (int i = 1; i < length; i++) {
			if (i != arr[i - 1]) {
				System.out.println(i);
				break;
			}
		}

	}

	public static void main(String args[]) {

		MissingNumbersInArray miss = new MissingNumbersInArray();
		miss.getMissingNo();

	}
}