package model;

public class InsuranceDetails {
	
	private String policyName;
	private String policyNumber;
	private String insurer;
	private String premium;
	private static String insuranceId;
	private static String comments;
	private String expiryDate;
	
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}	
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInsurer() {
		return insurer;
	}
	public void setInsurer(String insurer) {
		this.insurer = insurer;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public static String getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(String insuranceId) {
		InsuranceDetails.insuranceId = insuranceId;
	}
	public static String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		InsuranceDetails.comments = comments;
	}	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

}
