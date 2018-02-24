package com.SOB.dao.book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.SOB.bean.BookBean;
import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;


public interface BooksDaoIface {

	// 模糊查询所有书籍信息
	public List<BookBean> getAllBooks(Connection con,String bookName, String grade, String bookObject);
	// 查询所有商品信息
	public List<BookBean> findProByCategory(Connection con);
	// 根据商品的id查询该商品的信息
	public BookBean findBookById(Connection con, String proId);
	//根据商品名称，更新商品信息
	public int modifyProById(Connection con,BookBean bean, String bookName);
	//增加新商品信息
	public int insertNewPro(Connection con,BookBean bean);
	//删除商品信息
	public int deleteProInfor(Connection con,String bookName)throws Exception;
	//根据条件查询信息
	public ResultSet managerBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//查询用户上传图书
	public ResultSet uploadBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//根据条件查询书的数量
	public int managerBookCount(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//审批用户上传信息商品信息
	public int checkProInfor(Connection con,String bookName)throws Exception;
	
}
