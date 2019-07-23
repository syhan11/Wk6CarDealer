package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class CarDealerController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        model.addAttribute("allcategories", categoryRepository.findAll());
        model.addAttribute("allcars", carRepository.findAll());
        return "list";
    }

    @PostConstruct()
    public void initTable() {
        Category item = new Category("compact");
        categoryRepository.save(item);

        item = new Category("convertible");
        categoryRepository.save(item);

        item = new Category("crossover");
        categoryRepository.save(item);

        item = new Category("electric");
        categoryRepository.save(item);

        item = new Category("sedan");
        categoryRepository.save(item);

        item = new Category("sports");
        categoryRepository.save(item);

        item = new Category("suv");
        categoryRepository.save(item);

    }
}
