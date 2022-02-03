package Samples;

public class Reversestr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "feeling good";
		String str[]=test.split(" ");
		              
		//for (int i=0; i<str.length; i++) {
			     
			char ch[]=test.toCharArray();
					
				for (int j = ch.length-1; j >=0; j--) {
					
					System.out.print(ch[j]);
				}
				
				//System.out.print(" ");
					}
		
			
		
	}


