package repository.orderRepo;

import data.Customer;
import data.Order;
import data.Product;

import java.util.List;

public interface OrderRepository {
    boolean saveOrder(Order order);
    boolean deleteOrder(Order order);
    List<Order> findOrderByCustomer(Customer customer);
    List<Order> findOrderByProduct(Product product);
    List<Order> findOrderRecent();
    Order findById(long id);
    List<Order> findOrderAll();



}
