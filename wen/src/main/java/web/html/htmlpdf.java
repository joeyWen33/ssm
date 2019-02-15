package web.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class htmlpdf {
	//wkhtmltopdf在系统中的路径
		//需要先安装这个wkhtmltopdf程序 以管理员的身份运行
    private static final String toPdfTool = "C:\\Users\\Administrator\\Downloads\\wkhtmltopdf.exe";
    
    
    public static class HtmlToPdfInterceptor extends Thread {  
	    private InputStream is;  
	      
	    public HtmlToPdfInterceptor(InputStream is){  
	        this.is = is;  
	    }  
	      
	    public void run(){  
	        try{  
	        	//确定以文件流的方式传输，确定编码格式为utf-8
	            InputStreamReader isr = new InputStreamReader(is, "utf-8");  
	            BufferedReader br = new BufferedReader(isr);  
	            String line = null;  
	            while ((line = br.readLine()) != null) {  
	                System.out.println("------->"+line.toString()); //输出内容
	            }  
	        }catch (IOException e){  
	            e.printStackTrace();  
	        }  
	    }  
    
    }
    
    /**
     * html转pdf
     * @param srcPath html路径，可以是硬盘上的路径，也可以是网络路径
     * @param destPath pdf保存路径
     * @return 转换成功返回true
     */
    public static  boolean convert(String srcPath, String destPath){
        File file = new File(destPath);
        File parent = file.getParentFile();
        //如果pdf保存路径不存在，则创建路径
        if(!parent.exists()){
            parent.mkdirs();
        }
        
        StringBuilder cmd = new StringBuilder();
        //调用这个程序
        cmd.append(toPdfTool);
        cmd.append(" ");
        cmd.append("  --header-line");//页眉下面的线
        cmd.append("  --header-center 这里是页眉这里是页眉这里是页眉这里是页眉 ");//页眉中间内容
        //cmd.append("  --margin-top 30mm ");//设置页面上边距 (default 10mm) 
        cmd.append(" --header-spacing 10 ");//    (设置页眉和内容的距离,默认0)
        //需要转换的HTML文件路径
        cmd.append(srcPath);
        cmd.append(" ");
        //转换完成的PDF路径
        cmd.append(destPath);
        
        boolean result = true;
        try{
            Process proc = Runtime.getRuntime().exec(cmd.toString());
            HtmlToPdfInterceptor error = new HtmlToPdfInterceptor(proc.getErrorStream());
            HtmlToPdfInterceptor output = new HtmlToPdfInterceptor(proc.getInputStream());
         
            error.start();
            output.start();
            proc.waitFor();
        }catch(Exception e){
            result = false;
            e.printStackTrace();
        }
        
        return result;
    }
  
   
    
    public static void main(String[] args) {
    	htmlpdf.convert("\\https://music.163.com/", "F:\\d.pdf");
    	
    }
    
}