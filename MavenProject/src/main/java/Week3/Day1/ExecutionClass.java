package Week3.Day1;

public class ExecutionClass  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Audi a=new Audi();
  a.closedoor();
  a.drive();
  a.shiftgear();
  System.out.println("No of Wheels"+a.wheelcar);
  
  
  BMW b=new BMW();
  b.closedoor();
  System.out.println("No of Wheels"+b.wheelcar);
  b.drive();
  b.shiftgear();
	}

}
