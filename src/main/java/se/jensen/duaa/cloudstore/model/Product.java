package se.jensen.duaa.cloudstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    private Long id;

    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

}
