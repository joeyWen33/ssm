package web.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import web.entity.UserEntity;
import web.service.UserService;

public class UserRealm extends AuthorizingRealm{

	@Autowired
	private UserService  userService;
	/**
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthenticationInfo  authenticationInfo=null;
		return   (AuthorizationInfo) authenticationInfo;
	}

	/**
	 * 身份验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authentication) throws AuthenticationException {

		UsernamePasswordToken token=(UsernamePasswordToken) authentication;
	UserEntity user = userService.findByName(token.getUsername());
	
	System.out.println(user);
	if(user == null) {
	
		//没有找到账号
		throw new AuthenticationException();
	}
	ByteSource source = ByteSource.Util.bytes(user.getU_contact());
	SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getU_contact(),user.getU_passwd(), source, getName());
     
	return authenticationInfo;
	}

}
