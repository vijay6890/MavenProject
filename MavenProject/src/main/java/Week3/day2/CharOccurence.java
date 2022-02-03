package Week3.day2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CharOccurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String companyName="Amazon India Development Centre";
		char[] charArray=companyName.toCharArray();
		Map<Character, Integer> map=new HashMap<Character, Integer>();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		for (int i = 0; i < charArray.length; i++) {
			if(map.containsKey(charArray[i]))
				map.put(charArray[i], map.get(charArray[i])+1);
			else
				map.put(charArray[i], 1);
			
		}
		System.out.println(map);

	}

}
