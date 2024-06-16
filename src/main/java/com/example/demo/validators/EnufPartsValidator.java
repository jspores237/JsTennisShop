package com.example.demo.validators;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnufPartsValidator implements ConstraintValidator<ValidEnufParts, Product> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void initialize(ValidEnufParts constraintAnnotation) {
        // No initialization required
    }

    @Override
    public boolean isValid(Product product, ConstraintValidatorContext context) {
        if (applicationContext == null) {
            return true; // Validation cannot be performed without context
        }

        ProductService productService = applicationContext.getBean(ProductService.class);

        // Retrieve the existing product if it exists
        if (product.getId() != 0) {
            Product existingProduct = productService.findById((Long) product.getId());

            for (Part part : existingProduct.getParts()) {
                // Check if the part's inventory after decrementing would fall below the minimum inventory
                if (part.getInv() - 1 < part.getMinInv()) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate(
                                    String.format("Adding/updating this product would cause part '%s' to fall below its minimum inventory.", part.getName()))
                            .addConstraintViolation();
                    return false;
                }
            }
        }
        // If the product is new or the parts are sufficient
        return true;
    }
}
