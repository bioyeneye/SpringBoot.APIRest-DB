package main.repositories;

import main.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface IProductRepository extends JpaRepository<Product, Integer> {
//    Product findByName(String name);
//}
public interface IProductRepository extends PagingAndSortingRepository<Product, Integer> {
    Product findByName(String name);
}
