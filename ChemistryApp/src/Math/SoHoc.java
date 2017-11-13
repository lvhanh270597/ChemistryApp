/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;

/**
 *
 * @author Hanh
 */
public class SoHoc {
    public static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a % b);
    } 
    public static int min(int a, int b){
        return (a < b ? a : b);
    }
}    
