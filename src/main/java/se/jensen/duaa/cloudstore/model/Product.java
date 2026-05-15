package se.jensen.duaa.cloudstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    private Long id;

    private String title;
    private Double price;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private String category;
    private String image;

    @JsonProperty("rating.rate")
    private Double rate;
    @JsonProperty("rating.count")
    private Integer count;


}
