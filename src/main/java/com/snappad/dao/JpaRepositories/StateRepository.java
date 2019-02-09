package com.snappad.dao.JpaRepositories;

import com.snappad.model.StateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<StateModel,Integer> {
}
