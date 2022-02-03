package Week3.Day1;

public class CooperativeCalculations implements TaxCalculation,IncomeCalculation {

	

	public void calcDeductions(String Name, double HRA, double LTA) {
		// TODO Auto-generated method stub
		System.out.println("CalcDeductions-Cooperative");
		
	}

	public void calcGrossIncome(String Name, double netIncome, double Deductions) {
		// TODO Auto-generated method stub
		System.out.println("CalcGrossIncome-Co-operative");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
   IndividualCalculations IC=new  IndividualCalculations();
   IC.calcDeductions("Veera",20000 ,10000);
   IC.calcGrossIncome("Vel", 5000, 6000);
  CooperativeCalculations CC=new CooperativeCalculations();
  CC.calcGrossIncome();
 CC.replicativemethods();
 CC.removeDeductions();

    		 
	}

	public void calcGrossIncome() {
	System.out.println("Interface2-Gross Income");
	
}

	public void removeDeductions() {
		// TODO Auto-generated method stub
		System.out.println("Interface2-RemoveDeductions");
		
	}

	public void replicativemethods() {
		// TODO Auto-generated method stub
		System.out.println("Replication");
		
	}
}
