package com.qq.common.data.mapper;

import com.qq.common.domain.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	User selectUserByMail(User user);

	void insertUser(User user);

	void updateDestinationId(Map<String, Object> map);

	void updateDepartment(Map<String, Object> map);

	List<User> selectAllUsers();

}
