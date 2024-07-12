package org.example.gateway.controller;

import lombok.Data;
import org.example.gateway.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EncoderController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Data
    static
    class PasswordRequest {
        private String plainPassword; // 未加密的密码
        private String encodedPassword; // 从数据库取出的加密密码

        public PasswordRequest(String plainPassword, String encodedPassword) {
            this.plainPassword = plainPassword;
            this.encodedPassword = encodedPassword;
        }

        public PasswordRequest() {
        }
    }

    @PostMapping("/internal/gateway/matchPassword")
    public Boolean matchPassword(@RequestBody PasswordRequest passwordRequest) {
        System.out.println(passwordRequest);
        // 这里实现你的编码逻辑
        System.out.println("plainPassword: " + passwordRequest.getPlainPassword());
        System.out.println("encodedPassword: " + passwordRequest.getEncodedPassword());
        return passwordEncoder.matches(passwordRequest.getPlainPassword(), passwordRequest.getEncodedPassword());
    }

    @PostMapping("/internal/gateway/generateToken")
    public String generateToken(@RequestBody Integer userID) {
        System.out.println("userID: " + userID);
        String token = jwtTokenUtil.generateToken(userID);
//        String token = "";
        System.out.println("token: " + token);
        return token;
    }

    @PostMapping("/internal/gateway/encode")
    public String encode(@RequestBody String password) {
        return passwordEncoder.encode(password);
    }
}
