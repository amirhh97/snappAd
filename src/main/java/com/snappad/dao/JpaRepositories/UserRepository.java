package com.snappad.dao.JpaRepositories;

import com.snappad.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {

    @Query(value = "select  * from  user where usermobilenum=?1",nativeQuery = true)
    UserModel exist(String userNumber);
}
