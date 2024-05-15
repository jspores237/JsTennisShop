package com.example.demo.controllers;

import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class BuyNowController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/buyProduct")
    public String buyProduct(@RequestParam("productID") Long productID, RedirectAttributes redirectAttributes) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getInv() > 0) {
                product.setInv(product.getInv() - 1);
                productRepository.save(product);
                redirectAttributes.addFlashAttribute("message","Purchase Successful!");
                return "redirect:/purchaseSuccess";
            } else {
                redirectAttributes.addFlashAttribute("message", "Not enough inventory.");
                return "redirect:/purchaseError";
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Product not found.");
            return "redirect:/purchaseError";
        }
    }
    @GetMapping("/purchaseSuccess")
    public String displayPurchaseSuccess() {
        return "purchaseSuccess";
    }
    @GetMapping("/purchaseError")
    public String displayPurchaseError() {
        return "purchaseError";
    }
}
