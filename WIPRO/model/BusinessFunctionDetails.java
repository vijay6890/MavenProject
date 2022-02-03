package model;

public class BusinessFunctionDetails {
	
	private String functionName;
	private String functionId;
	private String department;
	private String lossPerDay;
	private String minimumEmployeesRequired;
	private String recoveryTime;
	private String objective;
	private String consequences;
	private String comments;
	
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionId() {
		return functionId;
	}
	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLossPerDay() {
		return lossPerDay;
	}
	public void setLossPerDay(String lossPerDay) {
		this.lossPerDay = lossPerDay;
	}
	public String getMinimumEmployeesRequired() {
		return minimumEmployeesRequired;
	}
	public void setMinimumEmployeesRequired(String minimumEmployeesRequired) {
		this.minimumEmployeesRequired = minimumEmployeesRequired;
	}
	public String getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(String recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public String getObjective() {
		return objective;
	}
	public void setObjective(String objective) {
		this.objective = objective;
	}
	public String getConsequences() {
		return consequences;
	}
	public void setConsequences(String consequences) {
		this.consequences = consequences;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

}
