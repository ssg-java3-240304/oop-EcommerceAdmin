package service;

import data.Customer;
import data.Order;
import data.Product;
import repository.orderRepo.MemoryOrderRepository;
import repository.orderRepo.OrderRepository;

import java.util.List;

public class OrderService {
    OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new MemoryOrderRepository();
    }

    public boolean saveOrder(Order newOrder){
        if(orderRepository!=null){
            return orderRepository.saveOrder(newOrder);
        }
        else return false;
    }
    public boolean deleteOrder(long id){
        if(orderRepository!=null){
            Order foundOrder = orderRepository.findById(id);
            return orderRepository.deleteOrder(foundOrder);
        }
        else return false;
    }
    public Order findByOrderId(long id){
        if(orderRepository!=null){
            return orderRepository.findById(id);
        }
        else return null;
    }
    public List<Order> findByCustomer(Customer customer){
        if(orderRepository!=null){
            return orderRepository.findOrderByCustomer(customer);
        }
        else return null;
    }
    public List<Order> findByProduct(Product product){
        if(orderRepository!=null){
            return orderRepository.findOrderByProduct(product);
        }
        else return null;
    }
    public List<Order> findOrderAll(){
        return orderRepository.findOrderAll();
    }
}
