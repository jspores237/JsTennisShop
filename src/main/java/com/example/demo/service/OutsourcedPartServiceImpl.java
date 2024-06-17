package com.example.demo.service;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.repositories.OutsourcedPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OutsourcedPartServiceImpl implements OutsourcedPartService {
    private final OutsourcedPartRepository partRepository;

    @Autowired
    public OutsourcedPartServiceImpl(OutsourcedPartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public List<OutsourcedPart> findAll() {
        return (List<OutsourcedPart>) partRepository.findAll();
    }

    @Override
    public OutsourcedPart findById(Long theId) {
        Optional<OutsourcedPart> result = partRepository.findById(theId);

        OutsourcedPart thePart = null;

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Did not find part id - " + theId);
        }
    }

    @Override
    public void save(OutsourcedPart thePart) {
        partRepository.save(thePart);
    }

    @Override
    public void updatePart(Long theId, OutsourcedPart updatedPart) {
        OutsourcedPart existingPart = findById(theId);
        if (existingPart != null) {
            existingPart.setName(updatedPart.getName());
            existingPart.setMinInv(updatedPart.getMinInv());
            existingPart.setMaxInv(updatedPart.getMaxInv());
            existingPart.setInv(updatedPart.getInv());
            partRepository.save(existingPart);
        }
    }

    @Override
    public void deleteById(Long theId) {
        partRepository.deleteById(theId);
    }

}