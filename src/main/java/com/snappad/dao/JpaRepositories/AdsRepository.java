package com.snappad.dao.JpaRepositories;

import com.snappad.model.AdsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepository extends JpaRepository<AdsModel,Integer> {
}
