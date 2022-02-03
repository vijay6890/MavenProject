package Week1.day2;

//import java.util.Iterator;

public class ChangeOddIndexToUpperCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String name = "veeravel";
		char[] charArray = name.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (i % 2 != 0) {
				System.out.print(Character.toUpperCase(charArray[i]));
			}

			else {
				System.out.print(charArray[i]);
			}
		}
	}

}
