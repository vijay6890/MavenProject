package Week1.day2;

public class SumOfDigitsFromString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text = "Tes12Le79af65";
		int n, sum = 0;
		String str = text.replaceAll("\\D", "");
		System.out.println(str);
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {

			n = Character.getNumericValue(charArray[i]);
			sum = sum + n;

		}
		System.out.println(sum);
	}

}
