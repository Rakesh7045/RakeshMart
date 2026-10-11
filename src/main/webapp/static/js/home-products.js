document.addEventListener('DOMContentLoaded', async () => {
  const grid = document.getElementById('homeFeaturedGrid');
  const message = document.getElementById('homeFeaturedMessage');
  if (!grid || typeof ApiClient === 'undefined') return;
  const escapeHtml = (value) => String(value ?? '').replace(/[&<>"']/g, (char) => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[char]));
  try {
    const result = await ApiClient.get('/api/products?sort=newest&page=1&size=4');
    const products = Array.isArray(result?.data) ? result.data : (Array.isArray(result?.content) ? result.content : []);
    if (!products.length) {
      grid.innerHTML = '';
      if (message) { message.hidden = false; message.textContent = 'No products are available to feature right now. Browse the full catalog to try again.'; }
      return;
    }
    const cp = ApiClient.getContextPath();
    grid.innerHTML = products.slice(0, 4).map((product) => {
      const name = escapeHtml(product.name || 'Product');
      const image = escapeHtml(ApiClient.resolveImageUrl(product.imageUrl));
      const price = Number(product.price || 0).toLocaleString('en-IN', {maximumFractionDigits: 2});
      const category = escapeHtml(product.category || 'Catalog');
      const stock = Number(product.stockQty || 0);
      const available = stock > 0;
      const id = encodeURIComponent(product.id);
      const placeholder = escapeHtml(ApiClient.getPlaceholderImage());
      return `<article class="product-card home-product-card"><a class="home-product-image" href="${cp}/products/${id}"><img src="${image}" alt="${name}" loading="lazy" onerror="this.onerror=null;this.src='${placeholder}'"><span class="home-product-category">${category}</span></a><div class="product-card-content"><h3 class="product-title"><a href="${cp}/products/${id}">${name}</a></h3><div class="home-product-meta"><strong class="product-price">₹${price}</strong><span class="${available ? 'stock-available' : 'stock-unavailable'}">${available ? 'In stock' : 'Unavailable'}</span></div><button class="btn ${available ? 'btn-primary home-add-to-cart' : 'btn-outline'} btn-block" type="button" ${available ? `data-product-id="${escapeHtml(product.id)}"` : 'disabled'}>${available ? 'Add to cart' : 'Unavailable'} <span aria-hidden="true">→</span></button></div></article>`;
    }).join('');
  } catch (error) {
    grid.innerHTML = '';
    if (message) { message.hidden = false; message.textContent = 'The catalog could not be loaded right now. Please visit the shop to try again.'; }
  }

  grid.addEventListener('click', async (event) => {
    const button = event.target.closest('.home-add-to-cart');
    if (!button) return;
    const productId = Number(button.dataset.productId);
    if (!Number.isInteger(productId) || productId <= 0) return;
    const originalText = button.innerHTML;
    button.disabled = true;
    button.textContent = 'Adding…';
    try {
      const cart = await ApiClient.post('/api/cart/add', { productId, quantity: 1 });
      if (window.DjMartToast) DjMartToast.success('Item added to your shopping bag.');
      const badge = document.getElementById('navCartBadge');
      if (badge && cart && Number.isFinite(Number(cart.totalItems))) {
        badge.textContent = Number(cart.totalItems) > 0 ? String(cart.totalItems) : '';
        badge.style.display = Number(cart.totalItems) > 0 ? 'inline-block' : 'none';
      }
    } catch (error) {
      if (error.status === 401) {
        window.location.href = `${ApiClient.getContextPath()}/auth/login?redirect=${encodeURIComponent(window.location.pathname + window.location.search)}`;
      } else if (window.DjMartToast) {
        DjMartToast.error(error.message || 'Could not add this item to your bag.');
      }
    } finally {
      button.disabled = false;
      button.innerHTML = originalText;
    }
  });
});
