package model;

public class IncidentsDetails {

	private String incidentlocation;
	private String incidentspot;
	private String incidenttype;
	private String date;
	private String time;
	private String severitylevel;
	private String areyouaffected;
	private String whathappened;
	
	public String getIncidentLocation()
	{
		return incidentlocation;
	}
	public void setIncidentLocation(String incidentlocation)
	{
		this.incidentlocation = incidentlocation;
	}
	
	public String getIncidentSpot()
	{
		return incidentspot;
	}
	
	public void setIncidentSpot(String incidentspot)
	{
		this.incidentspot = incidentspot;
	}
	
	public String getIncidentType()
	{
		return incidenttype;
	}
	public void setIncidentType(String incidenttype)
	{
		this.incidenttype = incidenttype;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public String getTime()
	{
		return time;
	}
	
	public void setTime(String time)
	{ 
		this.time = time;
	}
	
	public String getSeverityLevel()
	{
		return severitylevel;
	}
	
	public void setSeverityLevel(String severitylevel)
	
	{
		this.severitylevel = severitylevel;
	}
	
	public String getareyouaffected()
	{
		return areyouaffected;
	}
	
	public void setAreYouAffected(String areyouaffected)
	{
		this.areyouaffected = areyouaffected;
	}
	
	public String getWhatHappened()
	{
		return whathappened;
	}
	
	public void setWhatHappened(String whathappened)
	{
		this.whathappened = whathappened;
		
		
	}
}


