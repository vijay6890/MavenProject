package Samples;

import java.util.Arrays;

public class Anag {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text1 = "stops";
		String text2 = "potse";
		int count=0;

if(text1.length()==text2.length())
{
	char[] charArray = text1.toCharArray();
	char[] charArray2 = text2.toCharArray();
	
	Arrays.sort(charArray);
	Arrays.sort(charArray2);
	
	for (int i = 0; i < charArray.length; i++) {
		

			
			if(charArray[i]!=charArray2[i])
			{
				count++;
			}
	}
			
			if(count>0)
			{
				System.out.println("Two String are not matching");
			}
			else
				
			{
				System.out.println("Two Strings are matching");
			}
			
		
		
	
	
	
}
else
{
	System.out.println("Length is Mismatching");
}
	}

}
