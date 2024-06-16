package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConfirmationController {

    @GetMapping("/confirmationaddproduct")
    public String showConfirmationAddProduct() {
        return "confirmationaddproduct";
    }

    @GetMapping("/confirmationaddpart")
    public String showConfirmationAddPart() {
        return "confirmationaddpart";
    }

    @GetMapping("/confirmationdeleteproduct")
    public String showConfirmationDeleteProduct() {
        return "confirmationdeleteproduct";
    }

    @GetMapping("/confirmationdeletepart")
    public String showConfirmationDeletePart() {
        return "confirmationdeletepart";
    }

    @GetMapping("/confirmationassocpart")
    public String showConfirmationAssocPart() {
        return "confirmationassocpart";
    }
}
