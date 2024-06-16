package com.example.demo.service;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.InhousePartRepository;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InhousePartServiceImpl implements InhousePartService {
    private final InhousePartRepository partRepository; //made final.

    @Autowired
    public InhousePartServiceImpl(InhousePartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<InhousePart> findAll() {
        return (List<InhousePart>) partRepository.findAll();
    }

    @Override
    public InhousePart findById(Long theId) {
        Optional<InhousePart> result = partRepository.findById(theId);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find part id - " + theId);
        }
    }

    @Override
    public void save(InhousePart thePart) {partRepository.save(thePart);}

    @Override
    public void deleteById(Long theId) {
        partRepository.deleteById(theId);
    }

}
