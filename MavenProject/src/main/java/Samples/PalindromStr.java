package Samples;

public class PalindromStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str="Mayrm";
		String rev="";
	
		
	   char[] charArray = str.toCharArray();
	   for (int i =  charArray.length-1; i >=0; i--) {
		   
	      rev=rev+charArray[i];
		
	}
	    
	      if(str.equalsIgnoreCase(rev))
	      {
	    	  System.out.println("It is a Palindrom");
	      }
	      else
	      {
	    	  System.out.println("It is not Palindrome");
	      }

	}

}
