package com.example.demo.service;

import com.example.demo.domain.InhousePart;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface InhousePartService {
    public List<InhousePart> findAll();
    public InhousePart findById(Long theId);
    public void save (InhousePart thePart);
    public void deleteById(Long theId);
}
