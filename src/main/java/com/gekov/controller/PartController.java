package com.gekov.controller;

import com.gekov.entity.Part;
import com.gekov.service.PartsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/parts")
public class PartController {

    private static int currentPage = 1;
    private static int pageSize = 10;
    private static int totalPages = 0;
    private static int numberOfPagesInPagingToView = 5;
    private static int pagingCurrentStartPage = 1;
    private static int pagingCurrentEndPage = numberOfPagesInPagingToView;
    private static ViewFilterEnum viewFilter = ViewFilterEnum.ALL_PARTS;
    private static String partNameForFilter = "";

    @Autowired
    private PartsServiceImpl service;

    @RequestMapping
    public String getPartsPage(ModelMap model, @RequestParam(name = "page", required = false) Optional<Integer> page) {

        page.ifPresent(integer -> currentPage = integer);

        if (currentPage == 0)
            currentPage = 1;

//      Если текущая страница вышла за пределы пейджинга, пересчитать первую и последнюю страницы пейджинга:
        if (currentPage > 1 && (currentPage > pagingCurrentEndPage || currentPage < pagingCurrentStartPage))
            countPagingStartAndStopPagesNumber();

//      Получаем page лист в зависимости от фильтра:
        Page<Part> parts = getAllPageable();

//      Проверяем логику удаления на случай, если список на странице стал пуст после удаления элемента:
        if (parts.getContent().isEmpty()) {
            if (currentPage > 1) {
                currentPage--;
                return "redirect:/parts";
            } else {
                model.addAttribute("emptyList", "List is empty");
            }
        }

        model.addAttribute("parts", parts);
        totalPages = parts.getTotalPages();

// Добавляем аттрибуты для пейджинга:
        setPaginationAttributes(model);

        model.addAttribute("numberOfComps", service.getNumberOfComputers());
        model.addAttribute("filterName", new Part());
        model.addAttribute("commandpart", new Part());
        model.addAttribute("parttype", "new");
        return "parts.html";
    }

    @RequestMapping(value="/create", produces="application/json", method=RequestMethod.POST)
    public String create(Part inputtedPart) {
//      Если поле название пустое - ничего не делать:
        if (inputtedPart.getName().equals(""))
            return "redirect:/parts";

//      Определеяем новая ли деталь, либо изменяется существующая:
        if (inputtedPart.getId() != null)  {
            Part existingPart = new Part(
                    inputtedPart.getName(),
                    inputtedPart.isRequired(),
                    inputtedPart.getQuantity());
            existingPart.setId(inputtedPart.getId());
            service.updatePart(existingPart);
        } else {
            Part newPart = new Part(
                    inputtedPart.getName(),
                    inputtedPart.isRequired(),
                    inputtedPart.getQuantity());
            System.out.println(inputtedPart.isRequired());
            service.savePart(newPart);
        }

        return "redirect:/parts";
    }

//    Обновляем страницу при выборе детали для редактирования:
    @RequestMapping(value="/edit")
    public String edit(Long id, ModelMap model) {
        Page<Part> parts = getAllPageable();
        model.addAttribute("parts", parts);

        setPaginationAttributes(model);

        model.addAttribute("numberOfComps", service.getNumberOfComputers());
        model.addAttribute("filterName", new Part());
        model.addAttribute("commandpart", service.getPartById(id));
        model.addAttribute("parttype", "update");
        return "parts";
    }
    @RequestMapping(value="/delete")
    public String delete(Long id) {
        service.deletePart(id);
        return "redirect:/parts";
    }

    @RequestMapping(value = "/showAllParts")
    public String showAllParts() {
        viewFilter = ViewFilterEnum.ALL_PARTS;
        return "redirect:/parts";
    }

    @RequestMapping(value = "/showRequiredTrue")
    public String showOnlyRequiredTrue() {
        viewFilter = ViewFilterEnum.ONLY_REQUIRED_TRUE;
        return "redirect:/parts";
    }

    @RequestMapping(value = "/showRequiredFalse")
    public String showOnlyRequiredFalse() {
        viewFilter = ViewFilterEnum.ONLY_REQUIRED_FALSE;
        return "redirect:/parts";
    }

    @RequestMapping(value = "/findByName", produces = "application/json", method = RequestMethod.POST)
    public String showByName(Part part) {
        viewFilter = ViewFilterEnum.FIND_BY_NAME;
        partNameForFilter = part.getName();
        return "redirect:/parts";
    }

//    Получаем данные из базы с учетом пейджинга и фильтров:

    private Page<Part> getAllPageable() {
        PageRequest request = PageRequest.of(currentPage-1, pageSize);
        switch (viewFilter) {
            case ALL_PARTS:
                return service.findAllPageable(request);
            case ONLY_REQUIRED_TRUE:
                return service.findAllRequiredIsTrue(request);
            case ONLY_REQUIRED_FALSE:
                return service.findAllRequiredIsFalse(request);
            case FIND_BY_NAME:
                return new PageImpl<>(service.findByName(partNameForFilter));
            default:
                return service.findAllPageable(request);
        }
    }

    private void setPaginationAttributes(ModelMap model) {
        if (totalPages > 0) {
            List<Integer> pageNumbers = getIntListForPaging();
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("pageNumbers", pageNumbers);
            model.addAttribute("currentPage", currentPage);
            model.addAttribute("numberOfPagesInPagingToView", numberOfPagesInPagingToView);
        }
    }

    private List<Integer> getIntListForPaging() {
        return IntStream.rangeClosed(pagingCurrentStartPage, totalPages > pagingCurrentEndPage ? pagingCurrentEndPage : totalPages)
                .boxed()
                .collect(Collectors.toList());
    }

//    Считаем номер первой и последней страницы для пейджинга на данный момент:
    private void countPagingStartAndStopPagesNumber() {
        int i = currentPage;
        if (i > totalPages)
            i = totalPages;

        if ( (i % numberOfPagesInPagingToView == 0) && (i != 0) ) {
            pagingCurrentStartPage = i - numberOfPagesInPagingToView + 1;
        } else {
            while (i % numberOfPagesInPagingToView != 0) {
                i--;
                if (i % numberOfPagesInPagingToView == 0) {
                    pagingCurrentStartPage = i + 1;
                }
            }
        }
        pagingCurrentEndPage = pagingCurrentStartPage + numberOfPagesInPagingToView-1;
    }
}