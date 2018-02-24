package com.SOB.action.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import com.SOB.bean.BookBean;
import com.SOB.dao.book.BooksDaoIface;
import com.SOB.dao.book.BooksDaoImpl;
import com.SOB.util.DataBaseConnManager;
import com.SOB.util.ResponseUtil;

public class UserUploadAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String filePath = "G:\\eclipse\\workbench\\SchoolOldBook\\SchoolOldBook\\WebContent\\resource\\upload";    // �ļ����Ŀ¼    
	private String tempPath = "G:\\eclipse\\workbench\\SchoolOldBook\\SchoolOldBook\\WebContent\\resource\\backup";    // ��ʱ�ļ�Ŀ¼  
	
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
	}
	
    public void init(ServletConfig config) throws ServletException    
    {    
        super.init(config);    
        // �������ļ��л�ó�ʼ������    
        filePath = config.getInitParameter("filepath");    
        tempPath = config.getInitParameter("temppath");    
   
        ServletContext context = getServletContext();    
   
        filePath = context.getRealPath(filePath);    
        tempPath = context.getRealPath(tempPath);    
          
        //���·�������ڣ��򴴽�·��  
        File pathFile = new File(filePath);  
        File pathTemp = new File(tempPath);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        if(!pathTemp.exists()){  
            pathTemp.mkdirs();  
        }  
        System.out.println("�ļ����Ŀ¼����ʱ�ļ�Ŀ¼׼����� ...");    
    }    
        
    // doPost    
    public void doPost(HttpServletRequest req, HttpServletResponse res)    
        throws IOException, ServletException    
    {   
    	Connection con = DataBaseConnManager.getConnection_2();
    	Map bookMap = new HashMap();
//    	req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain;charset=UTF-8");    
        PrintWriter pw = res.getWriter();
        JSONObject resultset = new JSONObject();
        int up = 0;
        String filename = null;
        try{    
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();    
            // threshold ���ޡ��ٽ�ֵ����Ӳ�̻��� 1G   
            diskFactory.setSizeThreshold(1000 * 1024 * 1024);    
            // repository �����ң�����ʱ�ļ�Ŀ¼    
            diskFactory.setRepository(new File(tempPath));    
            
            ServletFileUpload upload = new ServletFileUpload(diskFactory);    
            // ���������ϴ�������ļ���С 1G   
            upload.setSizeMax(1000 * 1024 * 1024);    
            // ����HTTP������Ϣͷ    
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));    
            Iterator<FileItem> iter = fileItems.iterator();    
            while(iter.hasNext())    
            {    
                FileItem item = (FileItem)iter.next();    
                if(!item.isFormField()){
                    System.out.println("�����ϴ����ļ� ...");
                    filename = item.getName();
                   up = processUploadFile(item, pw);   
                }else{
                	this.processFormField(item, bookMap);
                }
            }// end while()
            String userId = (String)bookMap.get("userid");
            String bookName = (String)bookMap.get("bookname");
            String grade = (String)bookMap.get("grade");
            String bookObject = (String)bookMap.get("bookobject");
            String price = (String)bookMap.get("price");
            String physical = (String)bookMap.get("physical");
            String desc = (String)bookMap.get("description");
            String url = "resource/upload/"+filename;
            BookBean bb = new BookBean(0, bookName, grade, bookObject,
					price, desc, physical, "0",userId, url);
            BooksDaoIface bd = new BooksDaoImpl();
            int addBook = bd.insertNewPro(con, bb);
            if(addBook>0 && up == 1){
				req.setAttribute("uploadSuccess", "���ѳɹ��ϴ�����Ϊ��"+bookName+"���ľ���");
            }
			RequestDispatcher rd = req.getRequestDispatcher("resource/jsp/UserMain.jsp");
			BooksDaoIface bookDao = new BooksDaoImpl();
			List<BookBean> list = bookDao.getAllBooks(con, null, null, null);
			req.setAttribute("indexBookList", list);
			rd.forward(req, res);
            pw.close();    
        }catch(Exception e){    
            System.out.println("ʹ�� fileupload ��ʱ�����쳣 ...");    
            e.printStackTrace();    
        }// end try ... catch ...    
    }// end doPost()    
   
   
    // ���������    
    private void processFormField(FileItem item, Map bookMap)    
        throws Exception    
    {    
        String name = item.getFieldName();    
        String value = item.getString("UTF-8");
        bookMap.put(name, value);
    }    
        
    // �����ϴ����ļ�    
    private int processUploadFile(FileItem item, PrintWriter pw)    
        throws Exception    
    {    
        // ��ʱ���ļ���������������·������ע��ӹ�һ��    
        String filename = item.getName();           
        System.out.println("�������ļ�����" + filename);    
        int index = filename.lastIndexOf("\\");    
        filename = filename.substring(index + 1, filename.length());    
   
        long fileSize = item.getSize();    
   
        if("".equals(filename) && fileSize == 0)    
        {               
            System.out.println("�ļ���Ϊ�� ...");    
            return 0;    
        }    
   
        File uploadFile = new File(filePath + "/" + filename);    
        if(!uploadFile.exists()){  
            uploadFile.createNewFile();  
        }  
        item.write(uploadFile);
        return 1;
    }    
        
    // doGet    
    public void doGet(HttpServletRequest req, HttpServletResponse res)    
        throws IOException, ServletException    
    {    
        doPost(req, res);    
    }    

}
