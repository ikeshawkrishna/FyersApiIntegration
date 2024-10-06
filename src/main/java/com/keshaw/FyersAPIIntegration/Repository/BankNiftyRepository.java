package com.keshaw.FyersAPIIntegration.Repository;

import com.keshaw.FyersAPIIntegration.Model.APIModel.BankNiftyData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankNiftyRepository extends JpaRepository<BankNiftyData, Integer> {
}
