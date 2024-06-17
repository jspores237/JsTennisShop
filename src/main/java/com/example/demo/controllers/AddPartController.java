package com.example.demo.controllers;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.service.InhousePartService;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/parts")
public class AddPartController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PartService partService;

    @Autowired
    private InhousePartService inhousePartService;

    @Autowired
    private OutsourcedPartService outsourcedPartService;

    @GetMapping("/showPartFormForUpdate")
    public String showPartFormForUpdate(@RequestParam("partId") Long theId, Model theModel) {
        Part part = partService.findById(theId);
        if (part == null) {
            return "error"; // handle part not found
        }
        if (part instanceof InhousePart) {
            theModel.addAttribute("inhousepart", part);
            return "InhousePartForm";
        } else {
            theModel.addAttribute("outsourcedpart", part);
            return "OutsourcedPartForm";
        }
    }

    @PostMapping("/updateInhousePart")
    public String updateInhousePart(@Valid @ModelAttribute("inhousepart") InhousePart inhousePart, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            theModel.addAttribute("inhousepart", inhousePart);
            return "InhousePartForm";
        }
        if (inhousePart.getInv() < inhousePart.getMinInv() || inhousePart.getInv() > inhousePart.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.inhousepart", "Inventory must be between min and max values.");
            theModel.addAttribute("inhousepart", inhousePart);
            return "InhousePartForm";
        }
        inhousePartService.save(inhousePart);
        return "redirect:/confirmationaddpart";
    }

    @PostMapping("/updateOutsourcedPart")
    public String updateOutsourcedPart(@Valid @ModelAttribute("outsourcedpart") OutsourcedPart outsourcedPart, BindingResult bindingResult, Model theModel) {
        if (bindingResult.hasErrors()) {
            theModel.addAttribute("outsourcedpart", outsourcedPart);
            return "OutsourcedPartForm";
        }
        if (outsourcedPart.getInv() < outsourcedPart.getMinInv() || outsourcedPart.getInv() > outsourcedPart.getMaxInv()) {
            bindingResult.rejectValue("inv", "error.outsourcedpart", "Inventory must be between min and max values.");
            theModel.addAttribute("outsourcedpart", outsourcedPart);
            return "OutsourcedPartForm";
        }
        outsourcedPartService.save(outsourcedPart);
        return "redirect:/confirmationaddpart";
    }



    @GetMapping("/deletepart")
    public String deletePart(@RequestParam("partId") Long theId, Model model) {
        Part part = partService.findById(theId);
        if (part == null) {
            return "error"; // handle part not found
        }

        if (part.getProducts().isEmpty()) {
            partService.deleteById(theId);
            return "redirect:/confirmationdeletepart";
        } else {
            model.addAttribute("errorMessage", "Part cannot be deleted as it is used in a product.");
            return "negativeerror";
        }
    }
}
