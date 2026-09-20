package com.rakesh.rakeshmart.controller;

import com.rakesh.rakeshmart.dao.DAOFactory;
import com.rakesh.rakeshmart.dto.ApiResponse;
import com.rakesh.rakeshmart.exception.NotFoundException;
import com.rakesh.rakeshmart.filter.AuthFilter;
import com.rakesh.rakeshmart.model.Order;
import com.rakesh.rakeshmart.model.User;
import com.rakesh.rakeshmart.service.OrderService;
import com.rakesh.rakeshmart.service.impl.OrderServiceImpl;
import com.rakesh.rakeshmart.util.JsonUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * F6: order history. GET /api/v1/orders returns the buyer's own orders, or (with
 * ?as=seller) the seller's incoming orders for their products.
 * PUT /api/v1/orders/{id}/status - O2 status workflow, seller/admin only.
 */
@WebServlet("/api/v1/orders/*")
public class OrderServlet extends HttpServlet {

    private final OrderService orderService =
            new OrderServiceImpl(DAOFactory.orderDAO(), DAOFactory.cartDAO(), DAOFactory.productDAO());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = AuthFilter.currentUser(req);
        String as = req.getParameter("as");

        List<Order> orders;
        if ("seller".equals(as) && user.getRole() == User.Role.SELLER) {
            orders = orderService.sellerIncomingOrders(user.getId());
        } else {
            orders = orderService.buyerHistory(user.getId());
        }
        JsonUtil.writeJson(resp, HttpServletResponse.SC_OK, ApiResponse.ok(orders));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = AuthFilter.currentUser(req);
        if (user.getRole() == User.Role.BUYER) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_FORBIDDEN,
                    ApiResponse.fail("FORBIDDEN", "Only sellers/admin can update order status"));
            return;
        }
        String pathInfo = req.getPathInfo(); // expected: /{id}/status
        try {
            String[] parts = pathInfo.substring(1).split("/");
            long orderId = Long.parseLong(parts[0]);
            StatusUpdateRequest body = JsonUtil.readJson(req, StatusUpdateRequest.class);
            Order.Status status = Order.Status.valueOf(body.status.toUpperCase());
            Order updated = orderService.updateStatus(orderId, status);
            JsonUtil.writeJson(resp, HttpServletResponse.SC_OK, ApiResponse.ok(updated));
        } catch (NotFoundException e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_NOT_FOUND, ApiResponse.fail("NOT_FOUND", e.getMessage()));
        } catch (IllegalArgumentException | NullPointerException e) {
            JsonUtil.writeJson(resp, HttpServletResponse.SC_BAD_REQUEST,
                    ApiResponse.fail("VALIDATION_ERROR", "Invalid order id or status value"));
        }
    }

    private static class StatusUpdateRequest {
        String status;
    }
}
