package Week1.day2;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "We learn java java week1 ";
		String[] arr = text.split(" ");
		int count = 0;
		String temp = null;
		for (int i = 0; i < arr.length; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i].equals(arr[j])) {
					temp = arr[i];
					count++;

				}

			}

		}
		if (count >= 1) {
			System.out.print(text.replace(temp, ""));
		}

	}

}
