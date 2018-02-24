package com.SOB.dao.book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import com.SOB.bean.BookBean;
import com.SOB.bean.PageBean;
import com.SOB.bean.UserBean;


public interface BooksDaoIface {

	// ģ����ѯ�����鼮��Ϣ
	public List<BookBean> getAllBooks(Connection con,String bookName, String grade, String bookObject);
	// ��ѯ������Ʒ��Ϣ
	public List<BookBean> findProByCategory(Connection con);
	// ������Ʒ��id��ѯ����Ʒ����Ϣ
	public BookBean findBookById(Connection con, String proId);
	//������Ʒ���ƣ�������Ʒ��Ϣ
	public int modifyProById(Connection con,BookBean bean, String bookName);
	//��������Ʒ��Ϣ
	public int insertNewPro(Connection con,BookBean bean);
	//ɾ����Ʒ��Ϣ
	public int deleteProInfor(Connection con,String bookName)throws Exception;
	//����������ѯ��Ϣ
	public ResultSet managerBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//��ѯ�û��ϴ�ͼ��
	public ResultSet uploadBookList(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//����������ѯ�������
	public int managerBookCount(Connection con,PageBean pageBean,String bookName,String grade,String bookObject)throws Exception;
	//�����û��ϴ���Ϣ��Ʒ��Ϣ
	public int checkProInfor(Connection con,String bookName)throws Exception;
	
}
