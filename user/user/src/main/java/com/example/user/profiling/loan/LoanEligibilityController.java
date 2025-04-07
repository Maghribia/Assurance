package com.example.user.profiling.loan;

import com.example.user.profiling.userP.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
public class LoanEligibilityController {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private LoanEligibilityService loanEligibilityService;
/*
    @GetMapping("/{userId}")
    public String checkLoanEligibility(@PathVariable String userId) {
        UserProfile profile = userProfileRepository.findByUserId(userId);
        return (profile != null && loanEligibilityService.isEligibleForLoan(profile))
                ? "Éligible au crédit"
                : "Non éligible au crédit";
    }*/
}

