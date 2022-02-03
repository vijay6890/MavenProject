package List;

import java.util.ArrayList;
import java.util.List;

public class MissingEle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] arr= {1,2,3,4,7,6,8};
		List<Integer>  miss=new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			miss.add(arr[i]);
		}
		for (int j = 1; j < arr.length; j++) {
			if(miss.contains(j)==false)
				System.out.println(j);
			{
				
			}
		}
			
		}
}
