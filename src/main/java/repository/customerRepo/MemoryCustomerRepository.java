package repository.customerRepo;

import data.Customer;

import javax.swing.text.DateFormatter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class MemoryCustomerRepository {
    private static MemoryCustomerRepository memoryCustomerRepository;
     List<Customer> customerList =new ArrayList<>();
    private MemoryCustomerRepository() {}

    //저장소 싱글톤으로 생성
    public static MemoryCustomerRepository getInstance()
    {
        if(memoryCustomerRepository==null)
        {
            memoryCustomerRepository=new MemoryCustomerRepository();
        }
        return memoryCustomerRepository;
    }

    //회원 정보를 받고 리스트에 추가 / 단 여기서 중복 테스트를 해야 한다
    public boolean inputCustomer(Customer intputCustomer)
    {
        for(Customer customer: customerList)
        {
            if(customer.id()==intputCustomer.id())
                return false;
        }
        customerList.add(intputCustomer);
        return true;
    }

    public boolean deleteCustomer(long id) {
        // 어떤 값이 와야지만 삭제를 한다
        for (Customer customer : customerList) {
            if (customer.id()==id)// 맞는게 없으면 false 리턴
            {
                customerList.remove(customer);// 맞는게 있으면 제거
                return true;
            }
        }
        return false;
    }

    public Customer findByName(String name) {
        for(Customer customer: customerList)
        {
            if(customer.getName().equals(name))
                return customer;
        }
        return null;
    }

    public Customer findById(long id) {
        for(Customer customer: customerList)
        {
            if(customer.id()==id)
                return customer;
        }
        return null;
    }

    public List<Customer> findAll() {
        if(customerList.isEmpty()) {
            return null;
        }
        return customerList;
    }

    public Customer findByBirth(String inputmmdd) {
//        BiFunction<String ,String ,Boolean> biFunction=String::equals;
//        System.out.printf("동등비교 : ");
//        System.out.println(biFunction.apply("안녕하세요","안녕하세요")+"\n");

        for(Customer customer:customerList)
        {
            String[] MMDD= customer.getBirth().toString().split("-");//1992-02-24 => 19920224 로 바꾸고
            String mmdd=String.join("",MMDD).substring(4);// 19920224 -> 0224 월일로 바꾼다

            System.out.println("MMDD : "+ MMDD.toString());

            if(mmdd.equals(inputmmdd))
                return customer;
        }
        return null;
    }
}