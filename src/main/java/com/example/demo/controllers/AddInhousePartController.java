package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.service.InhousePartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AddInhousePartController {

    @Autowired
    private InhousePartService inhousePartService;

    @GetMapping("/showFormAddInPart")
    public String showFormAddInhousePart(Model theModel) {
        InhousePart inhousepart = new InhousePart();
        theModel.addAttribute("inhousepart", inhousepart);
        return "InhousePartForm";
    }

    @PostMapping("/showFormAddInPart")
    public String submitForm(@Valid @ModelAttribute("inhousepart") InhousePart part, BindingResult theBindingResult, Model theModel) {
        if (theBindingResult.hasErrors()) {
            return "InhousePartForm";
        }
        if (part.isInvInvalid()) { //altered statement for simplicity
            theBindingResult.rejectValue("inv", "error.part", "Inventory must be between minimum and maximum values.");
            return "InhousePartForm";
        }

        try {
            InhousePart existingPart = inhousePartService.findById(part.getPartId());
            if (existingPart != null) {
                part.setProducts(existingPart.getProducts());
            }
            inhousePartService.save(part);
        } catch (Exception e) {
            // Log the exception (you can add a logger to log the details)
            return "error"; // Ensure this view exists or handle it appropriately
        }

        return "redirect:/confirmationaddpart";
    }
}
