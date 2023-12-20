package com.learnjava.completableFuture;

import com.learnjava.domain.Product;
import com.learnjava.service.InventoryService;
import com.learnjava.service.ProductInfoService;
import com.learnjava.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author kansanja on 20/12/23.
 */
@ExtendWith(MockitoExtension.class)
class ProductServiceUsingCompletableFutureExceptionTest {

    @InjectMocks
    ProductServiceUsingCompletableFuture productServiceUsingCompletableFuture;

    @Mock
    private ProductInfoService productInfoService;
    @Mock
    private ReviewService reviewService;
    @Mock
    private InventoryService inventoryService;

    @Test
    void retrieveProductDetailsWithInventory_approch2() {

        //given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenCallRealMethod();
        when(reviewService.retrieveReviews(any())).thenThrow(new RuntimeException("Exception occurred"));
        when(inventoryService.addInventory(any())).thenCallRealMethod();
        //when
        Product product = productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory_approch2(productId);

        //then
        assertNotNull(product);
        assertTrue(product.getProductInfo().getProductOptions().size() > 0);
        assertNotNull(product.getReview());
        product.getProductInfo()
                .getProductOptions()
                .forEach(productOption -> {
                    assertNotNull(productOption.getInventory());
                });
        assertEquals(0, product.getReview().getNoOfReviews());
    }


    @Test
    void retrieveProductDetailsWithInventory_productInfoServiceError() {

        //given
        String productId = "ABC123";
        when(productInfoService.retrieveProductInfo(any())).thenThrow(new RuntimeException("Exception occurred"));
        when(reviewService.retrieveReviews(any())).thenCallRealMethod();

        //then
        assertThrows(RuntimeException.class, () -> productServiceUsingCompletableFuture.retrieveProductDetailsWithInventory_approch2(productId));
    }


}