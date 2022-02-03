package Week1.day2;

public class Occurence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "Welcome to Chennai";
		int count = 0;
		char chararray[] = str.toCharArray();
		for (int i = 0; i < chararray.length; i++) {
			{
				if (chararray[i] == 'e') {
					count++;
				}
			}

		}
		System.out.println(count);
	}

}
