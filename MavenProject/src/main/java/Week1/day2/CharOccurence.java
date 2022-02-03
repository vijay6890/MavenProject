package Week1.day2;

public class CharOccurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "welcome to chennai";
		int count = 0;

		char[] charArray = str.toCharArray();
		int length = charArray.length;
		for (int i = 0; i < length; i++) {
			if (charArray[i] == 'e')
				count++;

		}
		System.out.println(count);

	}

}
