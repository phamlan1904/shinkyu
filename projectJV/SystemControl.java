/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

/**
 *
 * @author 17JZ0135
 */
public class SystemControl {
    private ControlCustomer       controlCustomer;
    private ControlOrder          controlOrder;
    
   
    public SystemControl() {
       
        controlCustomer = new ControlCustomer();
        controlOrder    = new ControlOrder();
    }
    public void start() {
        controlOrder.setControl(this);
        controlOrder.start();
        controlCustomer.setControl(this);
    }
    public Customer searchCustomer(String telNo) {
        Customer customer = controlCustomer.searchCustomer(telNo); //controlCustomerを使ってcustomerの情報を検索
        return customer;
    }
    public void showBoundaryCustomer(String telNo) {
        controlCustomer.showBoundaryCustomer(telNo);
    }
    public void showBoundaryCustomer() {
        controlCustomer.showBoundaryCustomer();
    }
    
    void showBoundaryOrder(String telNo, String name, String address) {
        controlOrder.showBoundaryOrder(telNo, name, address);
    }
    
    public void addCount(String telNo, int serviceCount) {
        controlCustomer.addOrderCount(telNo, serviceCount);
    }
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new SystemControl().start();  // コントロールをnewし、start()メソッドを呼び出す
    }    
}
