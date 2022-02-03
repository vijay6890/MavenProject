package Week3.day2;

import java.util.HashMap;
import java.util.Map;

public class Occurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str="PayPal";
		Map<Character, Integer> occur=new HashMap<Character, Integer>();
		char[] charArray = str.toCharArray();
		for (char eachchar: charArray) {
			if(occur.containsKey(eachchar))
			{
				occur.put(eachchar, occur.get(eachchar)+1);
			}
			else
			{
				occur.put(eachchar, 1);
			}
			
		}
		System.out.println(occur);

	}

}
