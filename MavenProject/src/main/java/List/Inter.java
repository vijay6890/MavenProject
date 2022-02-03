package List;

import java.util.LinkedHashSet;
import java.util.Set;

public class Inter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1= {3,2,11,4,6,7};
		int[] a2= {1,2,8,4,9,7};
		Set<Integer> inter=new LinkedHashSet<Integer>();
		for (int i = 0; i < a2.length; i++) {
			inter.add(a2[i]);
			
		}
		for (int j = 0; j < a1.length; j++) {
			if(inter.contains(a1[j]))
			{
				System.out.println(a1[j]);
			}
		}
		
	}

}
