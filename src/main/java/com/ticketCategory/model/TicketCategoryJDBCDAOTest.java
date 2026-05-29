package com.ticketCategory.model;

import java.util.List;

public class TicketCategoryJDBCDAOTest {
	// ====== 測試檢查 =======
		public static void main(String[] args) {

	        TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
	        // 測試新增
	        testInsert(dao);
	        
	        // 測試更新
	        // testUpdate(dao);
	        
	        // 測試刪除
	        // testDelete(dao);
	        
	        // 測試單筆查詢
	        // testFindById(dao);
	        
            // 測試全部查詢	        
	        //testGetAll(dao);
	    }
		
		public static void testInsert(TicketCategoryJDBCDAO dao) {
			System.out.println("--- 開始測試 [新增] 功能 ---");
			TicketCategoryVO vo = new TicketCategoryVO();
			vo.setTicketCategoryName("測試");
			vo.setTicketCategoryStatus(0); 
			
			try {
				dao.insert(vo);
				System.out.println("新增測試成功");
			} catch (Exception e) {
				System.err.println("新增測試失敗");
				e.printStackTrace();
			}
		}
		
		public static void testUpdate(TicketCategoryJDBCDAO dao) {
			System.out.println("--- 開始測試 [更新] 功能 ---");
			TicketCategoryVO vo = new TicketCategoryVO();
			vo.setTicketCategoryId(1); 
			vo.setTicketCategoryName("測試更新1");
			vo.setTicketCategoryStatus(0); 
			try {
				dao.update(vo);
				System.out.println("更新測試成功");
			} catch (Exception e) {
				System.err.println("更新測試失敗");
				e.printStackTrace();
			}
			
		}
		
		public static void testDelete(TicketCategoryJDBCDAO dao) {
			System.out.println("--- 開始測試 [刪除] 功能 ---");
			
			Integer currentTicketCategoryId = 1;
			try {
				dao.delete(currentTicketCategoryId);
				System.out.println("刪除測試成功，刪除 TicketCategoryId = " + currentTicketCategoryId);
			} catch (Exception e) {
				System.err.println("刪除測試失敗");
				e.printStackTrace();
			}
			
		}
		
		public static void testFindById(TicketCategoryJDBCDAO dao) {
		    System.out.println("--- 開始測試 [單筆查詢] 功能 ---");
		    
		    Integer targetId = 2; 
		    
		    try {
		    	TicketCategoryVO vo = dao.findByPrimaryKey(targetId);
		    	
		    	if (vo != null) {
		    		System.out.println("單筆查詢測試成功，測試 TicketCategoryId = " + targetId);
		            System.out.println("id: " + vo.getTicketCategoryId());
		            System.out.println("name: " + vo.getTicketCategoryName());
		            System.out.println("status " + vo.getTicketCategoryStatus());
		        } else {
		            System.out.println("查無資料：資料庫中沒有 TicketCategoryId 為 " + targetId + "。");
		        }
		        
		        
		    } catch (Exception e) {
		        System.err.println("單筆查詢測試失敗，測試 TicketCategoryId = " + targetId);
		        e.printStackTrace();
		    }
		}
		
		public static void testGetAll(TicketCategoryJDBCDAO dao) {
			System.out.println("--- 開始測試 [全表查詢] 功能 ---");
			
			try {
				List<TicketCategoryVO> list = dao.getAll();
				
				if (list != null && !list.isEmpty()) {
					System.out.println("全表查詢測試成功，總共抓到 " + list.size() + " 筆資料：");
					System.out.println("----------------------------------------");
					
					for (TicketCategoryVO vo : list) {
						System.out.print("ID: " + vo.getTicketCategoryId() + " | ");
						System.out.print("名稱: " + vo.getTicketCategoryName() + " | ");
						System.out.println("狀態: " + vo.getTicketCategoryStatus());
					}
					
				} else {
					System.out.println("查詢成功，但目前資料庫內沒有任何票券類別資料。");
				}
				
			} catch (Exception e) {
				System.err.println("全表查詢測試失敗！");
				e.printStackTrace();
			}
		}
}
