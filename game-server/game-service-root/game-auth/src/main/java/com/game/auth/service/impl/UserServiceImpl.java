package com.game.auth.service.impl;

import java.util.*;

import com.game.auth.mapper.UserMapper;
import com.game.auth.repository.UserRepository;
import com.game.auth.search.UserQueryCriteria;
import com.game.auth.service.UserService;
import com.game.common.dto.user.UserDto;
import com.game.common.encode.MD5Util;
import com.game.common.entity.user.User;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : wx
 * @Desc :
 * @Date :  上午 10:30 2019/6/20 0020
 * @explain :
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public User fastLogin(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (Objects.isNull(user)) {
            log.info("该用户不存在！");
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto addUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", user.getUsername());
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", user.getEmail());
        }
        user.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        user.setPassword(MD5Util.encode((user.getPassword())));
        User save = userRepository.save(user);
        return userMapper.toDto(save);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserDto updateUser(User user) {
        Optional<User> oUser = userRepository.findById(user.getId());
        Optional.ofNullable(oUser).ifPresent(null);
        User currentUser = oUser.get();

        User currentUserName = userRepository.findByUsername(user.getUsername());
        if (currentUser != null && !currentUserName.getId().equals(currentUser.getId())) {
            throw new EntityExistException(User.class, "username", user.getUsername());
        }
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setEnabled(user.getEnabled());
        currentUser.setRoles(user.getRoles());
        currentUser.setDept(user.getDept());
        currentUser.setJob(user.getJob());
        currentUser.setPhone(user.getPhone());

        User user1 = userRepository.saveAndFlush(currentUser);
        return userMapper.toDto(user1);
    }

    /**
     * @Author: wx
     * @Date  : 上午 10:05 2019/6/29 0029 
     * @params: 
     * @Desc  :  操作的时候 有没有删除 用户的一系列的 链表信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        User userCurrent = user.get();
        if(Objects.isNull(userCurrent)) {
            throw new EntityNotFoundException(User.class,"id",id);
        }
        return userCurrent;
    }

    @Override
    public List<User> findByAll() {
        return null;
    }

    @Override
    public Object findByAllSearch(UserQueryCriteria criteria, Pageable pageable) {
        return null;
    }

}
