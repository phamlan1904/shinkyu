/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.Scanner;

/**
 *
 * @author plvnjp95
 */
public class sample {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.next(); //文字列を読み込む
        if(str.endsWith("ch") || str.endsWith("sh")) {
            System.out.println(str + "es");
        }
        else if(str.endsWith("y")) {
            String out = str.substring(0, str.length() - 1);
            System.out.println(str + "ies");
        }
        else {
            System.out.println(str + "s");
        }
        
    }
    
}
