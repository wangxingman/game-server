package com.game.auth.service.impl;

import java.util.*;

import com.game.auth.repository.UserRepository;
import com.game.auth.service.UserService;
import com.game.common.encode.MD5Util;
import com.game.common.entity.user.User;
import com.game.core.exception.BadRequestException;
import com.game.core.exception.EntityExistException;
import com.game.core.exception.EntityNotFoundException;
import com.game.core.utils.jpa.JpaPageUtil;
import com.game.core.utils.jpa.QueryHelp;
import com.game.core.utils.jpa.criteria.auth.UserQueryCriteria;
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

    public static String PASSWORLD = "123456";

    @Autowired
    private UserRepository userRepository;

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
    public User addUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new EntityExistException(User.class, "username", user.getUsername());
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EntityExistException(User.class, "email", user.getEmail());
        }
        user.setAvatar("https://i.loli.net/2019/04/04/5ca5b971e1548.jpeg");
        user.setPassword(MD5Util.encode((PASSWORLD)));
        User save = userRepository.save(user);
        return save;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User user) {
        Optional<User> oUser = userRepository.findById(user.getId());
        if (Objects.isNull(oUser)) {
            throw new EntityNotFoundException(User.class, "用户的id", user.getId());
        }
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
        return user1;
    }

    /**
     * @Author: wx
     * @Date : 上午 10:05 2019/6/29 0029
     * @params:
     * @Desc :  操作的时候 有没有删除 用户的一系列的 链表信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new BadRequestException("删除用户失败！");
        }
    }

    @Override
    public User findByOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        User userCurrent = user.get();
        if (Objects.isNull(userCurrent)) {
            throw new EntityNotFoundException(User.class, "id", id);
        }
        return userCurrent;
    }

    @Override
    public List<User> findByAll() {
        List<User> userList = userRepository.findAll();
        if (Objects.isNull(userList)) {
            throw new BadRequestException("查询用户为");
        }
        return userList;
    }

    @Override
    public Object findByAllSearch(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return JpaPageUtil.toPage(page);
    }

    @Override
    public void updateByPass(User user, String newPass) {
        String password = MD5Util.encode(user.getPassword());
        User currentUser = this.findByOne(user.getId());
        String encode = MD5Util.encode(newPass);
        if (currentUser.getPassword() != password) {
            throw new BadRequestException("您输入的密码不正确");
        } else if (encode == currentUser.getPassword()) {
            throw new BadRequestException("不能修改同样的密码");
        }
        userRepository.updatePass(user.getUsername(), encode, new Date());
    }

    @Override
    public User findByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if(Objects.isNull(user)) {
            throw new EntityNotFoundException(User.class,"用户的名字userName",userName);
        }
        return user;
    }

}
