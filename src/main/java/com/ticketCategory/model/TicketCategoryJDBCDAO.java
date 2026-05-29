package com.ticketCategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketCategoryJDBCDAO implements TicketCategoryDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/taipeigo?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = 
			"INSERT INTO ticketCategory (ticketCategoryName, ticketCategoryStatus) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT ticketCategoryId, ticketCategoryName, ticketCategoryStatus FROM ticketCategory order by ticketCategoryId";
	
	private static final String GET_ONE_STMT = 
			"SELECT ticketCategoryId, ticketCategoryName, ticketCategoryStatus FROM ticketCategory where ticketCategoryId = ?";
	
	private static final String DELETE = 
			"DELETE FROM ticketCategory where ticketCategoryId = ?";
	
	private static final String UPDATE = 
			"UPDATE ticketCategory set ticketCategoryName=?, ticketCategoryStatus=? where ticketCategoryId = ?";
	
	
	@Override
	public void insert(TicketCategoryVO ticketCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 新增 ResultSet 用來接回傳的 ID
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			// 加上 java.sql.Statement.RETURN_GENERATED_KEYS 告知驅動程式要拿回自增主鍵
			pstmt = con.prepareStatement(INSERT_STMT, java.sql.Statement.RETURN_GENERATED_KEYS);
			// 設定帶入 value 資料的位置，如1,2,3
			pstmt.setString(1, ticketCategoryVO.getTicketCategoryName());
			pstmt.setInt(2, ticketCategoryVO.getTicketCategoryStatus());
			
			// 執行指令並更新
			pstmt.executeUpdate();
			
			// 將資料庫自動生成的 ID 抓出來，塞回你的 ticketCategoryVO 物件中
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				Integer nextId = rs.getInt(1);
				ticketCategoryVO.setTicketCategoryId(nextId); // 大功告成！物件的 ID 不再是 null
			}
			
			// Handle any driver errors
		} catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
			// Handle any SQL errors
		} catch(SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void update(TicketCategoryVO ticketCategoryVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ticketCategoryVO.getTicketCategoryName());
			pstmt.setInt(2, ticketCategoryVO.getTicketCategoryStatus());
			pstmt.setInt(3, ticketCategoryVO.getTicketCategoryId());
			
			// 執行指令並更新
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public void delete(Integer ticketCategoryId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ticketCategoryId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	// 單筆查詢
	@Override
	public TicketCategoryVO findByPrimaryKey(Integer ticketCategoryId) {
		
		TicketCategoryVO ticketCategoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		// 接結果	回來	
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ticketCategoryId);
			
			//  executeQuery 執行指令但沒有更新 （抓資料而已）
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ticketCategoryVO 也稱為 Domain objects
				ticketCategoryVO = new TicketCategoryVO();
				ticketCategoryVO.setTicketCategoryId(rs.getInt("ticketCategoryId"));
				ticketCategoryVO.setTicketCategoryName(rs.getString("ticketCategoryName"));
				ticketCategoryVO.setTicketCategoryStatus(rs.getInt("ticketCategoryStatus"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return ticketCategoryVO;
	}
	
	// 全部查詢
	@Override
	public List<TicketCategoryVO> getAll(){
		List<TicketCategoryVO> list = new ArrayList<TicketCategoryVO>();
		TicketCategoryVO ticketCategoryVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		// 接結果	回來	
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			//  executeQuery 執行指令但沒有更新 （抓資料而已）
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// ticketCategoryVO 也稱為 Domain objects
				ticketCategoryVO = new TicketCategoryVO();
				ticketCategoryVO.setTicketCategoryId(rs.getInt("ticketCategoryId"));
				ticketCategoryVO.setTicketCategoryName(rs.getString("ticketCategoryName"));
				ticketCategoryVO.setTicketCategoryStatus(rs.getInt("ticketCategoryStatus"));
				// 存放單筆資料，用 while 去存全部
				list.add(ticketCategoryVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	
	
	
}
