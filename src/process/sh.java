/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

/**
 *
 * @author Hanh
 */
public class sh {
    public static int gcd(int a, int b){
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
