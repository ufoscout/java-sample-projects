package springapp.repository;

import java.util.List;

import springapp.domain.Product;

public interface IProductDao {

    public List<Product> getProductList();

    public void saveProduct(Product prod);

}