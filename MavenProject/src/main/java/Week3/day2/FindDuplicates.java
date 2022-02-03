package Week3.day2;

import java.util.HashSet;
import java.util.Set;

public class FindDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] number= {10,24,32,64,85,37,12,12};
		boolean bduplicates=false;
		Set<Integer> num=new HashSet<Integer>();
		for (int i = 0; i < number.length; i++) {
			if(num.add(number[i])==false)
			{
				System.out.println("Duplicate value is "+number[i]);
				bduplicates=true;
				break;
			}
		}
		if(bduplicates==false)
		{
			System.out.println("All values are unique");
		}
		}

}
