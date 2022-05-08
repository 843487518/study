package com.zxk.study.mapper;

import com.zxk.study.module.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    public UserDto selectOne(UserDto userDto);
    public List<UserDto > selectAll(UserDto userDto);
    public int update(UserDto user);
}
