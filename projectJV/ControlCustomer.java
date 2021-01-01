/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

//import System.SystemControl;
import java.util.ArrayList;
import java.util.List;

/**
 *  Memberテーブル問い合わせ処理　コントロール
 * @author 17jz0127
 */
public class ControlCustomer {

    
    private BoundaryCustomer boundary;
    private List<Customer>    customerList;
    private CustomerDAO       customerDAO;
    private SystemControl   control;
    
    
    public ControlCustomer() {
        boundary    = new BoundaryCustomer();
        customerList  = new ArrayList<>();
        customerDAO   = new CustomerDAO();

    }   
    public void setControl(SystemControl control) {
        this.control = control;
    }    
    public void exit() {
        boundary.setVisible(false);
        //control.exitCustomer();
    }
    public void start() {
        
        boundary.setControl(this);
        boundary.reset();
        boundary.setVisible(true);
        
    }

    static void showErrorDialog() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    public void insertCustomer(String telNo, String name, String address) {
        Customer customer = new Customer(telNo);
        customer.setName(name);
        customer.setAddress(address);
        customer.setOrderCount(0);
        customer.setServiceCount(0);
        
        customerDAO.insertCustomer(customer);
        
    }

    public Customer searchCustomer(String telNo) {
        customerList = customerDAO.dbSearchCustomerTelNo(telNo);  // DAOを使ってカスタマーを検索、検索した結果をcustomerListに格納する
        if(customerList.size() > 0) {
        return customerList.get(0); //customerの情報を返す
        }
        else {
            return null;
        }
    }
    
    public void showBoundaryCustomer(String telNo) {
        boundary.setTelNo(telNo);
        start();
    }
    
    public void showBoundaryCustomer() {
        boundary.changeTelNoArea(true);
        start();
    }
    
    public void addOrderCount(String telNo, int serviceCount) {
        customerDAO.setOrderCount(telNo, serviceCount);
    }

    void showBoundaryOrder(String telNo, String name, String address) {
        control.showBoundaryOrder(telNo, name, address);
    }
}
