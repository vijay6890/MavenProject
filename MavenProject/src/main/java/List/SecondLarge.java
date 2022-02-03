package List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SecondLarge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] data= {3,2,11,4,6,7};
		List<Integer > lar=new ArrayList<Integer>();
		for (int i = 0; i < data.length; i++) {
			lar.add(data[i]);
			
		}
		Collections.sort(lar);
		int size = lar.size();
		System.out.println(lar.get(size-2));

	}

}
