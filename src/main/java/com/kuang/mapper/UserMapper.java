package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.User;
import org.springframework.stereotype.Repository;

//继承BaseMapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
