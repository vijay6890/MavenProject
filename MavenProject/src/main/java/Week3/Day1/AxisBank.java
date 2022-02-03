package Week3.Day1;

public class AxisBank extends BankInfo {
	
	
	@Override
	public void deposit() {
		// TODO Auto-generated method stub
	System.out.println("Deposit is updated");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AxisBank bank=new AxisBank();
		bank.saving();
		bank.deposit();
		bank.fixed();

	}

}
