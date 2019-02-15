package web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadHandleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String path = this.getServletContext().getRealPath("WEB-INF/upload");
    File file = new File(path);
    if(!file.exists()&& !file.isDirectory()) {
    	System.out.println("目录或文件不存在");
    	//如果文件不存在的话就，就创建文件
    	file.mkdirs();
    }
    String message = "";
    try {
    	
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		if(!servletFileUpload.isMultipartContent(request)) {
			return ;
		}
		List<FileItem> list = servletFileUpload.parseRequest(request);
		for (FileItem item : list) {
			if(item.isFormField()) {
				String name = item.getFieldName();
				String value = item.getString("UTF-8");
				String string = new String(name.getBytes("iso8859-1"),"UTF-8");
				System.out.println(value);
				System.out.println(string);
			}else {
				
				String name = item.getName();
				System.out.println(name);
				if(name == null || name.trim().equals("")) {
					continue;
				}
				
				String fileName = name.substring(name.lastIndexOf(File.separator)+1);
				InputStream is = item.getInputStream();
				FileOutputStream out = new FileOutputStream(path+file.separator+fileName);
				
				//创建一个缓冲区
				byte buffer[] =new byte[1024];
				int length = 0;
				while((length = is.read(buffer))>0) {
					out.write(buffer,0,length);
				}
				
				is.close();
				out.close();
				item.delete();
				message ="文件上传成功";
			}
			
		}
    } catch (Exception e) {
		e.printStackTrace();
		message = "文件上传失败";
	}
    request.setAttribute("message", message);
    request.getRequestDispatcher("/").forward(request, response);
	}
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doGet(request, response);
 }
}
