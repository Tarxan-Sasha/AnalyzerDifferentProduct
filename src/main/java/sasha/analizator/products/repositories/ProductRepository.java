package sasha.analizator.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sasha.analizator.products.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
