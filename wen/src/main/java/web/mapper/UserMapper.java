package web.mapper;

import java.util.List;

import web.entity.UserEntity;



public interface UserMapper {

	/**
	 * query all information to user
	 * @return
	 */
	
	public List<UserEntity> queryAll();
	
	/**
	 * add user information
	 * @param user
	 */
	public void insertUser(UserEntity userEntity);
	
	/**
	 * query contact by user
	 * @param u_contact
	 * @return
	 */
	public String queryContact(String u_contact);
	
	
	/**
	 * update information for user
	 * @param userEntity
	 */
	public void updateUser(UserEntity userEntity);
	
	
	/**
	 * query name by user
	 * @param u_id
	 * @return
	 */
	public UserEntity findByName(String u_name);
}
