package com.rakesh.rakeshmart.controller;

import com.rakesh.rakeshmart.dao.DAOFactory;
import com.rakesh.rakeshmart.dto.ApiResponse;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.filter.AuthFilter;
import com.rakesh.rakeshmart.model.Review;
import com.rakesh.rakeshmart.service.ReviewService;
import com.rakesh.rakeshmart.service.impl.ReviewServiceImpl;
import com.rakesh.rakeshmart.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * F8: product reviews/ratings, restricted to delivered orders.
 * GET  /api/v1/reviews?productId=1   -> public, list reviews
 * POST /api/v1/reviews               -> body {productId, rating, comment}, buyer only
 */
@WebServlet("/api/v1/reviews")
public class ReviewServlet extends HttpServlet {

    private final ReviewService reviewService = new ReviewServiceImpl(DAOFactory.reviewDAO());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            long productId = Long.parseLong(req.getParameter("productId"));
            JsonUtil.writeJson(resp, HttpServletResponse.SC_OK, ApiResponse.ok(reviewService.forProduct(productId)));
        } catch (NumberFormatException | NullPointerException e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_BAD_REQUEST,
                    ApiResponse.fail("VALIDATION_ERROR", "productId query param is required"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long userId = AuthFilter.currentUserId(req);
        try {
            ReviewRequest body = JsonUtil.readJson(req, ReviewRequest.class);
            Review review = reviewService.addReview(userId, body.productId, body.rating, body.comment);
            JsonUtil.writeJson(resp, HttpServletResponse.SC_CREATED, ApiResponse.ok(review));
        } catch (ValidationException e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_BAD_REQUEST,
                    ApiResponse.fail("VALIDATION_ERROR", e.getField() + ": " + e.getMessage()));
        }
    }

    private static class ReviewRequest {
        long productId;
        int rating;
        String comment;
    }
}
