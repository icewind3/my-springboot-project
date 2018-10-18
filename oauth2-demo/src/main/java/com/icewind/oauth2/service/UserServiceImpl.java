package com.icewind.oauth2.service;

import com.icewind.oauth2.entity.User;
import com.icewind.oauth2.repository.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author yjy
 * @date 2018/1/12.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "user", key = "#username")
    public User findByUsername(String username) {
        logger.info(username);
        return userDao.findByUsername(username);
    }

    @Cacheable(value = "username", key = "#username")
    public String findStringByUsername(String username) {
        logger.info(username);
        return username;
    }

    @Cacheable(value = "countCache")
    public long countA(String username, List<String> a) {
        return userDao.count();
    }

    @Cacheable(value = "countCache")
    public long countB(String username, List<String> a) {
        return userDao.count();
    }

    public User findOne(Long id) throws RuntimeException {
        User user;
        Optional<User> optional = userDao.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        } else {
            throw new RuntimeException("用户不存在！");
        }
        return user;
//        return userDao.getOne(id);
    }

    public void deleteOne(Long id) {
        userDao.deleteById(id);
    }

    public User updateUser(User user){
        return userDao.save(user);
    }

    public User addUser(User user){
        return userDao.saveAndFlush(user);
    }

}
