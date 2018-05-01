package com.artemiishabanov.boxes;

import com.artemiishabanov.boxes.domain.Item;
import com.artemiishabanov.boxes.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Item> items = itemRepo.findAll();

        model.put("items", items);
        return "main";
    }

    @PostMapping("add")
    public String add(@RequestParam String name, @RequestParam String color, Map<String, Object> model) {
        Item item = new Item(name, color);

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