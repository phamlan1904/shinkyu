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
public class Item {
    private int itemNo;
    private String itemName;
    private int price;

    public int getItemNo() {
        return itemNo;
    }

    public void setItemNo(int itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public String[] toArray() {
        int itemNoA = getItemNo();
        String itemNameA = getItemName();
        int priceA = getPrice();
        String[] ret = new String[3]; 
        ret[0] = Integer.toString(itemNoA);
        ret[1] = itemNameA;
        ret[2] = Integer.toString(priceA);
        
        return ret;
    }
    
    @Override
    public String toString() {
        return getItemNo() + ", " + getItemName() + ", " + getPrice();
    }
}

