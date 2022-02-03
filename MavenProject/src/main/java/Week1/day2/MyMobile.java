package Week1.day2;

public class MyMobile {
	String brandname;
	int cost = 45000;
	boolean istouch;

	public void makecall() {
		System.out.println("making calls");
	}

	public void sendMsg() {
		System.out.println("Sending Message");
	}

	private void payBills() {
		System.out.println("Paying bills");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyMobile mob = new MyMobile();
		System.out.println("Redme");
		System.out.println(mob.cost);
		System.out.println(mob.istouch);
		mob.makecall();
		mob.sendMsg();
		mob.payBills();
	}

}
