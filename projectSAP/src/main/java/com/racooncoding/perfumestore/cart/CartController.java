//package com.example.demo.cart;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/cart")
//public class CartController {
//    @Autowired
//    private CartService cartService;
//
//    @PostMapping("/add")
//    public ResponseEntity<String> addToCart(@RequestParam Long productId, @RequestParam int quantity) {
//        // Call cartService.addToCart(productId, quantity)
//        return ResponseEntity.ok("Product added to cart");
//    }
//
////    @GetMapping("/{cartId}")
////    public ResponseEntity<Cart> getCart(@PathVariable Long cartId) {
////        // Call cartService.getCart(cartId)
////        return ResponseEntity.ok(cart);
////    }
//
//    @DeleteMapping("/remove/{cartItemId}")
//    public ResponseEntity<String> removeFromCart(@PathVariable Long cartItemId) {
//        // Call cartService.removeFromCart(cartItemId)
//        return ResponseEntity.ok("Item removed from cart");
//    }
//
//    // Other controller methods for cart operations
//}
//
