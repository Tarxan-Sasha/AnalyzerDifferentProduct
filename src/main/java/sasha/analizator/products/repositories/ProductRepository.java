package sasha.analizator.products.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sasha.analizator.products.entites.Product;

import java.beans.Transient;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
