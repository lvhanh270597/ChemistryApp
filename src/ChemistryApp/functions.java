/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChemistryApp;

/**
 *
 * @author Thien Trang
 */

import Interface.*;

public class functions {
    public static boolean buttonClicked;
    public static chatBotInterface a;
    public static void func1(){
        a = new chatBotInterface();
        a.initText("Xin chào!!!",70,35);
        a.initText("Để nhận biết một chất chưa rõ ",180,35);
        a.initText("Bạn hãy trả lời những câu hỏi của tôi",220,35);
        a.initText("Bạn có quỳ tím không? Có hay không?",200,35);
        a.initText("Xin trả lời không dấu VD: có trả lời co, không trả lời khong",330,35);
        a.show();
        
    }    
    public static void func2(){        
        while (a == null){
            System.out.println("Creating object a");
        }
        a.Bot();
    }    
}
