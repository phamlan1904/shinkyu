/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectJV;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * 会員　DAO
 * @author 17JZ0127
 */
public class ItemDAO {
    private static Connection con;
    private static PreparedStatement ps;
    
    public ItemDAO() {
    DBManager dbManager = DBManager.getDBManager();
    con = dbManager.getConnection();
    }
    
    public List<Item> selectItemExecute() {
        List<Item> itemList = new ArrayList<>();
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
            Integer id = rs.getInt("ITEMNO");
            String name = rs.getString("ITEMNAME");
            Integer price = rs.getInt("PRICE");
            item.setItemNo(id);
            item.setItemName(name);
            item.setPrice(price);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Item> dbSearchItemAll() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * " + " FROM ITEM ORDER BY ITEMNO ";
        try {
            ps = con.prepareStatement(sql);
            itemList = selectItemExecute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    
    /**
     * ITEMNO指定による検索
     * @param itemno    検索キーID
     * @return      検索結果リスト
     */
    public Item dbSearchItemNo(int itemno) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * " + " FROM ITEM " + " WHERE ITEMNO = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, itemno);
            
            itemList = selectItemExecute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return itemList.get(0);
    }
    
    public int insertOrderDetail(OrderDetail orderDetail) {
            String sql = "INSERT INTO ORDERDETAIL (ORDERNO, ITEMNO, ITEMCOUNT, PRICE )values(?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderDetail.getOrderNumber());
            ps.setString(2, orderDetail.getItemNo());
            ps.setInt(3, orderDetail.getItemCount());
            ps.setInt(4, orderDetail.getPrice());
            
         
            System.out.println(sql);
            return ps.executeUpdate();
            
        }
    
        catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    
    /**
     * 自己クラステスト用main
     * @param args 
     */
    public static void main(String[] args) {
    }
}
