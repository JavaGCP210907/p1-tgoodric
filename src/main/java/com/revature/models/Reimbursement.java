package com.revature.models;

import java.awt.Image; //I HATE java.awt
import java.sql.Date; //use System.currentTimeMillis()

public class Reimbursement {
	
	private int reimbursementId; //serial
	private double amount; //not null
	private Date submitted; //date, not null, consider changing to BigDecimal for arbitrary precision
	private Date resolved; //date, nulls allowed
	private String description; //text, 
	private Image receipt; //Stored in PostgreSQL as BYTEA, BLOB not supported by PostgreSQL AFAICT
	private int reimbursementType; // FK, keyed to type of reimbursement 
	private int submittedBy; // FK, keyed to person who requested	
	private int resolvedBy; // FK, keyed to person who resolved
	private int status = 1; //FK, keyed to status
	
	//Mostly completed 09/26, additional ctors may be added later
	
	public Reimbursement() {
		super();
	}

	/**
	 * All-Arguments Constructor
	 * 
	 * @param reimbursementId serial PK in database
	 * @param amount amount being reimbursed
	 * @param submitted SQL date submitted
	 * @param resolved SQL date resolved, nulls allowed
	 * @param description brief description of expense
	 * @param receipt image of receipt
	 * @param reimbursementType database FK, references reimbursement type table
	 * @param submittedBy database FK, references user table
	 * @param resolvedBy database FK, references user table, nulls allowed
	 * @param status database FK, references status table
	 */
	public Reimbursement(int reimbursementId, double amount, Date submitted, Date resolved, String description,
			Image receipt, int reimbursementType, int submittedBy, int resolvedBy, int status) {
		super();
		this.reimbursementId = reimbursementId;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
		this.resolvedBy = resolvedBy;
		this.status = status;
	}

	/**
	 * Most-arguments Constructor
	 * 
	 * @param amount amount being reimbursed
	 * @param submitted SQL date submitted
	 * @param resolved SQL date resolved, nulls allowed
	 * @param description brief description of expense
	 * @param receipt image of receipt
	 * @param reimbursementType database FK, references reimbursement type table
	 * @param submittedBy database FK, references user table
	 * @param resolvedBy database FK, references user table, nulls allowed
	 * @param status database FK, references status table
	 */
	public Reimbursement(double amount, Date submitted, Date resolved, String description, Image receipt,
			int reimbursementType, int submittedBy, int resolvedBy, int status) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
		this.resolvedBy = resolvedBy;
		this.status = status;
	}
	
	/**
	 * @param amount
	 * @param submitted
	 * @param description
	 * @param reimbursementType
	 * @param submittedBy
	 */
	public Reimbursement(double amount, Date submitted, String description, int reimbursementType, int submittedBy) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
		this.status = 1;
	}
	
	/**
	 * @param amount amount being reimbursed
	 * @param submitted SQL date submitted
	 * @param description
	 * @param receipt
	 * @param reimbursementType
	 * @param submittedBy
	 */
	public Reimbursement(double amount, Date submitted, String description, Image receipt, int reimbursementType,
			int submittedBy) {
		super();
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.receipt = receipt;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
		this.status = 1;
	}

	/**
	 * @param amount
	 * @param description
	 * @param reimbursementType
	 * @param submittedBy
	 */
	public Reimbursement(double amount, String description, int reimbursementType, int submittedBy) {
		super();
		this.amount = amount;
		this.description = description;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
		this.submitted = new Date(System.currentTimeMillis());
		this.resolved = null;
		this.receipt = null;
		this.status = 1;
	}

	/**
	 * @param amount
	 * @param description
	 * @param receipt
	 * @param reimbursementType
	 * @param submittedBy
	 */
	public Reimbursement(double amount, String description, Image receipt, int reimbursementType, int submittedBy) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.reimbursementType = reimbursementType;
		this.submittedBy = submittedBy;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + reimbursementId;
		result = prime * result + reimbursementType;
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolvedBy;
		result = prime * result + status;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + submittedBy;
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
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (reimbursementId != other.reimbursementId)
			return false;
		if (reimbursementType != other.reimbursementType)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolvedBy != other.resolvedBy)
			return false;
		if (status != other.status)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (submittedBy != other.submittedBy)
			return false;
		return true;
	}

	/**
	 * @return the reimbursementId
	 */
	public int getReimbursementId() {
		return reimbursementId;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @return the submitted
	 */
	public Date getSubmitted() {
		return submitted;
	}

	/**
	 * @return the resolved
	 */
	public Date getResolved() {
		return resolved;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the receipt
	 */
	public Image getReceipt() {
		return receipt;
	}

	/**
	 * @return the reimbursementType
	 */
	public int getReimbursementType() {
		return reimbursementType;
	}

	/**
	 * @return the submittedBy
	 */
	public int getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @return the resolvedBy
	 */
	public int getResolvedBy() {
		return resolvedBy;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param reimbursementId the reimbursementId to set
	 */
	void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	/**
	 * @param amount the amount to set
	 */
	void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @param submitted the submitted to set
	 */
	void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	/**
	 * @param resolved the resolved to set
	 */
	void setResolved(Date resolved) {
		this.resolved = resolved;
	}

	/**
	 * @param description the description to set
	 */
	void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param receipt the receipt to set
	 */
	void setReceipt(Image receipt) {
		this.receipt = receipt;
	}

	/**
	 * @param reimbursementType the reimbursementType to set
	 */
	void setReimbursementType(int reimbursementType) {
		this.reimbursementType = reimbursementType;
	}

	/**
	 * @param submittedBy the submittedBy to set
	 */
	void setSubmittedBy(int submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @param resolvedBy the resolvedBy to set
	 */
	void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	/**
	 * @param status the status to set
	 */
	void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", amount=" + amount + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", receipt=" + receipt
				+ ", reimbursementType=" + reimbursementType + ", submittedBy=" + submittedBy + ", resolvedBy="
				+ resolvedBy + ", status=" + status + "]";
	}
	
}
