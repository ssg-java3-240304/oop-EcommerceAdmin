package data;

import java.time.LocalDateTime;

public class Order {
    static long sequenceNum = 0L;
    long id;
    private Customer customer;
    private Product product;
    private int quantity;
    private String deliveryAddr;
    private LocalDateTime orderDateTime;
    private int discount;

    public Order(Customer customer, Product product, int quantity, String deliveryAddr, int discount) {
        this.id = ++sequenceNum;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.deliveryAddr = deliveryAddr;
        this.discount = discount;
        this.orderDateTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryAddr() {
        return deliveryAddr;
    }

    public void setDeliveryAddr(String deliveryAddr) {
        this.deliveryAddr = deliveryAddr;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", product=" + product +
                ", quantity=" + quantity +
                ", deliveryAddr='" + deliveryAddr + '\'' +
                ", orderDateTime=" + orderDateTime +
                ", discount=" + discount +
                '}';
    }
}
