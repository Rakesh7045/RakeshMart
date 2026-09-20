package com.rakesh.rakeshmart.service.impl;

import com.rakesh.rakeshmart.dao.UserDAO;
import com.rakesh.rakeshmart.dto.RegisterRequestDTO;
import com.rakesh.rakeshmart.exception.AuthException;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.User;
import com.rakesh.rakeshmart.service.UserService;
import com.rakesh.rakeshmart.util.PasswordUtil;
import com.rakesh.rakeshmart.util.ValidationUtil;

import javax.servlet.http.HttpServletResponse;

/** F1: registration/login with BUYER/SELLER roles only. ADMIN is seed-only. */
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User register(RegisterRequestDTO request) throws ValidationException {
        if (ValidationUtil.isBlank(request.getName())) {
            throw new ValidationException("name", "Name is required");
        }
        if (!ValidationUtil.isValidEmail(request.getEmail())) {
            throw new ValidationException("email", "A valid email is required");
        }
        if (!ValidationUtil.isValidPassword(request.getPassword())) {
            throw new ValidationException("password", "Password must be at least 8 characters");
        }
        String role = request.getRole();
        if (role == null || !(role.equals("BUYER") || role.equals("SELLER"))) {
            throw new ValidationException("role", "Role must be BUYER or SELLER");
        }
        if (userDAO.existsByEmail(request.getEmail())) {
            throw new ValidationException("email", "An account with this email already exists");
        }

        User user = new User();
        user.setName(request.getName().trim());
        user.setEmail(request.getEmail().trim().toLowerCase());
        user.setPasswordHash(PasswordUtil.hash(request.getPassword()));
        user.setRole(User.Role.valueOf(role));

        return userDAO.insert(user);
    }

    @Override
    public User login(String email, String password) throws AuthException, ValidationException {
        if (!ValidationUtil.isValidEmail(email) || ValidationUtil.isBlank(password)) {
            throw new ValidationException("email", "Email and password are required");
        }
        User user = userDAO.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new AuthException("Invalid email or password", HttpServletResponse.SC_UNAUTHORIZED));

        if (!PasswordUtil.verify(password, user.getPasswordHash())) {
            throw new AuthException("Invalid email or password", HttpServletResponse.SC_UNAUTHORIZED);
        }
        return user;
    }
}
