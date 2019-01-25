package com.snappad.dao.JpaRepositories;

import com.snappad.model.DistrictModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictModel, Integer> {
    @Query(value = "select * from district where cityid=?1&&district.Name=?2",nativeQuery = true)
    DistrictModel findByCity(int cityId, String districtName);
}
