package com.ticketCategory.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.ticketCategory.model.TicketCategoryJDBCDAO;
import com.ticketCategory.model.TicketCategoryVO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ticketCategory/ticketCategory.do")
public class TicketCategoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// ==================== 1. 處理來自首頁或選單的「查詢全部」 ====================
		if ("listAll".equals(action)) {
			// 直接呼叫 DAO 撈取全部資料
			TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
			List<TicketCategoryVO> list = dao.getAll();

			// 存入 request 範圍，準備轉交給前端 JSP
			req.setAttribute("list", list);

			// ⚠️ 修正：加上 /frontend
			String url = "/frontend/ticketCategory/listAllTicketCategory.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return; // 程式中斷
		}

		// ==================== 2. 來自 listAllTicketCategory.jsp 的「點擊修改」請求
		// ====================
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1. 接收請求參數 ****************************************/
				Integer ticketCategoryId = Integer.valueOf(req.getParameter("ticketCategoryId"));

				/*************************** 2. 開始查詢資料 ****************************************/
				TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
				TicketCategoryVO ticketCategoryVO = dao.findByPrimaryKey(ticketCategoryId);

				/*************************** 3. 查詢完成,準備轉交 ***********************************/
				req.setAttribute("ticketCategoryVO", ticketCategoryVO); // 查出的物件存入 req，供修改頁面預填資料

				// ⚠️ 修正：加上 /frontend
				String url = "/frontend/ticketCategory/update_ticketCategory_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料: " + e.getMessage());
				// ⚠️ 修正：加上 /frontend
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketCategory/listAllTicketCategory.jsp");
				failureView.forward(req, res);
			}
		}

		// ==================== 3. 來自 update_ticketCategory_input.jsp 的「送出修改」請求
		// ====================
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1. 接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer ticketCategoryId = Integer.valueOf(req.getParameter("ticketCategoryId").trim());

				String ticketCategoryName = req.getParameter("ticketCategoryName");
				if (ticketCategoryName == null || ticketCategoryName.trim().length() == 0) {
					errorMsgs.add("分類名稱: 請勿空白");
				}

				Integer ticketCategoryStatus = null;
				try {
					ticketCategoryStatus = Integer.valueOf(req.getParameter("ticketCategoryStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態請填寫正確數字.");
				}

				// 把使用者剛才輸入的資料打包
				TicketCategoryVO ticketCategoryVO = new TicketCategoryVO();
				ticketCategoryVO.setTicketCategoryId(ticketCategoryId);
				ticketCategoryVO.setTicketCategoryName(ticketCategoryName);
				ticketCategoryVO.setTicketCategoryStatus(ticketCategoryStatus);

				// 如果有錯誤訊息，原頁面返回
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketCategoryVO", ticketCategoryVO);
					// ⚠️ 修正：加上 /frontend
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketCategory/update_ticketCategory_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/***************************
				 * 2. 開始修改資料
				 *****************************************/
				TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
				dao.update(ticketCategoryVO);

				/*************************** 3. 修改完成,準備轉交 ***********************************/
				List<TicketCategoryVO> list = dao.getAll();
				req.setAttribute("list", list);

				// ⚠️ 修正：加上 /frontend
				String url = "/frontend/ticketCategory/listAllTicketCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗: " + e.getMessage());
				// ⚠️ 修正：加上 /frontend
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketCategory/update_ticketCategory_input.jsp");
				failureView.forward(req, res);
			}
		}

		// ==================== 4. 來自 addTicketCategory.jsp 的「新增」請求 ====================
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1. 接收請求參數 - 輸入格式的錯誤處理 *************************/
				String ticketCategoryName = req.getParameter("ticketCategoryName");
				if (ticketCategoryName == null || ticketCategoryName.trim().length() == 0) {
					errorMsgs.add("分類名稱: 請勿空白");
				}

				Integer ticketCategoryStatus = null;
				try {
					ticketCategoryStatus = Integer.valueOf(req.getParameter("ticketCategoryStatus").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態請填寫正確數字.");
				}

				TicketCategoryVO ticketCategoryVO = new TicketCategoryVO();
				ticketCategoryVO.setTicketCategoryName(ticketCategoryName);
				ticketCategoryVO.setTicketCategoryStatus(ticketCategoryStatus);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ticketCategoryVO", ticketCategoryVO);
					// ⚠️ 修正：加上 /frontend
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketCategory/addTicketCategory.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2. 開始新增資料 ***************************************/
				TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
				dao.insert(ticketCategoryVO);

				/*************************** 3. 新增完成,準備轉交 ***********************************/
				List<TicketCategoryVO> list = dao.getAll();
				req.setAttribute("list", list);

				// ⚠️ 修正：加上 /frontend
				String url = "/frontend/ticketCategory/listAllTicketCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("新增資料失敗: " + e.getMessage());
				// ⚠️ 修正：加上 /frontend
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketCategory/addTicketCategory.jsp");
				failureView.forward(req, res);
			}
		}

		// ==================== 5. 來自 listAllTicketCategory.jsp 的「刪除」請求
		// ====================
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1. 接收請求參數 ***************************************/
				Integer ticketCategoryId = Integer.valueOf(req.getParameter("ticketCategoryId"));

				/*************************** 2. 開始刪除資料 ***************************************/
				TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
				dao.delete(ticketCategoryId);

				/*************************** 3. 刪除完成,準備轉交 ***********************************/
				List<TicketCategoryVO> list = dao.getAll();
				req.setAttribute("list", list);

				// ⚠️ 修正：加上 /frontend
				String url = "/frontend/ticketCategory/listAllTicketCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗: " + e.getMessage());
				// ⚠️ 修正：加上 /frontend
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/ticketCategory/listAllTicketCategory.jsp");
				failureView.forward(req, res);
			}
		}

		// ==================== 0. 處理來自 select_page.jsp 的「單筆查詢」請求 ====================
		if ("getOne_For_Display".equals(action)) { // 檢查是否為單筆查詢的要求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1. 接收請求參數 - 輸入格式的錯誤處理 ***********************/
				String str = req.getParameter("ticketCategoryId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入票券分類編號");
				}

				// 如果輸入為空，直接彈回首頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketCategory/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				Integer ticketCategoryId = null;
				try {
					ticketCategoryId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("票券分類編號格式不正確");
				}

				// 格式不正確，彈回首頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketCategory/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2. 開始查詢資料 ****************************************/
				TicketCategoryJDBCDAO dao = new TicketCategoryJDBCDAO();
				TicketCategoryVO ticketCategoryVO = dao.findByPrimaryKey(ticketCategoryId);
				if (ticketCategoryVO == null) {
					errorMsgs.add("查無資料");
				}

				// 查無資料，彈回首頁
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/ticketCategory/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 3. 查詢完成,準備轉交 ***********************************/
				// 將查出來的物件存入 request 物件中，讓下一頁可以用 <%= ticketCategoryVO.getXxx() %> 撈取
				req.setAttribute("ticketCategoryVO", ticketCategoryVO);

				// 轉交給昨天做好的單筆顯示頁面
				String url = "/frontend/ticketCategory/listOneTicketCategory.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return; // 程式中斷

			} catch (Exception e) {
				errorMsgs.add("無法取得資料: " + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/ticketCategory/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

/*
 * 全部 http://localhost:8081/TKA101_C_myMaven1/ticketCategory/ticketCategory.do?
 * action=listAll 新增
 * 
 * 查詢
 */