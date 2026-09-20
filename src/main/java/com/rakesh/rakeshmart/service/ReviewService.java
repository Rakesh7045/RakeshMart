package com.rakesh.rakeshmart.service;

import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.Review;

import java.util.List;

public interface ReviewService {
    Review addReview(long userId, long productId, int rating, String comment) throws ValidationException;
    List<Review> forProduct(long productId);
}
