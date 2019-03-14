package com.snappad.dao.JpaRepositories;

import com.snappad.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    @Query(value = "select  * from  user where usermobilenum=?1",nativeQuery = true)
    UserModel exist(String userNumber);
    @Query(value = "select * from user where token?1",nativeQuery = true)
    UserModel getUserModelByToken(String token);
    UserModel findByToken(String token);
}
