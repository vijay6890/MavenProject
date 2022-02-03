package Week1.day2;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "madam";
		String rev = "";
		char[] charArray = str.toCharArray();
		// int length = charArray.length;

		for (int i = str.length() - 1; i >= 0; i--) {

			rev = rev + charArray[i];

		}

		if (str.equalsIgnoreCase(rev)) {
			System.out.println("It is a Palindrome");
		} else {
			System.out.println("Not palindrome");
		}
	}
}
