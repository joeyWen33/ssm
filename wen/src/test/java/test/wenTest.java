package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import web.entity.UserEntity;
import web.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:configure/spring*.xml","classpath:configure/mybaits.xml"})
public class wenTest {

	@Autowired
	private UserService userService;
	
/*	@Test
	public void run1() {
		UserEntity userEntity = new UserEntity();
		userEntity.setU_address("北京");
		userEntity.setU_age(15);
		userEntity.setU_contact("19950149120");
		userEntity.setU_email("when@163.com");
		userEntity.setU_gender("男");
		userEntity.setU_name("理查德");
		userEntity.setU_passwd("123456");
		userService.insertUser(userEntity);
		
	}*/
	
	@Test
	public void run2() {
		String queryContact = userService.queryContact("19950149120");
		System.out.println(queryContact);
		
	}
}
