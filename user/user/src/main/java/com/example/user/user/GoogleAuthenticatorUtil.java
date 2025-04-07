package com.example.user.user;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GoogleAuthenticatorUtil {
    public static String getOtpAuthUrl(String secret, String user, String issuer) {
        return String.format(
                "otpauth://totp/%s:%s?secret=%s&issuer=%s",
                URLEncoder.encode(issuer, StandardCharsets.UTF_8),
                URLEncoder.encode(user, StandardCharsets.UTF_8),
                secret,
                URLEncoder.encode(issuer, StandardCharsets.UTF_8)
        );
    }

    public static void main(String[] args) throws Exception {
        String secretKey = OTPUtil.generateSecretKey();
        String otpUrl = getOtpAuthUrl(secretKey, "selmimaher42@gmail.com", "MonApplication");
        System.out.println("Lien Google Authenticator: " + otpUrl);
    }
}
