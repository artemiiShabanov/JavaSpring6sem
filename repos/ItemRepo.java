package com.artemiishabanov.boxes.repos;

import com.artemiishabanov.boxes.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepo extends CrudRepository<Item, Long> {
    List<Item> findByColor(String color);
}
