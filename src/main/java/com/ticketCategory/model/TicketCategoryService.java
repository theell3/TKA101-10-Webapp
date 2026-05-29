package com.ticketCategory.model;

import java.util.List;

public class TicketCategoryService {
	
	private TicketCategoryDAO_interface dao;
	
	public TicketCategoryService() {
		dao = new TicketCategoryJDBCDAO();
	}
	
	
	public TicketCategoryVO addTicketCategory(Integer ticketCategoryId, String ticketCategoryName, Integer ticketCategoryStatus) {
		TicketCategoryVO ticketCategoryVO = new TicketCategoryVO();
		ticketCategoryVO.setTicketCategoryId(ticketCategoryId);
		ticketCategoryVO.setTicketCategoryName(ticketCategoryName);
		ticketCategoryVO.setTicketCategoryStatus(ticketCategoryStatus);
		dao.insert(ticketCategoryVO);
		return ticketCategoryVO;
	}
	
	public TicketCategoryVO updateTicketCategory(Integer ticketCategoryId, String ticketCategoryName, Integer ticketCategoryStatus) {
		TicketCategoryVO ticketCategoryVO = new TicketCategoryVO();
		ticketCategoryVO.setTicketCategoryId(ticketCategoryId);
		ticketCategoryVO.setTicketCategoryName(ticketCategoryName);
		ticketCategoryVO.setTicketCategoryStatus(ticketCategoryStatus);
		dao.update(ticketCategoryVO);
		return ticketCategoryVO;
	}
	
	
	public void deleteTicketCategory(Integer ticketCategoryId) {
		dao.delete(ticketCategoryId);
	}
	
	public TicketCategoryVO getOneTicketCategory(Integer ticketCategoryId) {
		return dao.findByPrimaryKey(ticketCategoryId);
	}
	
	public List<TicketCategoryVO> getAll(){
		return dao.getAll();
	}
}
