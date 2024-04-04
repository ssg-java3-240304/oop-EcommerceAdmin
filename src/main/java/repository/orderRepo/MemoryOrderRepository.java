package repository.orderRepo;

import data.Customer;
import data.Order;
import data.Product;
import repository.StorageSaver;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryOrderRepository implements OrderRepository{

    private ArrayList<Order> repository;  // singleton repository
    private StorageSaver storageSaver;
    Comparator<Order> comparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            if(o1.getOrderDateTime().isBefore(o2.getOrderDateTime()))return -1;
            else return 1;
        }
    };

    public MemoryOrderRepository() {
        this.repository = new ArrayList<>();
        this.storageSaver = storageSaver;
    }

    @Override
    public boolean saveOrder(Order order) {
        if(order==null || !(order instanceof Order)){
            return false;
        }
        else{
            repository.add(order);
            return true;
        }
    }

    @Override
    public boolean deleteOrder(Order order) {
        boolean result = repository.remove(order);
        return result;

    }

    @Override
    public List<Order> findOrderByCustomer(Customer customer) {
       List<Order> foundOrder =  repository.stream()
               .filter(order -> order.getCustomer().equals(customer))
               .toList();
       return foundOrder;
    }

    @Override
    public List<Order> findOrderByProduct(Product product) {
        List<Order> foundOrder =  repository.stream()
                .filter(order -> order.getProduct().equals(product))
                .toList();
        return foundOrder;
    }

    @Override
    public List<Order> findOrderRecent(){
        ArrayList<Order> newList = new ArrayList<>();
        PriorityQueue<Order> queue = new PriorityQueue<>(comparator);
        for(int i =0; i<repository.size(); i++){
            queue.add(repository.get(i));
        }
        for(int i =0; i< queue.size(); i++){
            newList.add(queue.poll());
        }
        return newList;
    }
    @Override
    public Order findById(long id){
        Order foundOrder = null;
        for(int i = 0; i< repository.size(); i++){
            if (repository.get(i).getId()==id){
                foundOrder = repository.get(i);
            }
        }
        return foundOrder;
    }

    @Override
    public List<Order> findOrderAll(){
        return repository;
    }
}
