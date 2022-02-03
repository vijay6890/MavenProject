package List;

import java.util.HashSet;
import java.util.Set;

public class Missing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] number= {2,7,5,4,9,6,7,3,10,1};
		Set<Integer> unique=new HashSet<Integer>();
		for (int i = 0; i < number.length; i++) {
			
			unique.add(number[i]);
			
		}
		System.out.println("Unique Numbers"+unique);
		for (int i = 1; i < unique.size(); i++) {
			if(unique.contains(i)==false)
				
			{
				System.out.println(i);
			}
			
		}

	}

}
