package com.example.cosmeticapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml2.provider.service.authentication.Saml2AuthenticationToken;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/saml2")
    public void saml2Login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Chuyển hướng người dùng đến IdP để thực hiện đăng nhập
        // Spring Security SAML2 sẽ tự động xử lý điều này nếu bạn cấu hình đúng
        response.sendRedirect("/saml2/login"); // Spring Security sẽ tự động xử lý chuyển hướng
    }
}

