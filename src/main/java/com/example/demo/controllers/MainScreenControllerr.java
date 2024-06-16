package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
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
import java.util.List;

@Controller
public class MainScreenControllerr {

    private final PartService partService;
    private final ProductService productService;

    @Autowired
    public MainScreenControllerr(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @GetMapping("/mainscreen")
    public String listPartsAndProducts(Model model) {
        List<Part> parts = partService.findAll();
        List<Product> products = productService.findAll();

        model.addAttribute("parts", parts);
        model.addAttribute("products", products);
        return "mainscreen";
    }

    @PostMapping("/updatePart/{partId}")
    public String updatePart(@PathVariable("partId") Long partId, @Valid @ModelAttribute("part") Part updatedPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";  // Assuming you are updating an OutsourcedPart
        }
        partService.updatePart(partId, updatedPart);
        return "redirect:/mainscreen";
    }

    @GetMapping("/showProductFormForUpdate/{productID}")
    public String showProductFormForUpdate(@PathVariable("productID") Long id, Model model) {
        Product product = productService.findById(id); //id instead of productId?
        if(product != null) {
        model.addAttribute("product", product);
        return "productForm";
    } else {
            return "redirect:/mainscreen";
        }
    }

    @PostMapping("/updateProduct/{productID}")
    public String updateProduct(@PathVariable("productID") Long id, @Valid @ModelAttribute("product") Product updatedProduct, BindingResult bindingResult) {        productService.updateProduct(id, updatedProduct);
        if (bindingResult.hasErrors()) {
            return "productForm";
        }
        productService.updateProduct(id, updatedProduct);
        return "redirect:/mainscreen";
    }

    @GetMapping("/showPartFormForAdd")
    public String showPartFormForAdd(Model model) {
        model.addAttribute("inhousepart", new InhousePart());
        return "InhousePartForm";
    }

    @PostMapping("/addPart")
    public String addPart(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";  // Return to the form with error messages
        }
        partService.save(inhousePart);
        return "redirect:/mainscreen";
    }

    @GetMapping("/showOutsourcedPartFormForAdd")
    public String showOutsourcedPartFormForAdd(Model model) {
        model.addAttribute("outsourcedpart", new OutsourcedPart());
        return "OutsourcedPartForm";
    }

    @PostMapping("/addOutsourcedPart")
    public String addOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";  // Return to the form with error messages
        }
        partService.save(outsourcedPart);
        return "redirect:/mainscreen";
    }
}
