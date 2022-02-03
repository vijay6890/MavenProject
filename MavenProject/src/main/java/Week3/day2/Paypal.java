package Week3.day2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Paypal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "PayPal";
		char[] charArray = str.toCharArray();
		Set<Character> charset = new HashSet<Character>();
		Set<Character> dupcharset = new HashSet<Character>();
		// .add(charArray[1]);
		for (int i = 0; i < charArray.length; i++) {

			if (charset.add(charArray[i]) == false) {

				dupcharset.add(charArray[i]);
			} else {
				charset.add(charArray[i]);
			}

		}

		System.out.println(charset);
		System.out.println(dupcharset);
		for (Character character : dupcharset) {
			// if (charset.add(character) == false) {
			charset.remove(character);

			// }

		}

		System.out.println("After Removing Duplicate" + charset);

	}

}
