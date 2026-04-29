package com.example.hcms.security;

import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class MFAService {
    public String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
    // TODO: Implement code verification & delivery (email/SMS)
}