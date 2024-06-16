package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.PartService;
import com.example.demo.service.PartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AddOutsourcedPartController {
    private final OutsourcedPartService outsourcedPartService;

    public AddOutsourcedPartController(OutsourcedPartService outsourcedPartService) {
        this.outsourcedPartService = outsourcedPartService;
    }

    @GetMapping("/showFormAddOutPart")
    public String showFormAddOutsourcedPart(Model theModel) {
        OutsourcedPart part = new OutsourcedPart(); //OutsourcedPart not Part
        theModel.addAttribute("outsourcedpart", part);
        return "OutsourcedPartForm";
    }

    @PostMapping("/updatePart")
    public String submitForm(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart part, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";
        }
        try {
            OutsourcedPart existingPart = outsourcedPartService.findById(part.getPartId());
            if (existingPart != null) {
                part.setProducts(existingPart.getProducts());
            }
            outsourcedPartService.save(part);
    } catch (Exception e) {
            return "error";
        }
        return "redirect:/confirmationaddpart";
    }
}
