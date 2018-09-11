package com.gekov.service;

import com.gekov.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartsService {

    Part getPartById(Long id);

    void savePart(Part part);

    void deletePart(Long id);

    //List<Part> getAllParts();

    boolean addPart(Part part);

    void updatePart(Part part);

    boolean isPartAvailable(Part part);

    List<Part> getPartByName(String name);

    void deleteAllParts();

    List<Part> getAllRequired();

    Integer getNumberOfComputers();

    Page<Part> findAllPageable(Pageable pageable);

    Part findByName(String name);
}