package com.zxk.study.service;

import com.zxk.study.module.dto.UserDto;

import java.util.List;

public interface UserService {

    public UserDto getUserById(UserDto user);
    public List<UserDto> getUserAll(int page);
    public int update(UserDto user);
}
