/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

//import dbTest.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * 顧客　DAO
 * @author 17JZ0135
 */
public class CustomerDAO {
    private static Connection con;
    private static PreparedStatement ps;
    
    
    
    public CustomerDAO() {
    DBManager dbManager = DBManager.getDBManager();
    con = dbManager.getConnection();
    }
    
    public List<Customer> selectCustomerExecute() {
        List<Customer> customerList = new ArrayList<>();
        try {
            customerList.clear();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer("0000000000");
                setCustomer(customer, rs);
                customerList.add(customer);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    
    
    
    public void setCustomer(Customer customer, ResultSet rs) {
        try {
            String telNo = rs.getString("TELNO");
            String name = rs.getString("NAME");
            String address = rs.getString("ADDRESS");
            int orderCount = rs.getInt("ORDERCOUNT");
            int serviceCount = rs.getInt("SERViCECOUNT");
            
           
            customer.setTelNo(telNo);
            customer.setName(name);
            customer.setAddress(address);
            customer.setOrderCount(orderCount);
            customer.setServiceCount(serviceCount);
           
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertCustomer(Customer customer) {
            String sql = "INSERT INTO CUSTOMER values(?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, customer.getTelNo());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getAddress());
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> dbSearchCustomerTelNo(String telNo) {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * " + " FROM CUSTOMER " + " WHERE TELNO = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, telNo);
            
            customerList = selectCustomerExecute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }

    public void setOrderCount(String telNo, int serviceCount) {
        String sql = "UPDATE CUSTOMER SET ORDERCOUNT = ?, SERVICECOUNT = ? WHERE TELNO = ? ";
        int countA = dbSearchCustomerTelNo(telNo).get(0).getOrderCount();
        int countB = serviceCount;
         try {
            countA++;
            if(countA % 5 == 0) {
                countB++;
            }
            ps = con.prepareStatement(sql);
            ps.setInt(1, countA);
            ps.setInt(2, countB);
            ps.setString(3, telNo);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 自己クラステスト用main
     * @param args 
     */
    public static void main(String[] args) {
        List<Customer>    customerList = new ArrayList<>();
        CustomerDAO       customerDAO  = new CustomerDAO();
        
        System.out.println("------------------------------------------ CustomerTelNo検索");
        //System.out.println(customerDAO.dbSearchCustomerTelNo("031111111").
        customerList = customerDAO.dbSearchCustomerTelNo("031111111");
        //System.out.println(customerList.);
       
        for(Customer customer : customerList) {
            System.out.print(customer);
        }
        System.out.println("");
        

    }
}
