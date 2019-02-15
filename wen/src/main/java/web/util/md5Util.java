package web.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.apache.shiro.crypto.hash.SimpleHash;



public class md5Util{

	
	 /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String name,String password){  
    	
		String algorithmName = "MD5";//加密算法
		Object source = password;//原始密码
		Object salt = name;//添加的盐值；
		int hashIterations = 1024;//加密次数
		SimpleHash result = new SimpleHash(algorithmName, source, salt, hashIterations);
		return result.toString();
    
    }
    
    // 测试主函数  
    public static void main(String args[]) {  
    	String md5Salt = md5Util.string2MD5("18283489214", "123456");
		System.out.println(md5Salt);
    }  
}
