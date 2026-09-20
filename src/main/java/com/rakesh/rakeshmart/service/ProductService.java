package com.rakesh.rakeshmart.service;

import com.rakesh.rakeshmart.dto.ProductRequestDTO;
import com.rakesh.rakeshmart.exception.AuthException;
import com.rakesh.rakeshmart.exception.NotFoundException;
import com.rakesh.rakeshmart.exception.ValidationException;
import com.rakesh.rakeshmart.model.Product;

import java.util.List;

public interface ProductService {
    Product create(long sellerId, ProductRequestDTO request) throws ValidationException;
    Product update(long productId, long sellerId, ProductRequestDTO request) throws ValidationException, NotFoundException, AuthException;
    void delete(long productId, long sellerId) throws NotFoundException, AuthException;
    List<Product> search(String keyword, String category);
    List<Product> findBySeller(long sellerId);
    Product findById(long id) throws NotFoundException;
}
