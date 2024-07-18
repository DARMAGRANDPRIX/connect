package com.example.nagoyameshi.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Category;
import com.example.nagoyameshi.entity.Restaurant;
import com.example.nagoyameshi.entity.RestaurantCategory;
import com.example.nagoyameshi.repository.CategoryRepository;
import com.example.nagoyameshi.repository.RestaurantCategoryRepository;
import com.example.nagoyameshi.repository.RestaurantRepository;

@Controller
public class RestaurantController {
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final RestaurantCategoryRepository restaurantCategoryRepository;

    public RestaurantController(CategoryRepository categoryRepository, RestaurantRepository restaurantRepository,
            RestaurantCategoryRepository restaurantCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.restaurantCategoryRepository = restaurantCategoryRepository;
    }

    @GetMapping("/restaurants")
    public String index(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "area", required = false) String area,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Restaurant> restaurants;

        if (keyword != null && !keyword.isEmpty()) {
            restaurants = restaurantRepository.findByNameContaining(keyword);
        } else if (area != null && !area.isEmpty()) {
            restaurants = restaurantRepository.findByAddressContaining(area);
        } else if (price != null) {
            restaurants = restaurantRepository.findByBudgetMaxLessThanEqual(price);
        } else if (categoryId != null) {
            restaurants = restaurantRepository.findByRestaurantCategoriesCategoryId(categoryId);
        } else {
            restaurants = restaurantRepository.findAll();
        }

        for (Restaurant restaurant : restaurants) {
            List<RestaurantCategory> restaurantCategories = restaurantCategoryRepository.findByRestaurant(restaurant);
            restaurant.setRestaurantCategories(restaurantCategories);
        }

        model.addAttribute("name", keyword);
        model.addAttribute("area", area);
        model.addAttribute("price", price);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categories);
        model.addAttribute("restaurants", restaurants);
        return "restaurants/index";
    }
}

//リポジトリ
package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.nagoyameshi.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByNameContaining(String name);
    List<Restaurant> findByAddressContaining(String area);
    List<Restaurant> findByBudgetMaxLessThanEqual(int price);
    List<Restaurant> findByRestaurantCategoriesCategoryId(Integer categoryId);
}