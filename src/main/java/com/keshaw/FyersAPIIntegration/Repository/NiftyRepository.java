package com.keshaw.FyersAPIIntegration.Repository;

import com.keshaw.FyersAPIIntegration.Model.APIModel.NiftyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NiftyRepository extends JpaRepository<NiftyData, Integer> {
}
