package Samples;

public class Types {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "$$ Welcome to 2nd Class of Automation $$ ";
		int digit=0,spacechar=0,letter=0;
		char[] charArray = test.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if(Character.isDigit(charArray[i]))
			{
				digit++;
			}
			if(Character.isSpaceChar(charArray[i]))
			{
				spacechar++;
			}
			if(Character.isLetter(charArray[i]))
			{
				letter++;
			}
		}
		System.out.println("No of Digits"+digit);
		System.out.println("No of Space:"+spacechar);
		System.out.println("No of letters"+letter);
	}

}
