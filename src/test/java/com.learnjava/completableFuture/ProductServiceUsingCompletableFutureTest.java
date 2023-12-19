package com.learnjava.completableFuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author kansanja on 18/12/23.
 */
class ProductServiceUsingCompletableFutureTest {

    private ProductInfoService productInfoService = new ProductInfoService();
    private ReviewService reviewService = new ReviewService();
    private InventoryService inventoryService = new InventoryService();

    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture = new ProductServiceUsingCompletableFuture(productInfoService, reviewService, inventoryService);

    @Test
    void retrieveProductDetails() {

        // given
        String productId = "1234";

        // when
        Product product = productServiceUsingCompletableFuture.retrieveProductDetails(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
    }

    @Test
    void retrieveProductDetails_approach2() {
        startTimer();
        // given
        String productId = "1234";

        // when
        CompletableFuture<Product> productCompletableFuture = productServiceUsingCompletableFuture.retrieveProductDetails_approach2(productId);

        // then
        productCompletableFuture.thenAccept(product -> {
            assertNotNull(product);
            assertTrue(product.getProductInfo().getProductOptions().size() > 0);
            assertNotNull(product.getReview());
        }).join();

        timeTaken();
    }

    @Test
    void retrieveProductDetailsWithInventoryTest() {

        // given
        String productId = "1234";

        // when
        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
        product.getProductInfo()
                .getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
    }

    @Test
    void retrieveProductDetailsWithInventoryApproach2Test() {

        // given
        String productId = "1234";

        // when
        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory_approch2(productId);

        // then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
        product.getProductInfo()
                .getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
    }
}