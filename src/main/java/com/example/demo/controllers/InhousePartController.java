package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@RequestMapping("/inhousepart")
public class InhousePartController {

    @Autowired
    private PartRepository partRepository;

    @PostMapping("/save")
    public String saveInhousePart(@Valid InhousePart inhousePart, BindingResult bindingResult, Model model) {
        if (!inhousePart.isInvValid()) {
            bindingResult.rejectValue("inv", "error.inhousePart", "Inventory must be between Minimum and Maximum values.");
        }
        if (inhousePart.getInv() < inhousePart.getMinInv()) {
            bindingResult.rejectValue("inv", "error.inhousePart", "Inventory must not be below the minimum value.");

        } else if (inhousePart.getInv() > inhousePart.getMinInv()) {
            bindingResult.rejectValue("inv", "error.inhousePart", "Inventory must not exceed maximum value.");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("inhousepart", inhousePart);
            return "InhousePartForm";
        }
        partRepository.save(inhousePart);
        return "redirect:/parts";
    }
}
