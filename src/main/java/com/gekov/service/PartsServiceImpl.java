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

    public void updatePart(Part part) {
        Optional<Part> optionalPart = repository.findById(part.getId());

        Part updatedPart;

        if (optionalPart.isPresent())
            updatedPart = optionalPart.get();
        else return;

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
        List<Part> allByName = repository.findAllByName(part.getName());
        if (allByName.isEmpty()) {
            repository.save(part);
        }
    }

    @Override
    public void deletePart(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Part> getPartByName(String name) {
        return repository.findAllByName(name);
    }

    @Override
    public Page<Part> findAllRequiredIsTrue(Pageable pageable) {
        return repository.findByRequiredIsTrue(pageable);
    }

    @Override
    public Page<Part> findAllRequiredIsFalse(Pageable pageable) {
        return repository.findByRequiredIsFalse(pageable);
    }

// Вычисляем количество компьютеров, готовых к сборке:
    @Override
    public Integer getNumberOfComputers() {
        List<Part> allRequired = repository.findAllByRequiredIsTrue();
        if (allRequired.isEmpty())
            return 0;

        int minQuantity = allRequired.get(0).getQuantity();
        for (Part part : allRequired) {
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
    public List<Part> findByName(String name) {
        return repository.findAllByName(name);
    }

}
