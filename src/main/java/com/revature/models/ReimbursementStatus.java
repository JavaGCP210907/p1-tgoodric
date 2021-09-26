package com.revature.models;

public class ReimbursementStatus {

	
	//Class completed 26/09/2021
	
	private int reimbursementStatusId;
	private String reimbursementStatusName;
	
	//ctors
	public ReimbursementStatus() {
		super();
	}

	public ReimbursementStatus(String reimbursementStatusName) {
		super();
		this.reimbursementStatusName = reimbursementStatusName;
	}

	public ReimbursementStatus(int reimbursementStatusId, String reimbursementStatusName) {
		super();
		this.reimbursementStatusId = reimbursementStatusId;
		this.reimbursementStatusName = reimbursementStatusName;
	}
	
	//Boilerplate code
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbursementStatusId;
		result = prime * result + ((reimbursementStatusName == null) ? 0 : reimbursementStatusName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatus other = (ReimbursementStatus) obj;
		if (reimbursementStatusId != other.reimbursementStatusId)
			return false;
		if (reimbursementStatusName == null) {
			if (other.reimbursementStatusName != null)
				return false;
		} else if (!reimbursementStatusName.equals(other.reimbursementStatusName))
			return false;
		return true;
	}

	public int getReimbursementStatusId() {
		return reimbursementStatusId;
	}

	public String getReimbursementStatusName() {
		return reimbursementStatusName;
	}

	void setReimbursementStatusId(int reimbursementStatusId) {
		this.reimbursementStatusId = reimbursementStatusId;
	}

	void setReimbursementStatusName(String reimbursementStatusName) {
		this.reimbursementStatusName = reimbursementStatusName;
	}
}
