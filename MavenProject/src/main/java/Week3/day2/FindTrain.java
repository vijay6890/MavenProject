package Week3.day2;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FindTrain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<String> trainnames=Arrays.asList("Pandian","Vaigai","Seran","Podhigai","Nellai");
		Collections.sort(trainnames);
		System.out.println("Sorted list"+trainnames);
		          int size = trainnames.size();
		            System.out.println(trainnames.get(trainnames.size()-2));
	}

}
