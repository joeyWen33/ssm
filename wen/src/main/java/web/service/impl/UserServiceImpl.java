package web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import web.entity.UserEntity;
import web.mapper.UserMapper;
import web.service.UserService;
import web.util.md5Util;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserMapper userMapper;
	public List<UserEntity> queryAll() {
		
		return userMapper.queryAll();
	}

	public void insertUser(UserEntity userEntity) {
		//密码加密
		String u_passwd = userEntity.getU_passwd();
		String u_name = userEntity.getU_name();
		String string2md5 = md5Util.string2MD5(u_name, u_passwd);
		userEntity.setU_passwd(string2md5);
		userMapper.insertUser(userEntity);
		
	}

	@Override
	public String queryContact(String u_contact) {
		return userMapper.queryContact(u_contact);
	}

	@Override
	public void updateUser(UserEntity userEntity) {
		
		userMapper.updateUser(userEntity);
	}

	@Override
	public UserEntity findByName(String u_name) {
		
		return userMapper.findByName(u_name);
	}




}
