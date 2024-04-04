package service;

import data.Customer;
import repository.customerRepo.MemoryCustomerRepository;

import java.util.List;

public class CustomerService {
    MemoryCustomerRepository memoryCustomerRepository=MemoryCustomerRepository.getInstance();
    boolean check;
    //정보 입력
    public boolean inputCustomer(Customer newCustomer) {
        check=memoryCustomerRepository.inputCustomer(newCustomer);
        return check;
    }

    //정보 삭제
    public boolean deleteCustomer (long id)
    {
        check= memoryCustomerRepository.deleteCustomer(id);
        return check;
    }

    //이름으로 검색
    public Customer findByName(String name) {
        return memoryCustomerRepository.findByName(name);

    }

    //id로 검색
    public Customer findById(long id) {
        return memoryCustomerRepository.findById(id);

    }

    //전체 조회
    public List<Customer> findAll() {
        return memoryCustomerRepository.findAll();
    }

    public Customer findByBirth(String mmdd) {
        return memoryCustomerRepository.findByBirth(mmdd);
    }

}
