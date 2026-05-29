package com.ticketCategory.model;

import java.util.List;

public interface TicketCategoryDAO_interface {
	public void insert(TicketCategoryVO ticketCategoryVO);
	public void update(TicketCategoryVO ticketCategoryVO);
	public void delete(Integer ticketCategoryId);
	
	// 單筆查詢
	public TicketCategoryVO findByPrimaryKey(Integer ticketCategoryId);
	// 多筆查詢
	public List<TicketCategoryVO> getAll();

}
