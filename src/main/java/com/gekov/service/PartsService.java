package com.gekov.service;

import com.gekov.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartsService {

    Part getPartById(Long id);

    void savePart(Part part);

    void deletePart(Long id);

    void updatePart(Part part);

    List<Part> getPartByName(String name);

    Page<Part> findAllRequiredIsTrue(Pageable pageable);

    Page<Part> findAllRequiredIsFalse(Pageable pageable);

    Integer getNumberOfComputers();

    Page<Part> findAllPageable(Pageable pageable);

    List<Part> findByName(String name);
}