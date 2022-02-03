package Week3.Day1;

public interface TaxCalculation {
	
	void calcDeductions(String Name,double HRA,double LTA);
	void calcGrossIncome(String Name,double netIncome,double Deductions);
	void replicativemethods();

}
