package com.SOB.action.book;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

public class AddBookAction extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private String filePath = "G:\\eclipse\\workbench\\SchoolOldBook\\SchoolOldBook\\WebContent\\resource\\upload";    // 文件存放目录    
	private String tempPath = "G:\\eclipse\\workbench\\SchoolOldBook\\SchoolOldBook\\WebContent\\resource\\backup";    // 临时文件目录   
	
	public void execute(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		
	}
	
    public void init(ServletConfig config) throws ServletException    
    {    
        super.init(config);    
        // 从配置文件中获得初始化参数    
        filePath = config.getInitParameter("filepath");    
        tempPath = config.getInitParameter("temppath");    
   
        ServletContext context = getServletContext();    
   
        filePath = context.getRealPath(filePath);    
        tempPath = context.getRealPath(tempPath);    
          
        //如果路径不存在，则创建路径  
        File pathFile = new File(filePath);  
        File pathTemp = new File(tempPath);  
        if(!pathFile.exists()){  
            pathFile.mkdirs();  
        }  
        if(!pathTemp.exists()){  
            pathTemp.mkdirs();  
        }  
        System.out.println("文件存放目录、临时文件目录准备完毕 ...");    
    }    
        
    // doPost    
    public void doPost(HttpServletRequest req, HttpServletResponse res)    
        throws IOException, ServletException    
    {   
    	Connection con = DataBaseConnManager.getConnection_2();
    	Map bookMap = new HashMap();
//    	req.setCharacterEncoding("UTF-8");
//        res.setContentType("text/plain;charset=UTF-8");    
        PrintWriter pw = res.getWriter();
        JSONObject resultset = new JSONObject();
        int up = 0;
        String filename = null;
        try{    
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();    
            // threshold 极限、临界值，即硬盘缓存 1G   
            diskFactory.setSizeThreshold(1000 * 1024 * 1024);    
            // repository 贮藏室，即临时文件目录    
            diskFactory.setRepository(new File(tempPath));    
            
            ServletFileUpload upload = new ServletFileUpload(diskFactory);    
            // 设置允许上传的最大文件大小 1G   
            upload.setSizeMax(1000 * 1024 * 1024);    
            // 解析HTTP请求消息头    
            List<FileItem> fileItems = upload.parseRequest(new ServletRequestContext(req));    
            Iterator<FileItem> iter = fileItems.iterator();    
            while(iter.hasNext())    
            {    
                FileItem item = (FileItem)iter.next();    
                if(!item.isFormField()){
                    System.out.println("处理上传的文件 ...");
                    filename = item.getName();
                   up = processUploadFile(item, pw);   
                }else{
                	this.processFormField(item, bookMap);
                }
            }// end while()
            String bookName = (String)bookMap.get("bookname");
            String grade = (String)bookMap.get("grade");
            String bookObject = (String)bookMap.get("bookobject");
            String price = (String)bookMap.get("price");
            String physical = (String)bookMap.get("physical");
            String desc = (String)bookMap.get("description");
            String url = "resource/upload/"+filename;
            BookBean bb = new BookBean(0, bookName, grade, bookObject,
					price, desc, physical, "1","manager", url);
            BooksDaoIface bd = new BooksDaoImpl();
            int addBook = bd.insertNewPro(con, bb);
            if(addBook>0 && up == 1){
            	resultset.put("success", "true");
            }
            ResponseUtil.write(res, resultset);
            pw.close();    
        }catch(Exception e){    
            System.out.println("使用 fileupload 包时发生异常 ...");    
            e.printStackTrace();    
        }// end try ... catch ...    
    }// end doPost()    
   
   
    // 处理表单内容    
    private void processFormField(FileItem item, Map bookMap)    
        throws Exception    
    {    
        String name = item.getFieldName();    
        String value = item.getString("UTF-8");
        bookMap.put(name, value);
    }    
        
    // 处理上传的文件    
    private int processUploadFile(FileItem item, PrintWriter pw)    
        throws Exception    
    {    
        // 此时的文件名包含了完整的路径，得注意加工一下    
        String filename = item.getName();           
        System.out.println("完整的文件名：" + filename);    
        int index = filename.lastIndexOf("\\");    
        filename = filename.substring(index + 1, filename.length());    
   
        long fileSize = item.getSize();    
   
        if("".equals(filename) && fileSize == 0)    
        {               
            System.out.println("文件名为空 ...");    
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
