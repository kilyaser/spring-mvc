package ru.gb.dto;

import ru.gb.model.Product;

public class ProductDto {
    private Long id;
    private String title;
    private Integer cost;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.cost = product.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public ProductDto() {
    }
}
