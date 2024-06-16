package com.example.demo.controllers;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class AddProductController {

    private final PartService partService;
    private final ProductService productService;
    private Product currentProduct;

    @Autowired
    public AddProductController(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/showFormAddProduct")
    public String showFormAddProduct(Model theModel) {
        Product currentProduct = new Product();
        prepareProductFormModel(theModel, currentProduct);
        return "productForm";
    }

    @PostMapping("/updateProduct")
    public String submitForm(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            prepareProductFormModel(theModel, product);
            return "productForm";
        }

        if (product.getInv() < product.getMinInv() || product.getInv() > product.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.product", "Inventory must be between min and max values.");
            prepareProductFormModel(theModel, product);
            return "productForm";
        }

        productService.save(product);
        return "redirect:/confirmationaddproduct";
    }



    @GetMapping("/showProductFormForUpdate/{productID}")
    public String showProductFormForUpdate(@PathVariable("productID") Long theId, Model theModel) {
        Product currentProduct = productService.findById(theId);
        if (currentProduct == null) {
            return "error"; // handle product not found
        }
        prepareProductFormModel(theModel, currentProduct);
        return "productForm";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(@RequestParam("productID") Long theId) {
        Product product = productService.findById(theId);
        if (product == null) {
            return "error"; // handle product not found
        }
        dissociatePartsFromProduct(product);
        productService.deleteById(theId);
        return "redirect:/confirmationdeleteproduct";
    }

    @GetMapping("/associatePart")
    public String associatePart(@RequestParam("partId") Long partId, @RequestParam("productID") Long productId, Model theModel) {
        Product currentProduct = productService.findById(productId);
        if (currentProduct == null) {
            return "error"; // handle product not found
        }

        Part part = partService.findById(partId);
        if (part == null) {
            return "error"; // handle part not found
        }

        currentProduct.getParts().add(part);
        part.getProducts().add(currentProduct);

        productService.save(currentProduct);
        partService.save(part);

        prepareProductFormModel(theModel, currentProduct);
        return "redirect:/confirmationassocpart";
    }


    @GetMapping("/removePart")
    public String removePart(@RequestParam("partId") Long partId, @RequestParam("productID") Long productId, Model theModel) {
        Product currentProduct = productService.findById(productId);
        if (currentProduct == null) {
            return "error"; // handle product not found
        }

        Part part = partService.findById(partId);
        if (part == null) {
            return "error"; // handle part not found
        }

        currentProduct.getParts().remove(part);
        part.getProducts().remove(currentProduct);

        productService.save(currentProduct);
        partService.save(part);

        prepareProductFormModel(theModel, currentProduct);
        return "redirect:/confirmationassocpart";
    }


    private void prepareProductFormModel(Model theModel, Product product) {
        theModel.addAttribute("product", product);
        theModel.addAttribute("parts", partService.findAll());
        theModel.addAttribute("assparts", product.getParts());

        List<Part> availableParts = new ArrayList<>();
        for (Part part : partService.findAll()) {
            if (!product.getParts().contains(part)) {
                availableParts.add(part);
            }
        }
        theModel.addAttribute("availparts", availableParts);
    }

    private void updatePartInventoryForExistingProduct(Product product) {
        Product existingProduct = productService.findById(product.getId());
        if (existingProduct == null) {
            throw new RuntimeException("Product not found");
        }
        if (product.getInv() > existingProduct.getInv()) {
            for (Part part : existingProduct.getParts()) {
                part.setInv(part.getInv() - (product.getInv() - existingProduct.getInv()));
                partService.save(part);
            }
        }
    }
    private void dissociatePartsFromProduct(Product product) {
        for (Part part : product.getParts()) {
            part.getProducts().remove(product);
            partService.save(part);
        }
        product.getParts().clear();
        productService.save(product);
    }
}