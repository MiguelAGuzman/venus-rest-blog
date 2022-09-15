package miguelguzman.venusrestblog.controller;

import lombok.AllArgsConstructor;
import miguelguzman.venusrestblog.data.Category;
import miguelguzman.venusrestblog.data.Post;
import miguelguzman.venusrestblog.repository.CategoriesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/categories", produces = "application/json")
public class CategoriesController {
    private CategoriesRepository categoriesRepository;

    @GetMapping("")
    private List<Category> fetchPostsByCategory(@RequestParam String categoryName) {


        return categoriesRepository.findAll();
    }

}