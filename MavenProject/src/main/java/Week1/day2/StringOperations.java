package Week1.day2;

public class StringOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String str = "Veeravel87";
		String str2 = "veeravel";
		// String str3=new String("testleaf");
		// String str4=new String("Testleaf");
		// Length of the String
		int length = str.length();

		System.out.println("Lenth of the Text is :" + length);
		// Index Position of Character
		char charAt = str.charAt(4);
		System.out.println("4th character is :" + charAt);
		// Index of the Particular Character
		int indexOf = str.indexOf('e');
		System.out.println("index of e is :" + indexOf);
		// Last Index of the Specific Character
		int lastIndexOf = str.lastIndexOf('e');
		System.out.println("lastindex of e is :" + lastIndexOf);
		// Convert into Char array
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			System.out.println("Char array[" + i + "]is :" + charArray[i]);
		}
		String[] split = str.split(" ");
		for (int i = 0; i < split.length; i++) {
			System.out.println("Word Split :" + split[i]);

		}
		String substring = str.substring(5);
		System.out.println("SubString is :" + substring);
		String substring2 = str.substring(3, 8);
		System.out.println("SubString 2 is" + substring2);
		String replace = str.replace('e', '@');
		System.out.println("Replaced String " + replace);
		String replaceAll = str.replaceAll("\\D", "");
		System.out.println("ReplaceAll :" + replaceAll);
		if (str.equals(str2)) {
			System.out.println("Content are Equal");
		} else {
			System.out.println("Content are not Equal");
		}
	}

}
