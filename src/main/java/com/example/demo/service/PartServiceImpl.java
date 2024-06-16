package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PartServiceImpl implements PartService{

    private final PartRepository partRepository; //made final

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<Part> findAll() {
        List<Part> parts = (List<Part>) partRepository.findAll();
        parts.stream().filter(Objects::nonNull).forEach(part -> {
            System.out.println("Part fetched from DB: " + part.getName() + ", ID: " + part.getPartId());
        });
        return parts.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<Part> listAll(String keyword){
        if (keyword != null) {
            return partRepository.search(keyword);
        }
        return (List<Part>) partRepository.findAll();
    }

    @Override
    public Part findById(Long partId) {
        return partRepository.findById(partId).orElse(null);
    }

    @Override
    public void save(Part part) {
            partRepository.save(part);
    }

    @Override
    public void deleteById(Long partId) {
        partRepository.deleteById(partId);
    }

    @Override
    public void updatePart(Long partId, Part updatedPart) {
        Part existingPart = findById(partId);
        if (existingPart == null) {
            throw new RuntimeException("Part not found");
        }
        // Update the properties of the existing part with the new values
        existingPart.setName(updatedPart.getName());
        existingPart.setInv(updatedPart.getInv());
        existingPart.setMaxInv(updatedPart.getMaxInv());
        existingPart.setMinInv(updatedPart.getMinInv());
        existingPart.setPrice(updatedPart.getPrice());

        // Check if the part is an instance of OutsourcedPart and update the companyName
        if (existingPart instanceof OutsourcedPart && updatedPart instanceof OutsourcedPart) {
            ((OutsourcedPart) existingPart).setCompanyName(((OutsourcedPart) updatedPart).getCompanyName());
        }

        partRepository.save(existingPart);
    }
}
