package com.gekov.service;

import com.gekov.dao.PartsRepository;
import com.gekov.entity.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartsServiceImpl implements PartsService {


    @Autowired
    private PartsRepository repository;

    public boolean addPart(Part part) {
        if (repository.findAllByName(part.getName()).size() == 0)
            return false;

        repository.save(part);
        return true;
    }

    public void updatePart(Part part) {
        Part updatedPart = repository.findById(part.getId()).get();
        updatedPart.setName(part.getName());
        updatedPart.setRequired(part.isRequired());
        updatedPart.setQuantity(part.getQuantity());
        repository.save(updatedPart);
    }

    public Part getPartById(Long id) {
        Optional<Part> optionalPart = repository.findById(id);
        return optionalPart.orElse(null);
    }

    public void savePart(Part part) {
        repository.save(part);
    }

    public void deletePart(Long id) {
        repository.deleteById(id);
    }

//    public List<Part> getAllParts() {
//        return repository.findAll();
//    }

    public boolean isPartAvailable(Part part) {
        return (getPartById(part.getId()) != null) || (getPartByName(part.getName()) != null && (getPartByName(part.getName()).size() > 0));
    }

    public List<Part> getPartByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public void deleteAllParts() {
        repository.deleteAll();
    }

    @Override
    public List<Part> getAllRequired() {
        return repository.findAllByRequired(true);
    }

    @Override
    public Integer getNumberOfComputers() {
        List<Part> allRequired = getAllRequired();

        if (allRequired.isEmpty())
            return 0;

        int minQuantity = allRequired.get(0).getQuantity();
        for (Part part : getAllRequired()) {
            if (part.getQuantity() < minQuantity)
                minQuantity = part.getQuantity();
        }
        return minQuantity;
    }

    @Override
    public Page<Part> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Part findByName(String name) {
        return repository.findAllByName(name).get(0);
    }

}
