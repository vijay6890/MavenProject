package Week1.day2;

import java.util.Arrays;

public class Anagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text1 = "stops";
		String text2 = "potss";

		if (text1.length() == text2.length()) {
			char[] charArray = text1.toCharArray();
			char[] charArray2 = text2.toCharArray();
			Arrays.sort(charArray);
			Arrays.sort(charArray2);

			if (Arrays.equals(charArray, charArray2)) {
				System.out.println("Both are Equal");
			} else {
				System.out.println("Not Equal");
			}
		} else {
			System.out.println("Length is not match");
		}
	}
}
