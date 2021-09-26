package com.revature.models;

public class ReimbursementType {

	int reimbursementTypeId; //PK, serial
	String reimbursementTypeName; //text

	
	//ctors
	public ReimbursementType() {
		super();
	}

	public ReimbursementType(String reimbursementTypeName) {
		super();
		this.reimbursementTypeName = reimbursementTypeName;
	}

	public ReimbursementType(int reimbursementTypeId, String reimbursementTypeName) {
		super();
		this.reimbursementTypeId = reimbursementTypeId;
		this.reimbursementTypeName = reimbursementTypeName;
	}

	//boilerplate instance methods
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbursementTypeId;
		result = prime * result + ((reimbursementTypeName == null) ? 0 : reimbursementTypeName.hashCode());
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
		ReimbursementType other = (ReimbursementType) obj;
		if (reimbursementTypeId != other.reimbursementTypeId)
			return false;
		if (reimbursementTypeName == null) {
			if (other.reimbursementTypeName != null)
				return false;
		} else if (!reimbursementTypeName.equals(other.reimbursementTypeName))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "ReimbursementType [reimbursementTypeId=" + reimbursementTypeId + ", reimbursementTypeName="
				+ reimbursementTypeName + "]";
	}

	
	/**
	 * @return the reimbursementTypeId
	 */
	public int getReimbursementTypeId() {
		return reimbursementTypeId;
	}

	/**
	 * @return the reimbursementTypeName
	 */
	public String getReimbursementTypeName() {
		return reimbursementTypeName;
	}

	/**
	 * @param reimbursementTypeId the reimbursementTypeId to set
	 */
	void setReimbursementTypeId(int reimbursementTypeId) {
		this.reimbursementTypeId = reimbursementTypeId;
	}

	/**
	 * @param reimbursementTypeName the reimbursementTypeName to set
	 */
	void setReimbursementTypeName(String reimbursementTypeName) {
		this.reimbursementTypeName = reimbursementTypeName;
	}
		
}
