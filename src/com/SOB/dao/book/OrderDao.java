package com.SOB.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SOB.bean.BookBean;
import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.StringUtil;

public class OrderDao {
	public int insertNewOrder(Connection con,String orderid,String userid,Double price, String phone,String receiptAddress){
		PreparedStatement pst = null;
		String sql = "insert into order_t (orderid,userid,price,phone,receiptaddress) " +
										 "values (?,?,?,?,?)";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,orderid);
			pst.setString(2,userid);
			pst.setDouble(3,price);
			pst.setString(4,phone);
			pst.setString(5, receiptAddress);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return i;
	}
	public ResultSet orderList(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select * from order_t where 1 = 1");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int orderCount(Connection con,PageBean pageBean)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from order_t where 1 = 1");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet resu =  pstmt.executeQuery();
		if(resu.next()){
			return resu.getInt("total");
		}else{
			return 0;
		}
	}
	public int deleteOrder(Connection con,String orderId)throws Exception{
		String sql = "delete from order_t where orderid in ("+orderId+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
