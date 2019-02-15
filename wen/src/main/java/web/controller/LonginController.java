package web.controller;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import web.util.md5Util;

@Controller

public class LonginController {
	private static Logger logger = Logger.getLogger(LonginController.class);

	/**
	 * user logining 
	 * @param u_name
	 * @param u_password
	 * @return
	 */
	@RequestMapping("/loginin")
	@ResponseBody
	public JSONObject login(@RequestParam(value = "username", required = false) String u_name,
			@RequestParam(value = "password", required = false) String u_password) {

		System.out.println("進入方法");
		System.out.println(u_name + "," + u_password);

		String error = null;
		HashMap<String, Object> map = new HashMap<>();

		if (u_name != null && u_password != null) {
			Subject subject = SecurityUtils.getSubject();
			
			UsernamePasswordToken token = new UsernamePasswordToken(u_name, u_password);
			

			try {
				// 登录验证
				subject.login(token);
				// 登录成功进入首页
				map.put("status", "200");
				logger.info("successful login in ");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("未知错误，错误信息：" + e.getMessage());
				error = e.getMessage();
				map.put("status", "401");
				map.put("message", "账号或密码输入错误");
			}
		} else {
			logger.error("please input username and pwd");
		}

		logger.info("return layout");
		JSONObject object = new JSONObject(map);
        
		return object;
	}
}
