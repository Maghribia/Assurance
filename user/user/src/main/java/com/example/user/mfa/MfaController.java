package com.example.user.mfa;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mfa")
public class MfaController {

    private final MfaService mfaService;

    public MfaController(MfaService mfaService) {
        this.mfaService = mfaService;
    }

    @GetMapping("/setup/{username}/{email}")
    public String setupMfa(@PathVariable String username, @PathVariable String email) {
        String secretKey = mfaService.generateSecretKey(username, email);
        return mfaService.getQrCodeUrl(username, secretKey);
    }

}
