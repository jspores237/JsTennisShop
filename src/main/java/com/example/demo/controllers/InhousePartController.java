package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.repositories.PartRepository;
import com.example.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class InhousePartController {

    @Autowired
    private PartRepository partRepository;
    private PartService partService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("inhousepart", new InhousePart());
        return "InhousePartForm";
    }
    @PostMapping("/add")
    public String addInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "InhousePartForm";
        }
        partService.save(inhousePart);
        return "redirect:/parts/list";
    }

    @GetMapping("/showInhousePartFormForUpdate/{partId}")
    public String showUpdateForm(@PathVariable("partId") Long partId, Model model) {
        InhousePart inhousePart = (InhousePart) partService.findById(partId);
        if (inhousePart == null) {
            throw new IllegalArgumentException("Invalid part Id:" + partId);
        }
        model.addAttribute("inhousepart", inhousePart);
        return "InhousePartForm";
    }

    @PostMapping("/updateInhousePart")
    public String saveInhousePart(@ModelAttribute("inhousepart") InhousePart inhousePart) {
        partService.save(inhousePart);
        return "redirect:/mainscreen";
        }
    }

