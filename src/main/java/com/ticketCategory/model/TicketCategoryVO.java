package com.ticketCategory.model;

public class TicketCategoryVO {
	
	private Integer ticketCategoryId;
	private String ticketCategoryName;
	private Integer ticketCategoryStatus;
	
	public Integer getTicketCategoryId() {
		return ticketCategoryId;
	}
	

	public void setTicketCategoryId(Integer ticketCategoryId) {
		this.ticketCategoryId = ticketCategoryId;
	}
	
	public String getTicketCategoryName() {
		return ticketCategoryName;
	}
	
	public void setTicketCategoryName(String ticketCategoryName) {
		this.ticketCategoryName = ticketCategoryName;
	}
	
	public Integer getTicketCategoryStatus() {
		return ticketCategoryStatus;
	}
	
	public void setTicketCategoryStatus(Integer ticketCategoryStatus) {
		this.ticketCategoryStatus = ticketCategoryStatus;
	}
	
}
