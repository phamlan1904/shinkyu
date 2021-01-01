/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;


import com.sun.xml.internal.ws.api.ha.StickyFeature;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17JZ0135
 */
class ControlOrder {
    private BoundaryOrder   boundaryOrder;  // バウンダリークラス 
    private SystemControl   controlSystem;
    private OrderDAO        orderDAO;
//    private List<Order>     orderList;
    private ItemDAO         itemDAO;
    private List<Item>      itemList;       // アイテムリスト
    private boolean         discountFlag;   // 割引期間の有無
     
    public ControlOrder() {
       boundaryOrder    = new BoundaryOrder();
//       orderList   = new ArrayList<>();
       orderDAO    = new OrderDAO();
       boundaryOrder.setControl(this);
       itemDAO     = new ItemDAO();
       itemList    = new ArrayList<Item>();
       discountFlag = false;
    }
    public void start() {
        boundaryOrder.setControl(this);
        boundaryOrder.setItems();
        boundaryOrder.setDate();
        boundaryOrder.setVisible(true);
        
    }
    public Customer searchCustomer(String telNo) {
        Customer customer = controlSystem.searchCustomer(telNo);
   
        return customer;
    }  
    
    public List<Item> searchItem() {
         itemList = itemDAO.dbSearchItemAll();
         return itemList;
    }
    
    public Item showSearchItemNo(int index) {
        return itemDAO.dbSearchItemNo(index);
    }

    public void setControl(SystemControl control) {
        this.controlSystem = control;
        
    }
    /**
     * 顧客情報の検索
     * @param telNo 電話番号
     */
    public void showBoundaryCustomer(String telNo) {
        
        controlSystem.showBoundaryCustomer(telNo);
    }
    public void showBoundaryCustomer() {
        controlSystem.showBoundaryCustomer();
    }
    
    /**
     * 現時点での合計金額を算出する
     * @return　String[合計金額, 割引額, ] 
     */
    public String[] calcPrice() {
        int total = 0;
        String[] calc = new String[3];
        calc[1] = "0";
        for (int i = 0; i < boundaryOrder.orderViewTableModel.getRowCount(); i++) {
            //if(!(boolean)boundaryOrder.orderViewTableModel.getValueAt(i, 5)) {
                total +=   Integer.parseInt(boundaryOrder.orderViewTableModel.getValueAt(i, 4).toString());
        }
        
        calc[0] = Integer.toString(total);
        if(discountFlag) {
            calc[2] = Integer.toString((int)(total * 0.9));
            calc[1] = Integer.toString(total - Integer.parseInt(calc[2]));
        }
        else {
            calc[2] = Integer.toString(total);
        }
        return calc;
    }
    
    /**
     * 時間を切り替えたとき割引する時間帯かを判定する
     * @param hour 配達時間の時
     */
    public void switchDiscount(int hour) {
        discountFlag = ((10 <= hour && hour < 12) || (14 <= hour && hour < 16 )); // 割引の時間であれば
    }
    
    /**
     * チェックがついている列の数を保存する
     * @return　select チェックがついている列の数
     */
    public int rowCount() {
        int select = 0;
        for(int i = 0; i < boundaryOrder.orderViewTableModel.getRowCount(); i++) {
        //    if(!(boolean)boundaryOrder.orderViewTableModel.getValueAt(i, 5)) {
                select++;
                
         //   }
        }
        return select;
    }
    
    public void insertOrderDetail(String telNo, int total, int discountPrice) {
        Order order = new Order(); // 注文伝票表の作成
        int orderNo;
        
        order.setTelNo(telNo);
        order.setTotal(total);
        order.setDiscountPrice(discountPrice);
        orderNo = insertOrder(order);
        
        for(int i = 0; i < boundaryOrder.orderViewTableModel.getRowCount(); i++) {
//            if(!(boolean)boundaryOrder.orderViewTableModel.getValueAt(i, 5)) {
                OrderDetail orderDetail = new OrderDetail();                                   // 注文明細表の作成
                orderDetail.setItemNo(boundaryOrder.orderViewTableModel.getValueAt(i, 0).toString());
                orderDetail.setOrderNumber(orderNo);
                orderDetail.setItemCount(Integer.parseInt(boundaryOrder.orderViewTableModel.getValueAt(i, 3).toString()));
                orderDetail.setPrice(Integer.parseInt(boundaryOrder.orderViewTableModel.getValueAt(i, 2).toString()));
                
                insertOrderDetail(orderDetail);
 //          }
        }
        
    }
    
    public void addOrderCount(String telNo, int serviceCount) {
        controlSystem.addCount(telNo, serviceCount);
    }
    
    public int insertOrder(Order order) {
        return orderDAO.insertOrder(order);
        
    }
    void insertOrderDetail(OrderDetail orderDetail) {
        itemDAO.insertOrderDetail(orderDetail);
        
    }
    
    /**
     * 検索結果を表示する
     * @param telNo         電話番号
     * @param name          名前
     * @param address       アドレス
     */
    void showBoundaryOrder(String telNo, String name, String address) {
        start();
        boundaryOrder.setParameter(telNo, name, address);
    }
    
    
    public static void main(String[] args) {
        ControlOrder controlOrder = new ControlOrder();
        controlOrder.start();
    }
}

