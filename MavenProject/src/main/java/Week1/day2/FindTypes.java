package Week1.day2;

public class FindTypes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "$$ Welcome to 2nd Class of Automation $$ ";
		int letter = 0, space = 0, num = 0, specialChar = 0;
		char[] charArray = test.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isLetter(charArray[i])) {

				letter++;
			} else if (Character.isDigit(charArray[i])) {
				num++;
			} else if (Character.isSpaceChar(charArray[i])) {
				space++;

			} else {
				specialChar++;
			}

		}

		System.out.println("Number of Letter" + letter);
		System.out.println("Number of Digits" + num);
		System.out.println("Number of Spaces" + space);
		System.out.println("Number of SpaecialChar" + specialChar);
	}
}
