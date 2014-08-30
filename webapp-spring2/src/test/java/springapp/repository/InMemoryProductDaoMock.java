package springapp.repository;

import java.util.List;

import springapp.domain.Product;

public class InMemoryProductDaoMock implements IProductDao {

    private List<Product> productList;

    public InMemoryProductDaoMock(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void saveProduct(Product prod) {
    }

}
