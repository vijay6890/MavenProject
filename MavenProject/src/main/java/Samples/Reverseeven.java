package Samples;

public class Reverseeven {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "I am a software tester";
		
		String str[]=test.split(" ");
		for (int i = 0; i < str.length; i++) {
			
			if(i%2==0) {
				char[] charArray = str[i].toCharArray();
			
			  for (int j =charArray.length-1; j>=0; j--) {
				  System.out.print(charArray[j]);
				
			}
			  System.out.print(" ");
			}
			else
			{
				System.out.print(str[i]+" ");
			}
			
		}

}
}
