package List;

import java.util.LinkedHashSet;
import java.util.Set;

public class Paypal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "PayPal";
		char[] charArray = str.toCharArray();
		Set<Character> charset = new LinkedHashSet<Character>();
		Set<Character> dupcharset = new LinkedHashSet<Character>();
		for (int i = 0; i < charArray.length; i++) {
			if (charset.add(charArray[i]) == false) {
				dupcharset.add(charArray[i]);

			} else {
				charset.add(charArray[i]);
			}

		}
		System.out.print(dupcharset);
		System.out.println(charset);
		for (Character eachchar : dupcharset) {
			//if (charset.add(eachchar) == false) {
				charset.remove(eachchar);
		//	}

		}
		System.out.println("After Removing Duplicate:" + charset);

	}
}