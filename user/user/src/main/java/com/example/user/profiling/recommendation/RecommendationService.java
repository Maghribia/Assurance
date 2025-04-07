package com.example.user.profiling.recommendation;

import com.example.user.profiling.userP.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private InsuranceProductRepository insuranceProductRepository;
/*
    public List<InsuranceProduct> recommendProducts(String userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId);
        if (profile == null) return Collections.emptyList();

        return insuranceProductRepository.findAll().stream()
                .filter(product -> isEligible(profile, product))
                .collect(Collectors.toList());
    }*/
/*
    private boolean isEligible(UserProfile profile, InsuranceProduct product) {
        for (String criteria : product.getEligibilityCriteria()) {
            if (criteria.equals("hasCar") && !profile.isHasCar()) return false;
            if (criteria.startsWith("age>") && profile.getAge() <= Integer.parseInt(criteria.substring(4))) return false;
        }
        return true;
    }*/
}


