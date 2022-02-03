package Week3.day2;

import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String text ="We learn java basics as part of java sessions in java week1";	
		 String[] split = text.split(" ");
		 int length = split.length;
		 Set<String> dup=new LinkedHashSet<String>();
		 for (int i = 0; i < length; i++) {
			 dup.add(split[i]);
			
		}
		 System.out.println(dup);
	}

}
