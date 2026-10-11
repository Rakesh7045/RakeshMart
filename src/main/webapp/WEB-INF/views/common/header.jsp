<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="theme-color" content="#B83232">
    <title><c:out value="${pageTitle != null ? pageTitle : 'RAKESH KANNAN MART — Shop with confidence'}" /></title>
    <meta name="description" content="Discover everyday essentials and considered finds at Rakesh Kannan Mart.">
    <meta name="csrf-token" content="${sessionScope.csrfToken != null ? sessionScope.csrfToken : csrfToken}">
    <meta name="context-path" content="${pageContext.request.contextPath}">
    <link rel="icon" type="image/svg+xml" href="${pageContext.request.contextPath}/static/images/rk-mart-logo.svg">
    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/static/images/rk-mart-logo.svg">
    <link rel="manifest" href="${pageContext.request.contextPath}/manifest.json">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=DM+Sans:wght@400;500;600;700&family=Manrope:wght@400;500;600;700;800&display=swap" rel="stylesheet">
</head>
<body>
<a class="skip-link" href="#mainContent">Skip to content</a>
<div class="announcement-bar"><span>Thoughtful finds. Everyday value.</span><span class="announcement-note">A smoother way to shop</span></div>
<header class="site-header">
    <div class="container header-inner">
        <a href="${pageContext.request.contextPath}/" class="brand-logo" aria-label="Rakesh Kannan Mart home">
            <img src="${pageContext.request.contextPath}/static/images/rk-mart-logo.svg" alt="Rakesh Kannan Mart" class="brand-logo-img">
        </a>
        <form class="header-search" action="${pageContext.request.contextPath}/products" method="GET" role="search">
            <span class="search-symbol" aria-hidden="true">⌕</span>
            <input type="search" name="search" placeholder="Search products and brands" aria-label="Search products and brands" autocomplete="off">
            <button type="submit" aria-label="Submit search">Search</button>
        </form>
        <button class="mobile-menu-toggle" id="mobileMenuToggle" aria-label="Toggle navigation" aria-controls="navContainer" aria-expanded="false">
            <span class="bar"></span><span class="bar"></span><span class="bar"></span>
        </button>
        <nav class="nav-container" id="navContainer" aria-label="Main navigation">
            <ul class="nav-menu">
                <li><a href="${pageContext.request.contextPath}/" class="nav-link">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/products" class="nav-link">Shop</a></li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <c:if test="${sessionScope.user.role == 'BUYER'}">
                            <li><a href="${pageContext.request.contextPath}/orders" class="nav-link">My Orders</a></li>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'SELLER'}">
                            <li><a href="${pageContext.request.contextPath}/seller/dashboard" class="nav-link">Seller Studio</a></li>
                        </c:if>
                        <c:if test="${sessionScope.user.role == 'ADMIN'}">
                            <li><a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-link">Admin</a></li>
                            <li><a href="${pageContext.request.contextPath}/admin/analytics" class="nav-link">Analytics</a></li>
                        </c:if>
                        <li><a href="${pageContext.request.contextPath}/auth/logout" class="nav-link">Sign out</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/auth/login" class="nav-link">Sign in</a></li>
                        <li><a href="${pageContext.request.contextPath}/auth/register" class="btn btn-primary nav-register">Create account</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="${pageContext.request.contextPath}/cart" class="cart-link" aria-label="Shopping cart">Bag <span id="navCartBadge" class="badge badge-accent"></span></a></li>
            </ul>
        </nav>
    </div>
</header>
<div id="toastContainer" class="toast-container" aria-live="polite" aria-atomic="true"></div>
<main class="main-content" id="mainContent">
