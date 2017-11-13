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
public class Array {
    public static int min(int []a,int m){
        int result = a[0];
        for(int i=1; i<m; i++) result = SoHoc.min(result, a[i]);       
        return result;
    }
}
