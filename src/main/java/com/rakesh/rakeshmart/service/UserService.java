package com.rakesh.rakeshmart.service;

import com.rakesh.rakeshmart.dto.RegisterRequestDTO;
import com.rakesh.rakeshmart.exception.AuthException;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.User;

public interface UserService {
    User register(RegisterRequestDTO request) throws ValidationException;
    User login(String email, String password) throws AuthException, ValidationException;
}
