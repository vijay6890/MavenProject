package Samples;

public class Prime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int a=9,count=0;
for (int i = 1; i <=a; i++) {
	if(a%i==0)
	{
		count++;
	}
}
if(count==2)
{
	System.out.println("Number is Prime");
}
else
{
	System.out.println("Number is not Prime");
}
	
}
}