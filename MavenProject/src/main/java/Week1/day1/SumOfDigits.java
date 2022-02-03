package Week1.day1;

public class SumOfDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=123,sum=0,i=0,remainder;
		
		while(n>i)
		{
			 remainder=n%10;
			sum=sum+remainder;
		//	 q=n/10;
			//sum=sum+q;
			//System.out.println("Remainder value"+remainder);
			n=n/10;
			
			}
	System.out.println(sum);

	}

}
