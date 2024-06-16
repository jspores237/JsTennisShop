package com.example.demo.service;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface OutsourcedPartService {
        public List<OutsourcedPart> findAll();
        public OutsourcedPart findById(Long theId); //changed from int to Long
        public void save (OutsourcedPart thePart);
        public void deleteById(Long theId);
        public void updatePart(Long theId, OutsourcedPart updatedPart);
}
