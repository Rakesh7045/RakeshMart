package com.rakesh.rakeshmart.controller;

import com.rakesh.rakeshmart.dao.DAOFactory;
import com.rakesh.rakeshmart.dto.ApiResponse;
import com.rakesh.rakeshmart.dto.RegisterRequestDTO;
import com.rakesh.rakeshmart.dto.UserResponseDTO;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.User;
import com.rakesh.rakeshmart.service.UserService;
import com.rakesh.rakeshmart.service.impl.UserServiceImpl;
import com.rakesh.rakeshmart.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** F1: buyer/seller registration. Servlet stays thin - no SQL, no business rules. */
@WebServlet("/api/v1/auth/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl(DAOFactory.userDAO());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            RegisterRequestDTO request = JsonUtil.readJson(req, RegisterRequestDTO.class);
            User user = userService.register(request);
            JsonUtil.writeJson(resp, HttpServletResponse.SC_CREATED, ApiResponse.ok(UserResponseDTO.from(user)));
        } catch (ValidationException e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_BAD_REQUEST,
                    ApiResponse.fail("VALIDATION_ERROR", e.getField() + ": " + e.getMessage()));
        } catch (Exception e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    ApiResponse.fail("SERVER_ERROR", "Registration failed"));
        }
    }
}
