package springapp.service;

import java.io.Serializable;
import java.util.List;

import springapp.domain.Product;

public interface IProductManager extends Serializable {

    public void increasePrice(int percentage);
    
    public List<Product> getProducts();

}
