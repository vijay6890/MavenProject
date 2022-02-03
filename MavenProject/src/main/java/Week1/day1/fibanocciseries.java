package Week1.day1;

public class fibanocciseries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int firstnum=0,secondnum=1,range=8,sum;
		System.out.print(firstnum);
		System.out.print(","+secondnum);
		
		for (int i = 1; i <range; i++) {
			
			sum=firstnum+secondnum;
			firstnum=secondnum;
			secondnum=sum;
			System.out.print(","+sum);
		}
			
		}
		

}
