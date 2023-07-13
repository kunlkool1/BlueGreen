<section id="category" class="category py-md-5 py-3">
    <div class="container-lg">
        <div class="swiper category-list">
            <div class="swiper-wrapper">
                <c:forEach items="${cateList}" var="c">
                    <div class="swiper-slide">
                        <a href="#" class="category-link">
                            <div class="category-icon">
                                <img src="${c.getValue().imageUrl}" alt="${c.getValue().name}">
                            </div>
                            <p class="category-name">${c.getValue().name}</p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</section>

