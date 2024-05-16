package com.example.demo.controllers;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/outsourcedpart")
public class OutsourcedPartController {

    @Autowired
    private PartRepository partRepository;

    @PostMapping("/save")
    public String saveOutsourcedPart(@Valid OutsourcedPart outsourcedPart, BindingResult bindingResult, Model model) {
        if (!outsourcedPart.isInvValid()) {
            bindingResult.rejectValue("inv", "error.outsourcedPart", "Inventory must be between Minimum and Maximum values.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("outsourcedpart", outsourcedPart);
            return "OutsourcedPartForm";
        }
        partRepository.save(outsourcedPart);
        return "redirect:/parts";
    }
}
