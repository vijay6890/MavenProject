package Week3.Day1;

public class StringReplace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sentence = "I am working with Java8";
		CharSequence subSequence = sentence.substring(5, 15);
		System.out.println(subSequence);
		
	   String replace = sentence.replace("8", "11");
	   System.out.println(replace);
	
		
	}

}

