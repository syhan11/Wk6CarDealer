package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
public class CarDealerController {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String homePage(Model model) {
        return "homepg";
    }

    @GetMapping("/addcar")
    public String addCar(Model model) {
        model.addAttribute("allcategories", categoryRepository.findAll());
        model.addAttribute("car", new Car());
        return "addcar";
    }

    @PostMapping("/processcar")
    public String processPage(@ModelAttribute("car") Car car, Model model){
        carRepository.save(car);
        return "redirect:/listcar";
    }

    @RequestMapping("/listcar")
    public String listCar(Model model){

        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("allcars", carRepository.findAll());
        return "listcar";
    }

    @RequestMapping("/detailcar/{id}")
    public String showMsg(@PathVariable("id") long id, Model model){

        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("category", carRepository.findById(id).get().getCategory());
        return "detailcar";
    }

    @RequestMapping("/updatecar/{id}")
    public String updateCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("allcategories", categoryRepository.findAll());
        return "updatecar";
    }


    @RequestMapping("/delcar/{id}")
    public String deleteCar(@PathVariable("id") long id, Model model) {
        carRepository.deleteById(id);
        return "redirect:/listcar";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PostMapping("/processcategory")
    public String processCategory(@ModelAttribute("category") Category category, Model model){
        categoryRepository.save(category);
        return "redirect:/listcategory";
    }

    @RequestMapping("/listcategory")
    public String listCategory(Model model){

        model.addAttribute("allcategories", categoryRepository.findAll());
        model.addAttribute("allcars", carRepository.findAll());
        return "listcategory";
    }

    @RequestMapping("/updatecategory/{id}")
    public String updateCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        //model.addAttribute("allcategories", categoryRepository.findAll());
        return "addcategory";
    }

    @RequestMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") long id, Model model) {
        categoryRepository.deleteById(id);
        return "redirect:/listcategory";
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
