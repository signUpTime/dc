package com.qq.common.data.mapper;

import com.qq.common.domain.User;

public interface UserMapper {

	User selectUserByMail(User user);

	void insertUser(User user);

}
