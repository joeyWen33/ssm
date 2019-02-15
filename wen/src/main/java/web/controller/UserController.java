package web.controller;



	import java.io.IOException;
	import java.util.List;

	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;


	import com.alibaba.fastjson.JSONObject;

import web.entity.UserEntity;
import web.service.UserService;

	@Controller
	@RequestMapping("/user")
	public class UserController {

		@Autowired
		private UserService userService;
		/**
		 * query all information to user
		 * @param response
		 * @return
		 */
		@ResponseBody
		@RequestMapping("/queryall")
		public JSONObject QueryAll(HttpServletResponse response) {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
		
			System.out.println("00");
			
			List<UserEntity> queryAll = userService.queryAll();
			System.out.println(queryAll);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("all", queryAll);
			return jsonObject;
		}
		
		/**
		 *add information by user
		 *		 * @throws IOException 
		 */
		
		@ResponseBody
		@RequestMapping("insertuser")
		public void insertUser(@RequestParam("u_name") String u_name, @RequestParam("u_passwd") String u_passwd,
				@RequestParam("u_age") int u_age, @RequestParam("u_email") String u_email, @RequestParam("u_gender") String u_gender,
				@RequestParam("u_contact") String u_contact, @RequestParam("u_address") String u_address,
				HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			JSONObject jsonObject = new JSONObject();
			UserEntity user = new UserEntity();
			user.setU_address(u_address);
			user.setU_age(u_age);
			user.setU_contact(u_contact);
			user.setU_email(u_email);
			user.setU_gender(u_gender);
			user.setU_name(u_name);
			user.setU_passwd(u_passwd);
			
			//add not null
			if( (u_name != null && ! u_name.equals("")) && (u_passwd != null && !  u_passwd.equals(""))
					&& (u_contact !=null) && ! u_contact.equals("")) {
				//not repeat
				if(userService.queryContact(u_contact) == null) {
					userService.insertUser(user);
					jsonObject.put("status", "200");
					jsonObject.put("message", "add successful");
					response.getWriter().write(jsonObject.toString());
				}else {
					jsonObject.put("status", "401");
					jsonObject.put("message", "add failed");
					response.getWriter().write(jsonObject.toString());
				}
			
			}else {
				jsonObject.put("status", "402");
				jsonObject.put("message", "the contact exist!!,Please input again");
				response.getWriter().write(jsonObject.toString());
			}
			}
		
	
		
			/**
		 * update information for user
		 * @param userEntity
		 */
		public void updateUser(@RequestParam("u_name") String u_name,@RequestParam("u_passwd") String u_passwd,
				@RequestParam("u_age") Integer u_age,@RequestParam("u_email") String u_email,
				@RequestParam("u_gender") String u_gender,@RequestParam("u_contact") String u_contact,
				@RequestParam("u_address") String u_address,HttpServletRequest request ,HttpServletResponse response) {
			
			UserEntity user = new UserEntity();
			user.setU_address(u_address);
			user.setU_age(u_age);
			user.setU_contact(u_contact);
			
		}

}
