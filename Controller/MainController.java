package com.artemiishabanov.boxes.Controller;

import com.artemiishabanov.boxes.domain.Item;
import com.artemiishabanov.boxes.domain.User;
import com.artemiishabanov.boxes.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Item> items = itemRepo.findAll();

        model.put("items", items);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String name, @RequestParam String color, Map<String, Object> model) {
        Item item = new Item(name, color, user);

        itemRepo.save(item);

        Iterable<Item> items = itemRepo.findAll();
        model.put("items", items);
        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Item> items;

        if (filter != null && !filter.isEmpty()) {
            items = itemRepo.findByColor(filter);
        } else {
            items = itemRepo.findAll();
        }

        model.put("items", items);
        return "main";
    }

}