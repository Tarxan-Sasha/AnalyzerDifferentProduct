package sasha.analizator.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sasha.analizator.products.entites.Product;

import java.beans.Transient;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public boolean existsByName(String name);
    public boolean existsByPrice(String price);
    public boolean existsByLink(String link);

    public Product findByName(String name);
    public List<Product> findAllByName(String name);
}
