package com.icewind.oauth2.repository;

import com.icewind.oauth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yjy
 * @date 2017/6/12
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, CrudRepository<User, Long> {

    User findByUsername(String username);
}
