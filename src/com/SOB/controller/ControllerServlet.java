package com.SOB.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SOB.action.book.AddBookAction;
import com.SOB.action.book.CheckBookAction;
import com.SOB.action.book.DealShoppingAction;
import com.SOB.action.book.DeleteBoookAction;
import com.SOB.action.book.DeleteOrderAction;
import com.SOB.action.book.EditBookAction;
import com.SOB.action.book.ListBooksAction;
import com.SOB.action.book.ListOrderAction;
import com.SOB.action.book.ManagerBookListAction;
import com.SOB.action.book.UploadBookListAction;
import com.SOB.action.user.AddUserAction;
import com.SOB.action.user.DeleteUserAction;
import com.SOB.action.user.EditUserAction;
import com.SOB.action.user.ListBookAction;
import com.SOB.action.user.LoginAction;
import com.SOB.action.user.LogoutAction;
import com.SOB.action.user.MyCartAction;
import com.SOB.action.user.RegistAction;
import com.SOB.action.user.SubmitOrderAction;
import com.SOB.action.user.UserUploadAccessAction;
import com.SOB.action.user.UserUploadAction;


public class ControllerServlet extends HttpServlet {
	
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException{
		
		String requestURI = request.getRequestURI(); //  /TingTaoBookStore0808/test.mis
		
		String contextName = request.getContextPath(); //app's name
		
		String action_uri =  requestURI.substring(contextName.length(), requestURI.length()-4);
		
		System.out.println("action_uri is :-----"+action_uri);
		
		if(action_uri.equals("/regist")){
			RegistAction regist = new RegistAction();
			regist.execute(request, response);
		}
		if(action_uri.equals("/login")){
			LoginAction login = new LoginAction();
			login.execute(request, response);
		}
		if(action_uri.equals("/searchBook")){
			ListBooksAction lba = new ListBooksAction();
			lba.execute(request, response);
		}
		if(action_uri.equals("/myCart")){
			MyCartAction mc = new MyCartAction();
			mc.execute(request, response);
		}
		if(action_uri.equals("/doShopping")){
			DealShoppingAction dealShoppingAction = new DealShoppingAction();
			dealShoppingAction.execute(request, response);
		}
		if(action_uri.equals("/userList")){
			ListBookAction lb = new ListBookAction();
			lb.execute(request, response);
		}
		if(action_uri.equals("/deleteUser")){
			DeleteUserAction du = new DeleteUserAction();
			du.execute(request, response);
		}
		if(action_uri.equals("/addUser")){
			AddUserAction du = new AddUserAction();
			du.execute(request, response);
		}
		if(action_uri.equals("/editUser")){
			EditUserAction du = new EditUserAction();
			du.execute(request, response);
		}
		if(action_uri.equals("/managerBookList")){
			ManagerBookListAction mb = new ManagerBookListAction();
			mb.execute(request, response);
		}
		if(action_uri.equals("/addBook")){
			AddBookAction ab = new AddBookAction();
			ab.doPost(request, response);
		}
		if(action_uri.equals("/editBook")){
			EditBookAction ab = new EditBookAction();
			ab.doPost(request, response);
		}
		if(action_uri.equals("/deleteBook")){
			DeleteBoookAction db = new DeleteBoookAction();
			db.execute(request, response);
		}
		if(action_uri.equals("/userUpload")){
			UserUploadAction db = new UserUploadAction();
			db.doPost(request, response);
		}
		if(action_uri.equals("/uploadBookList")){
			UploadBookListAction db = new UploadBookListAction();
			db.execute(request, response);
		}
		if(action_uri.equals("/checkBook")){
			CheckBookAction db = new CheckBookAction();
			db.execute(request, response);
		}
		if(action_uri.equals("/logout")){
			LogoutAction logoutAction = new LogoutAction();
			logoutAction.execute(request, response);
		}
		if(action_uri.equals("/userUploadAccess")){
			UserUploadAccessAction uula = new UserUploadAccessAction();
			uula.execute(request, response);
		}
		if(action_uri.equals("/submitOrder")){
			SubmitOrderAction uula = new SubmitOrderAction();
			uula.execute(request, response);
		}
		if(action_uri.equals("/orderList")){
			ListOrderAction mb = new ListOrderAction();
			mb.execute(request, response);
		}
		if(action_uri.equals("/deleteOrder")){
			DeleteOrderAction db = new DeleteOrderAction();
			db.execute(request, response);
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
													throws ServletException, IOException {
		this.processRequest(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
													throws ServletException, IOException {
		this.processRequest(request, response);
	}

}
