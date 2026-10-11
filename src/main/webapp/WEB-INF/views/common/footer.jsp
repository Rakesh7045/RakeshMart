</main>
<footer class="site-footer">
    <div class="container footer-main">
        <div class="footer-brand-block">
            <a href="${pageContext.request.contextPath}/" aria-label="Rakesh Kannan Mart home"><img src="${pageContext.request.contextPath}/static/images/rk-mart-logo.svg" alt="Rakesh Kannan Mart" class="footer-logo-img"></a>
            <p>Everyday essentials, considered finds, and a shopping experience designed around you.</p>
        </div>
        <div class="footer-links"><strong>Explore</strong><a href="${pageContext.request.contextPath}/products">Shop all products</a><a href="${pageContext.request.contextPath}/cart">Your shopping bag</a><a href="${pageContext.request.contextPath}/orders">My orders</a></div>
        <div class="footer-links"><strong>Your account</strong><a href="${pageContext.request.contextPath}/auth/login">Sign in</a><a href="${pageContext.request.contextPath}/auth/register">Create account</a><button type="button" class="footer-chat-link" onclick="document.getElementById('chatFloatingBtn').click();">Ask the shopping assistant</button></div>
    </div>
    <div class="container footer-bottom"><span>&copy; 2026 RAKESH KANNAN MART. All rights reserved.</span><span>Built for a better everyday shopping experience.</span></div>
</footer>
<button id="chatFloatingBtn" class="chat-floating-btn" aria-label="Open Rakesh Kannan Mart shopping assistant" title="Ask RK Mart Assistant"><span aria-hidden="true">✦</span></button>
<section id="chatDrawer" class="chat-drawer" style="display:none" aria-label="Shopping assistant" aria-live="polite">
    <div class="chat-header">
        <div class="chat-brand"><span class="chat-brand-mark">RK</span><div><strong>RK Mart Assistant</strong><span class="chat-status">Product and order help</span></div></div>
        <div class="chat-header-actions"><button id="chatClearBtn" class="chat-clear-btn" type="button" title="Clear conversation">Clear</button><button id="chatCloseBtn" class="chat-close-btn" type="button" aria-label="Close chat">&times;</button></div>
    </div>
    <div class="chat-quick-suggestions" id="chatQuickSuggestions">
        <button type="button" class="chat-chip" data-msg="Show me products under ₹1,000">Under ₹1,000</button>
        <button type="button" class="chat-chip" data-msg="What is the price of Sony headphones?">Sony headphones</button>
        <button type="button" class="chat-chip" data-msg="Check what is in stock">Check stock</button>
        <button type="button" class="chat-chip" data-msg="Show me electronics">Electronics</button>
    </div>
    <div id="chatMessages" class="chat-messages"><div class="chat-msg bot">Hello! I’m the Rakesh Kannan Mart shopping assistant. Ask about products, prices, stock, or orders.</div></div>
    <form id="chatForm" class="chat-input-bar"><input type="text" id="chatInput" placeholder="Ask about products or orders…" maxlength="500" autocomplete="off" aria-label="Message the shopping assistant"><button type="submit" id="chatSendBtn" class="chat-send-btn" aria-label="Send message">➤</button></form>
</section>
<script src="${pageContext.request.contextPath}/static/js/toast.js"></script>
<script src="${pageContext.request.contextPath}/static/js/api-client.js"></script>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>
<script src="${pageContext.request.contextPath}/static/js/chat.js"></script>
<script src="${pageContext.request.contextPath}/static/js/home-products.js"></script>
</body>
</html>
