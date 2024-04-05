package service;

import data.Product;
import repository.productRepo.MemoryProductRepository;

public class ProductService {
    MemoryProductRepository repository = new MemoryProductRepository();
    public boolean saveProduct(long id, String name, int quantity, int stock, String category){
        return repository.saveProduct(id, name, quantity, stock, category);
    }
    public boolean deleteProduct(long id){
        return repository.deleteProduct(id);
    }
    public Product findById(long id){
        return repository.findById(id);
    }
    public Product findByName(String name){
        return repository.findByName(name);
    }
}
