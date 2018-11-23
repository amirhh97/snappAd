package com.snappad.dao.JpaRepositories;

import com.snappad.model.BuildingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingModel,Integer> {
}
