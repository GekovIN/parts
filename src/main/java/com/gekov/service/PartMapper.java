package com.gekov.service;

import com.gekov.entity.Part;
import com.gekov.entity.PartDto;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class PartMapper {

    public static PartDto map(Part user) {
        PartDto dto = new PartDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setRequired(user.isRequired());
        dto.setQuantity(user.getQuantity());
        return dto;
    }

    public static List<PartDto> map(Page<Part> parts) {
        List<PartDto> dtos = new ArrayList<PartDto>();
        for (Part part: parts) {
            dtos.add(map(part));
        }
        return dtos;
    }
}
