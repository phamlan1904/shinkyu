/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

//import dbTest.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 会員エンティティクラス
 * @author 17JZ0135 PHAM LAN
 */
public class Customer {
    private String telNo;
    private String name;
    private String address;
    
    private int orderCount;
    private int serviceCount;
   
   
    
    public Customer(String telNo) {
        setTelNo(telNo);
        
    }
    
    
    
    @Override
    public String toString() {
        return getTelNo();
    }
    public void print() {
        System.out.print(this);
    }
    
    public void println() {
        print();
        System.out.println("");
    }
    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    

    

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }
    
    public int getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(int serviceCount) {
        this.serviceCount = serviceCount;
    }

    

    
    

    public static void main(String[] args ) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("0311111111"));
        customers.add(new Customer("0322222222"));
        customers.add(new Customer("0333333333"));
        customers.add(new Customer("0344444444"));
        
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    
}
