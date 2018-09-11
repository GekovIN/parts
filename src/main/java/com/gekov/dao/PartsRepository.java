package com.gekov.dao;

import com.gekov.entity.Part;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PartsRepository extends PagingAndSortingRepository<Part, Long> {

    List<Part> findAllByName(String name);
    List<Part> findAllByRequired(boolean required);
}
