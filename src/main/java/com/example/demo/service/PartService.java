package com.example.demo.service;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import java.util.List;

public interface PartService {
    public List<Part> findAll();
    public Part findById(Long theId);
    public void save (Part thePart);
    public void deleteById(Long theId);
    public void updatePart(Long partId, Part updatedPart); // Added this method
    public List<Part> listAll(String keyword);
}
