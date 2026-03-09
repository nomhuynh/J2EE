package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("listproduct", productService.getAllProducts());
        return "products"; 
    }

    @GetMapping("/create")
    public String create(Model model) {
        Product newProduct = new Product();
        Category category = new Category();
        newProduct.setCategory(category);
        model.addAttribute("product", newProduct);
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("product") Product product,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "create";
        }
        
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        Product product = productService.getProductById(id);
        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "edit";
        }
        return "redirect:/products";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("product") Product product,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("listCategory", categoryService.getAllCategories());
            return "edit";
        }

        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
