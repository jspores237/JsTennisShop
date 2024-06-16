package com.example.demo.validators;

import com.example.demo.domain.Part;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InventoryValidator implements ConstraintValidator<ValidInventory, Part> {

    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Part part, ConstraintValidatorContext context) {
        if (part == null) {
            return true; // Valid if part is null, as we are not handling null checks here
        }

        boolean isValid = part.getInv() >= part.getMinInv() && part.getInv() <= part.getMaxInv();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                            "Inventory must be between minimum and maximum values.")
                    .addPropertyNode("inv")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
