package Week1.day1;

public class RemainderProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num=11;
		if (num%3==0&&num%5==0) {
			System.out.println("TRIZZ-FIZZ");
		} else if(num%5==0) {
			System.out.println("FIZZ");
		}
			else if(num%3==0){
				System.out.println("TRIZZ");
	}
			else
			{
				System.out.println("Number not divisible both by 3 or 5");
			}

		}

	}


