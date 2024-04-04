package repository.productRepo;

import data.Product;

public interface ProductRepository {
    boolean saveProduct(long id, String name, int price, int stock, String category);
    boolean deleteProduct(long id);
    Product findById(long id);
    Product findByName(String name);
}
