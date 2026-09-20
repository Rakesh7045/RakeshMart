package com.rakesh.rakeshmart.service.impl;

import com.rakesh.rakeshmart.dao.ReviewDAO;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.Review;
import com.rakesh.rakeshmart.service.ReviewService;
import com.rakesh.rakeshmart.util.ValidationUtil;

import java.util.List;

/** F8: product reviews and star ratings, restricted to completed (DELIVERED) orders. */
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    @Override
    public Review addReview(long userId, long productId, int rating, String comment) throws ValidationException {
        if (!ValidationUtil.isValidRating(rating)) {
            throw new ValidationException("rating", "Rating must be between 1 and 5");
        }
        if (!reviewDAO.buyerCompletedOrderForProduct(userId, productId)) {
            throw new ValidationException("order", "You can only review products from delivered orders");
        }
        if (reviewDAO.hasReviewedFromCompletedOrder(userId, productId)) {
            throw new ValidationException("review", "You have already reviewed this product");
        }

        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setRating(rating);
        review.setComment(comment);
        return reviewDAO.insert(review);
    }

    @Override
    public List<Review> forProduct(long productId) {
        return reviewDAO.findByProduct(productId);
    }
}
