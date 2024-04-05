package data;

import java.time.LocalDate;

public class Customer {
    private long id;// 이건 해쉬값인가?? 이건 내가 찾아봐야겟네//set과 list 고민해봐
    private String name;
    private int age;
    private String phoneNumber;
    private String address;
    private LocalDate birth;
    private String tier; //이거 티어는 내가 정하는 건지?? ex) bronze, silver, gold, vip 등등

    public Customer(long id, String name, int age, String phoneNumber, String address, LocalDate birth, String tier) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.birth = birth;
        this.tier = tier;
    }

    @Override
    public String toString() {
        return
                "id: '" + id +'\''+
                " 이름: '" + name + '\'' +
                " 나이: '" + age +'\'' +
                " 전화번호: '" + phoneNumber + '\'' +
                " 주소: '" + address + '\'' +
                " 생년월일: '" + birth +
                " 등급: '" + tier + '\'';
    }

    public long id() {
        return id;
    }

    public void setUuid(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }
}
