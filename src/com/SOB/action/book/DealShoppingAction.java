package com.SOB.action.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SOB.bean.BookBean;
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.Cart;
import com.SOB.util.DataBaseConnManager;


public class DealShoppingAction {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
			String bookName = request.getParameter("bookName");
			String id = request.getParameter("bookId");
			List<BookBean> products_list;
			HttpSession session = request.getSession();
			if(session.getAttribute("user_id") !=null){
				Connection con = DataBaseConnManager.getConnection_2();
				BooksDaoIface bookDao = new BooksDaoImpl();
	//			products_list = (List<BookBean>)session.getAttribute("products");//��session��ȡ��֮ǰ�����������Ʒ����ļ���
				products_list = bookDao.findProByCategory(con);
				System.out.println(" DealShoppingAction  products_list 's length is :"+products_list.size());
				
				Cart cart = (Cart)session.getAttribute("myCart");			//��һ��������ʱ����session����û�����cart����ġ�
				String action = request.getParameter("actionName");
				
				System.out.println("action is :   "+action);
				
				if("delete".equals(action)){
					cart.removeProFromCart(id);
					RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/UserCart.jsp");
					rd.forward(request, response);
				}else if("add".equals(action)){
					if(cart == null){
						cart = new Cart();
						session.setAttribute("myCart",cart);
					}
					Iterator<BookBean> it = products_list.iterator();
					
					while(it.hasNext()){
						BookBean products = it.next();
						if(products.getBookName().equals(bookName)){
							cart.addProToCart(products);
						}
					}
					System.out.println("++++++++++++++"+cart.getAllProductFromCart().size());
				}
				
			      response.setCharacterEncoding("UTF-8");
			      PrintWriter out = response.getWriter();
			      out.print("{\"msg\":\"���ѳɹ�����"+bookName+"�����빺�ﳵ"+"!\"}");
			}else{
//				String msg = "�����ȵ�½��";
//				request.setAttribute("noLogin", msg);
//				RequestDispatcher rd = request.getRequestDispatcher("resource/jsp/UserMain.jsp");
//				rd.forward(request, response);
			      response.setCharacterEncoding("UTF-8");
			      PrintWriter out = response.getWriter();
			      out.print("{\"error\":\"true\"}");
			}
	}
}
