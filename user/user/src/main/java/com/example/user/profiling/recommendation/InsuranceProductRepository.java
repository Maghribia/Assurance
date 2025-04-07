package com.example.user.profiling.recommendation;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceProductRepository extends JpaRepository<InsuranceProduct, String> {
}

