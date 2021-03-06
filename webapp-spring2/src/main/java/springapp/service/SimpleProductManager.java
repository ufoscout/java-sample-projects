package springapp.service;

import java.util.List;

import springapp.domain.Product;
import springapp.repository.IProductDao;

public class SimpleProductManager implements IProductManager {

	private static final long serialVersionUID = 1L;
    private IProductDao productDao;

    public List<Product> getProducts() {
        return productDao.getProductList();
    }

    public void increasePrice(int percentage) {
        List<Product> products = productDao.getProductList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDao.saveProduct(product);
            }
        }
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }


}
