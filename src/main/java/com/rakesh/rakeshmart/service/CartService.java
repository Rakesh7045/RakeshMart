package com.rakesh.rakeshmart.service;

import com.rakesh.rakeshmart.exception.NotFoundException;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.CartItem;

import java.util.List;

public interface CartService {
    void addItem(long userId, long productId, int quantity) throws ValidationException, NotFoundException;
    void updateItem(long userId, long cartItemId, int quantity) throws ValidationException, NotFoundException;
    void removeItem(long userId, long cartItemId) throws NotFoundException;
    List<CartItem> viewCart(long userId);
}
