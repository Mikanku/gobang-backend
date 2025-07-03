package icu.mikanku.gobangbackend.user.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.mikanku.gobangbackend.user.entity.User;
import icu.mikanku.gobangbackend.user.mapper.UserMapper;
import icu.mikanku.gobangbackend.user.util.JWTUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements UserDetailsService  {

    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User selectByUsername(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }

    public void register(String username, String password) {
        User user = selectByUsername(username);
        if (user != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        if (StrUtil.isBlank(password)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        save(newUser);
    }

    public String login(String username, String password) {
        User user = selectByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在或密码错误");
        }
        boolean isMatch = passwordEncoder.matches(password, user.getPassword());
        if (!isMatch) {
            throw new UsernameNotFoundException("用户不存在或密码错误");
        }
        return JWTUtil.generateToken(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return selectByUsername(username);
    }
}
