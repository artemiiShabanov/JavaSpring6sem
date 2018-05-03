package com.artemiishabanov.boxes.domain;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String color;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public Item() {
    }

    public Item(String name, String color, User user) {
        this.owner = user;
        this.name = name;
        this.color = color;
    }

    public String getOwnerName() {
        return owner.getUsername();
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
