package Week2.Day2;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class PrintDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[]= {14,12,13,11,15,14,18,16,17,19,18,17,20};
		Set<Integer> dup=new LinkedHashSet<Integer>();
		for (int i = 0; i < arr.length; i++) {
			
			//dup.add(arr[i]);
			if(dup.add(arr[i])==false)
					{
				   System.out.println(arr[i]);
					}
			
		}
		
		

	}

}
