package Week1.day2;

public class ReverseEvenWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "I am a software tester";
		String str[] = test.split(" ");
		int length = str.length;
		// char[] charArray = test.toCharArray();
		for (int i = 0; i < length; i++) {

			if (i % 2 == 0) {
				char[] charArray = str[i].toCharArray();
				int length2 = charArray.length;
				for (int j = length2 - 1; j >= 0; j--) {
					System.out.print(charArray[j]);

				}
				System.out.print(" ");
			} else {
				// char[] charArray = str[i].toCharArray();
				// int length3 = charArray.length;
				// for (int k =0; k<=length3-1; k++) {
				System.out.print(str[i] + " ");

			}

		}

	}

}
