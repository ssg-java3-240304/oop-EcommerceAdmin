package repository.customerRepo;

import data.Customer;

public interface CustomerRepository {
    public Customer findById(long id);
}
