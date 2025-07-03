package icu.mikanku.gobangbackend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.mikanku.gobangbackend.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
