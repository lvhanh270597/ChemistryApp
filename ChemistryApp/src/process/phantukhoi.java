/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import javafx.util.Pair;
import Math.*;
import process.*;
import Basic.*;
import knowledge.*;
import java.util.*;
import javafx.util.*;
import knowledge.knowledge;
import java.util.Stack; 
/**
 *
 * @author OS
 */
public class phantukhoi {
    public static float PhanTuKhoi(String a) {
    Stack <Integer> count = new Stack<Integer>();
    Stack <String> element = new Stack<String>();
    float ptk=0;
    for(int i=0;i<a.length();i++){
        if(i+1<a.length())
        if(((int)a.charAt(i)>=65&& (int)a.charAt(i)<=90)&&(int)a.charAt(i+1)<97){// Case have one Element
            if(i+1<=a.length())
                if((int)a.charAt(i+1)<49||(int)a.charAt(i+1)>57)
                count.push(1);
                element.push(a.substring(i, i+1));
        }
        if(((int)a.charAt(i)>=65&& (int)a.charAt(i)<=90)&&i+1==a.length()){// Element Final
            count.push(1);
            element.push(a.substring(i, i+1));
        }
        if(i+1<a.length())
        if(((int)a.charAt(i)>=65&& (int)a.charAt(i)<=90)&& ((int)a.charAt(i+1)>=97&&(int)a.charAt(i+1)<=122)){// Case have two Element
            if(i+2<a.length())
            if((int)a.charAt(i+2)<49||(int)a.charAt(i+2)>57)
            count.push(1);
            if(a.length()==2) count.push(1);
            element.push(a.substring(i, i+2));
             i++;
        }
        if(i+1<a.length()){
        if((int)a.charAt(i)>=49&& (int)a.charAt(i)<=57&& ((int)a.charAt(i+1)<48||(int)a.charAt(i+1)>57))// case have 1 number
            count.push((int)a.charAt(i)-48);
        if((int)a.charAt(i)>=49&& (int)a.charAt(i)<=57 &&(int)a.charAt(i+1)>=48&&(int)a.charAt(i+1)<=57){// case have two number
            count.push(((int)a.charAt(i)-48)*10+((int)a.charAt(i+1)-48));
            i++;
        }
        }
        if((int)a.charAt(i)>=49&& (int)a.charAt(i)<=57&& i+1 == a.length())// Number Final
            count.push((int)a.charAt(i)-48);
        if((int)a.charAt(i)>=49&& (int)a.charAt(i)<=57&& i+2==a.length()){
            count.push(((int)a.charAt(i)-48)*10+((int)a.charAt(i+1)-48));
            i++;
        }
        if((int)a.charAt(i)=='('){ // Case have "("
            count.push((int)a.charAt(i));
            element.push(a.substring(i, i+1));
        }
        if((int)a.charAt(i)==')'){// case befor ")" have 1 number
            if((int)a.charAt(i+1)>=49&& a.charAt(i+1)<=57){
            int x = a.charAt(i+1)-48;
            element.push(a.substring(i,i+1));
            count.push(0);
            Stack <Integer> temp = new Stack<Integer> ();
            while(count.peek()!=40){// get number in count
                temp.push(count.peek()*x);
                count.pop();
            }
            count.pop();
            count.push(0);
            int k = temp.size();
            for(int j=0;j<k;j++){
                count.push(temp.peek());
                temp.pop();
            }
            i++;
            }
            else{
                element.push(a.substring(i, i+1));
                count.push(0);
                Stack <Integer> temp = new Stack <Integer>();
                while(count.peek()!=40){// get number in count
                temp.push(count.peek());
                count.pop();
                }
                count.pop();
                count.push(0);
                int k = temp.size();
                for(int j=0;j<k;i++){
                count.push(temp.peek());
                temp.pop();
            }
            }
        }
    }
    for(int i=0;i<element.size();i++){
        if(element.get(i).equals(i)==false&&element.get(i).equals(i)==false){
            ptk+= knowledge.nguyenTo.get(element.get(i)).getM()*count.get(i);
        }
    }
    return ptk;
    }
}
