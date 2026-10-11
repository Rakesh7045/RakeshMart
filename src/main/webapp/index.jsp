<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="pageTitle" value="RAKESH KANNAN MART — Everyday finds, made simple" scope="request"/>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<section class="home-hero">
  <div class="container hero-layout">
    <div class="hero-copy">
      <span class="eyebrow"><span class="eyebrow-dot"></span> YOUR EVERYDAY MARKETPLACE</span>
      <h1>Good finds.<br><span>Good days.</span></h1>
      <p>From useful everyday essentials to things you’ll love discovering, find your next favourite in one easy place.</p>
      <div class="hero-actions"><a href="${pageContext.request.contextPath}/products" class="btn btn-primary btn-lg">Explore the shop <span aria-hidden="true">→</span></a><a href="${pageContext.request.contextPath}/products" class="hero-text-link">Browse all products <span aria-hidden="true">↗</span></a></div>
      <div class="hero-note"><span class="hero-note-icon">✓</span><span>Clear prices <b>·</b> Live catalog <b>·</b> Easy ordering</span></div>
    </div>
    <div class="hero-visual">
      <div class="hero-image-frame"><img src="${pageContext.request.contextPath}/static/images/products/prod-1-sony-headphones.jpg" alt="Wireless headphones from the product catalog" fetchpriority="high"></div>
      <div class="hero-float-card"><span class="hero-float-icon">✦</span><div><strong>Find your next favourite</strong><span>Discover the collection</span></div></div>
      <span class="hero-orbit orbit-one" aria-hidden="true"></span><span class="hero-orbit orbit-two" aria-hidden="true"></span>
    </div>
  </div>
</section>

<section class="value-strip" aria-label="Shopping features"><div class="container value-grid">
  <div class="value-item"><span class="value-icon">⌕</span><div><strong>Easy discovery</strong><span>Search by product or brand</span></div></div>
  <div class="value-item"><span class="value-icon">₹</span><div><strong>Clear pricing</strong><span>Prices shown in Indian rupees</span></div></div>
  <div class="value-item"><span class="value-icon">▣</span><div><strong>Live availability</strong><span>Catalog stock information</span></div></div>
  <div class="value-item"><span class="value-icon">♡</span><div><strong>Simple shopping</strong><span>Cart and order tools in one place</span></div></div>
</div></section>

<section class="home-section categories-section"><div class="container">
  <div class="section-heading"><div><span class="eyebrow">EXPLORE YOUR INTERESTS</span><h2>Shop by category</h2><p>Start with what you’re looking for.</p></div><a class="section-link" href="${pageContext.request.contextPath}/products">View all products <span aria-hidden="true">→</span></a></div>
  <div class="category-grid">
    <a class="category-card category-electronics" href="${pageContext.request.contextPath}/products?category=Electronics"><img src="${pageContext.request.contextPath}/static/images/categories/cat-electronics.jpg" alt="Electronics" loading="lazy"><span class="category-overlay"></span><span class="category-label"><strong>Electronics</strong><span>Explore collection ↗</span></span></a>
    <a class="category-card category-fashion" href="${pageContext.request.contextPath}/products?category=Fashion"><img src="${pageContext.request.contextPath}/static/images/categories/cat-fashion.jpg" alt="Fashion" loading="lazy"><span class="category-overlay"></span><span class="category-label"><strong>Fashion</strong><span>Explore collection ↗</span></span></a>
    <a class="category-card category-home" href="${pageContext.request.contextPath}/products?category=Home+%26+Kitchen"><img src="${pageContext.request.contextPath}/static/images/categories/cat-home-kitchen.jpg" alt="Home and kitchen" loading="lazy"><span class="category-overlay"></span><span class="category-label"><strong>Home &amp; Kitchen</strong><span>Explore collection ↗</span></span></a>
    <a class="category-card category-books" href="${pageContext.request.contextPath}/products?category=Books+%26+Stationery"><img src="${pageContext.request.contextPath}/static/images/categories/cat-books-stationery.jpg" alt="Books and stationery" loading="lazy"><span class="category-overlay"></span><span class="category-label"><strong>Books &amp; Stationery</strong><span>Explore collection ↗</span></span></a>
    <a class="category-card category-fitness" href="${pageContext.request.contextPath}/products?category=Wellness+%26+Fitness"><img src="${pageContext.request.contextPath}/static/images/categories/cat-fitness-lifestyle.jpg" alt="Wellness and fitness" loading="lazy"><span class="category-overlay"></span><span class="category-label"><strong>Wellness &amp; Fitness</strong><span>Explore collection ↗</span></span></a>
  </div>
</div></section>

<section class="home-section featured-section"><div class="container">
  <div class="section-heading"><div><span class="eyebrow">A LITTLE INSPIRATION</span><h2>Explore the collection</h2><p>Browse products currently available in our catalog.</p></div><a class="section-link" href="${pageContext.request.contextPath}/products">Shop all <span aria-hidden="true">→</span></a></div>
  <div id="homeFeaturedGrid" class="products-grid home-featured-grid" aria-live="polite"><div class="featured-loading"><span class="loading-spinner"></span><span>Loading products from the catalog…</span></div></div>
  <p id="homeFeaturedMessage" class="home-featured-message" hidden></p>
</div></section>

<section class="assistant-promo"><div class="container assistant-promo-inner"><div class="assistant-promo-mark" aria-hidden="true">✦</div><div class="assistant-promo-copy"><span class="eyebrow">NEED A HAND?</span><h2>Not sure where to start?</h2><p>Ask the shopping assistant about product details, current prices, and stock information.</p></div><button type="button" class="btn btn-light" onclick="document.getElementById('chatFloatingBtn').click();">Ask the assistant <span aria-hidden="true">→</span></button></div></section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
