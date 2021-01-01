/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17JZ0135
 */
class OrderDAO {

    private static Connection con;
    private static PreparedStatement ps;
    
    
    
    public OrderDAO() {
    DBManager dbManager = DBManager.getDBManager();
    con = dbManager.getConnection();
    }
    
    public List<Item> selectItemExecute() {
        List<Item> itemList = new ArrayList<Item>();
        try {
            itemList.clear();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                setItem(item, rs);
                itemList.add(item);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    public void setItem(Item item, ResultSet rs) {
        try {
            int itemNo = rs.getInt("ITEMNO");
            String itemName = rs.getString("ITEMNAME");
            int price = rs.getInt("PRICE");
            
           
            item.setItemNo(itemNo);
            item.setItemName(itemName);
            item.setPrice(price);
            
           
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Item searchItem(int itemNo) {
        
        List<Item> itemList = new ArrayList<Item>(); 
        String sql = "SELECT * " + " FROM ITEM " + " WHERE ITEMNO = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemNo);
            
            itemList = selectItemExecute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList.get(0);
    }
    public Item searchItem(String itemName) {
        
        List<Item> itemList = new ArrayList<Item>(); 
        String sql = "SELECT * " + " FROM ITEM " + " WHERE ITEMNAME = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, itemName);
            
            itemList = selectItemExecute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList.get(0);
    }
    
    
     public int insertOrder(Order order) {
            String sql = "INSERT INTO ORDERS (TELNO, TOTAL, DISCOUNTPRICE) values (?, ?, ?) ";
            //String sqlR = "SELECT ORDERNO FROM (SELECT * FROM ORDERS ORDER BY ORDERNO DESC WHERE "
 //           String sqlR = "SELECT LAST_VALUE(ORDERNO) OVER(ORDER BY ORDERNO DESC) AS MAXNO FROM ORDERS ";
            String sqlR = "SELECT MAX(ORDERNO) AS MAXNO FROM ORDERS";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, order.getTelNo());
            ps.setInt(2, order.getTotal());
            ps.setInt(3, order.getDiscountPrice());
    
            ps.executeUpdate();
            
            ps = con.prepareStatement(sqlR);
            ResultSet pa = ps.executeQuery();
            pa.next();
            return pa.getInt("MAXNO");
        }
    
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        
        
    }
     
     public int getOrderCurrVal() {
         String sql = "SELECT AUTOINCREMENT VALUE from sys.systables t, sys.syscolumns c where t.tablename='ORDER' "
                 + "and c.referenceid=t.tableid and c.columnname='ORDERNO'";
           
              
         int result = 0;
         
         try {
            ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = rs.getInt(0);
            
            rs.close();
           
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
     }
     
     
    
}
