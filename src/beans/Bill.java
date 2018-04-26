package beans;

import java.sql.Timestamp;

public class Bill {
	private String billNumber;
	private Timestamp startTime;
	private Timestamp planTime;
	private boolean state;
	private String orgName;
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Timestamp getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Timestamp finishTime) {
		this.planTime = finishTime;
	}
	public String getBillNumber() {
		return billNumber;
	}
	public void setBillNumber(String billNumber) {
		this.billNumber = billNumber;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
}
