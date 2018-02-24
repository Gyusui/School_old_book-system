package com.SOB.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SOB.bean.BookBean;
import com.SOB.bean.PageBean;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.StringUtil;


public class BooksDaoImpl implements BooksDaoIface{

	// 获取所有图书
	public List<BookBean> getAllBooks(Connection con, String bookName, String grade, String bookObject) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = null;
		List<BookBean> list = new ArrayList<BookBean>();
		if(bookName!=null && grade!=null && bookObject!=null){
			sql = "select * from book_t where grade=? and bookobject=? and bookname like ?";
			try {
				pst = con.prepareStatement(sql);
				pst.setString(1, grade);
				pst.setString(2, bookObject);
				pst.setString(3, "%"+bookName+"%");
				
				rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String bookname = rs.getString("bookname");
					String usergrade = rs.getString("grade");
					String bookobject = rs.getString("bookobject");
					String price = new Double(rs.getDouble("price")).toString();
					String physical = rs.getString("physical");
					String url = rs.getString("picture");
					String desc = "";
					if(rs.getString("description")==null){
						desc = "";
					}else{
						desc = rs.getString("description");
					}
					BookBean bb = new BookBean(id, bookname, usergrade, bookobject,
							price, desc, physical, "",
							"", url);
					
	
					list.add(bb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}else {
			sql = "select * from book_t";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String bookname = rs.getString("bookname");
					String usergrade = rs.getString("grade");
					String bookobject = rs.getString("bookobject");
					String price = new Double(rs.getDouble("price")).toString();
					String physical = rs.getString("physical");
					String url = rs.getString("picture");
					String desc = "";
					if(rs.getString("description")==null){
						desc = "";
					}else{
						desc = rs.getString("description");
					}
					BookBean bb = new BookBean(id, bookname, usergrade, bookobject,
							price, desc, physical, "",
							"", url);
					
	
					list.add(bb);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// ��ѯ������Ʒ��Ϣ
	public List<BookBean> findProByCategory(Connection con) {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<BookBean> list = new ArrayList<BookBean>();
		String sql = "select * from book_t";

		try {
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String bookname = rs.getString("bookname");
				String usergrade = rs.getString("grade");
				String bookobject = rs.getString("bookobject");
				String price = new Double(rs.getDouble("price")).toString();
				String physical = rs.getString("physical");
				String url = rs.getString("picture");
				String desc = rs.getString("description");

				BookBean product = new BookBean();
				product.setId(id);
				product.setBookName(bookname);
				product.setGrade(usergrade);
				product.setBookObject(bookobject);
				product.setPrice(price);
				product.setPhysical(physical);
				product.setPictureUrl(url);
				product.setDescription(desc);

				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBaseConnManager.closeResultSet(rs);
			DataBaseConnManager.closePreparedStatement(st);
		}
		return list;
	}

	// �����Ʒ��id��ѯ����Ʒ����Ϣ
	public BookBean findBookById(Connection con, String proId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from product_book_t where productid=?";
		BookBean product = new BookBean();

		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,proId);
			rs = pst.executeQuery();
			while (rs.next()) {

				String name = rs.getString("name");
				String producer = rs.getString("publisher");
				float price = rs.getFloat("price");
				String categoryid = rs.getString("categoryid");
				String descn = rs.getString("description");
//
//				product.setCateid(categoryid);
//				product.setDesc(descn);
//				product.setName(name);
//				product.setPrice(price);
//				product.setPublisher(producer);
//				product.setId(proId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closeResultSet(rs);
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return product;
	}
	
	//���������ѯ��Ϣ
	public ResultSet managerBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book_t where status ='1'");
		if(StringUtil.isNotEmpty(bookName)){
			sb.append(" and bookname like '%"+bookName+"%'");
		}
		if(StringUtil.isNotEmpty(grade)){
			sb.append(" and grade = '"+grade+"'");
		}
		if(StringUtil.isNotEmpty(bookObject)){
			sb.append(" and bookobject = '"+bookObject+"'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	//��ѯ�û��ϴ�ͼ��
	public ResultSet uploadBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception{
		StringBuffer sb=new StringBuffer("select * from book_t where userId != 'manager'");
		if(StringUtil.isNotEmpty(bookName)){
			sb.append(" and bookname like %'"+bookName+"'%");
		}
		if(StringUtil.isNotEmpty(grade)){
			sb.append(" and grade = '"+grade+"'");
		}
		if(StringUtil.isNotEmpty(bookObject)){
			sb.append(" and bookobject = '"+bookObject+"'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int managerBookCount(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from book_t where status ='1'");
		if(StringUtil.isNotEmpty(bookName)){
			sb.append(" and bookname like %'"+bookName+"'%");
		}
		if(StringUtil.isNotEmpty(grade)){
			sb.append(" and grade = '"+grade+"'");
		}
		if(StringUtil.isNotEmpty(bookObject)){
			sb.append(" and bookobject = '"+bookObject+"'");
		}
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
	//�����Ʒ��ƣ�������Ʒ��Ϣ
	public int modifyProById(Connection con,BookBean bean, String bookName){
		PreparedStatement pst = null;
		String sql = "update book_t set bookname=?,grade=?,bookobject=?,price=?,physical=?," +
				"picture=?,description=? where bookname=?";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, bean.getBookName());
			pst.setString(2, bean.getGrade());
			pst.setString(3, bean.getBookObject());
			pst.setDouble(4, new Double(bean.getPrice()));
			pst.setString(5, bean.getPhysical());
			pst.setString(6, bean.getPictureUrl());
			pst.setString(7, bean.getDescription());
			pst.setString(8, bookName);
			i = pst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return i;
	}
	//��������Ʒ��Ϣ
	public int insertNewPro(Connection con,BookBean bean){
		PreparedStatement pst = null;
		String sql = "insert into book_t (bookname,grade,bookobject,price,physical,status,userId,picture,description) " +
										 "values (?,?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,bean.getBookName());
			pst.setString(2,bean.getGrade());
			pst.setString(3,bean.getBookObject());
			pst.setDouble(4, new Double(bean.getPrice()));
			pst.setString(5, bean.getPhysical());
			pst.setString(6, bean.getStatus());
			pst.setString(7, bean.getUserId());
			pst.setString(8, bean.getPictureUrl());
			pst.setString(9, bean.getDescription());
			i = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DataBaseConnManager.closePreparedStatement(pst);
		}
		return i;
	}
	
	//ɾ����Ʒ��Ϣ
	public int deleteProInfor(Connection con,String bookName)throws Exception{
		String sql = "delete from book_t where bookname in ("+bookName+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
	
	//�����û��ϴ���Ϣ��Ʒ��Ϣ
	public int checkProInfor(Connection con,String bookName)throws Exception{
		String sql = "update from book_t set status='1' where bookname in ("+bookName+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}
}
