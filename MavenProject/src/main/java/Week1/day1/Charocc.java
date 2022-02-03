package Week1.day1;

public class Charocc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String name="veeravel";
 char[] charArray = name.toCharArray();
 int count=0;
 for (int i = 0; i < charArray.length; i++) {
	 if(charArray[i]=='e')
	 {
		 count++;
	 }
	
}
 System.out.println("No of Occurence"+count);
	}

}
