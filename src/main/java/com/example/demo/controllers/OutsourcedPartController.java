package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.PartRepository;
import com.example.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class OutsourcedPartController {

    private final PartService partService;
    private PartRepository partRepository;

    @Autowired
    public OutsourcedPartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/showPartFormForUpdate/{partId}")
    public String showPartFormForSave(@PathVariable("partId") Long partId, Model model) {
        OutsourcedPart part = (OutsourcedPart) partService.findById(partId);
        if (part != null) {
            model.addAttribute("outsourcedpart", part);
            return "OutsourcedPartForm";
        } else {
            return "redirect:/mainscreen";
        }
    }


    @PostMapping("/updateOutsourcedPart/{partId}")
    public String saveOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "OutsourcedPartForm";  // Return to the form with error messages
        }
        partService.updatePart(outsourcedPart.getPartId(), outsourcedPart);  // Update the part
        return "redirect:/mainscreen";
    }

    @PostMapping("/deleteOutsourcedPart/{partId}")
    public String deleteOutsourcedPart(@PathVariable("partId") Long partId) {
        try {
            partService.deleteById(partId);
            return "redirect:/mainscreen";
        } catch (RuntimeException e) {
            // Handle the error, perhaps redirect to an error page or display a message
            return "redirect:/error";
        }
    }
}
