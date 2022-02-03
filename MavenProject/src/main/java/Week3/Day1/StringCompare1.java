package Week3.Day1;

public class StringCompare1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String String1="I am Learning Java" ;
	String  String2="I am learning java?";
	if(String1==String2)
	{
		System.out.println("Strings are Equal");
	
	}
	else if (String1.equals(String2)) {
		System.out.println("Strings are Equal");
		
	}
	else if(String1.equalsIgnoreCase(String2))
		System.out.println("Strings are Equal");
	else
	{
		System.out.println("Strings are Not Equal");
	}

		
	}

}
