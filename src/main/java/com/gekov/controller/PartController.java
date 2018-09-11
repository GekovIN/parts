package com.gekov.controller;

import com.gekov.entity.Part;
import com.gekov.entity.PartDto;
import com.gekov.service.PartMapper;
import com.gekov.service.PartsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/parts")
public class PartController {

    @Autowired
    private PartsServiceImpl service;

    @RequestMapping()
    public String getPartsPage(ModelMap model) {
        Pageable pageRequest = PageRequest.of(0, 10);
        Page<Part> parts = service.findAllPageable(pageRequest);
        model.addAttribute("parts", PartMapper.map(parts));
        model.addAttribute("commandpart", new PartDto());
        model.addAttribute("parttype", "new");
        return "parts";
    }

    @RequestMapping(value="/get", produces="application/json")
    public @ResponseBody PartDto get(@RequestBody PartDto part) {
        return PartMapper.map(service.findByName(part.getName()));
    }

    @RequestMapping(value="/create", produces="application/json", method=RequestMethod.POST)
    public String create(PartDto dto) {
        if (dto.getId() != null)  {
            Part existingPart = new Part(
                    dto.getName(),
                    dto.isRequired(),
                    dto.getQuantity());
            existingPart.setId(dto.getId());
            service.updatePart(existingPart);
        } else {
            Part newPart = new Part(
                    dto.getName(),
                    dto.isRequired(),
                    dto.getQuantity());
            System.out.println(dto.isRequired());
            service.savePart(newPart);
        }

        return "redirect:/parts";
    }

    @RequestMapping(value="/edit")
    public String edit(Long id, ModelMap model) {
        Pageable pageRequest =  PageRequest.of(0, 10);
        Page<Part> parts = service.findAllPageable(pageRequest);
        model.addAttribute("parts", PartMapper.map(parts));
        model.addAttribute("commandpart", PartMapper.map(service.getPartById(id)));
        model.addAttribute("parttype", "update");
        return "parts";
    }

    @RequestMapping(value="/delete")
    public String delete(Long id) {
        service.deletePart(id);
        return "redirect:/parts";
    }
}