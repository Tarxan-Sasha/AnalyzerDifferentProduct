package sasha.analizator.products.entites;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name="product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int id;

    @Column(name="name_product")
    private String name;

    @Column(name = "price_product")
    private String price;

    @Column(name= "exist_product")
    private String exist;

    @Column(name="link_product")
    private String link;

}
