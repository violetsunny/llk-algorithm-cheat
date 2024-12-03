/**
 * LY.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package top.kexcellent.back.code;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 就看你写的细心不细心
 * @author kanglele
 * @version $Id: T3, v 0.1 2024/12/2 17:35 kanglele Exp $
 */
public class Calculate {
    public int calculate(String s) {
        //加 压栈 减 压负数栈  乘除与栈顶计算后压栈
        Stack<Integer> stack = new Stack<>();
        //s = s.trim();
        int n = s.length()-1;
        int i = 0;
        int num = 0;
        boolean numFlag = false;//1数字
        String pre = "";
        while(i<=n){
            if((s.charAt(i) >= '0' && s.charAt(i) <= '9')){
                int preNum = s.charAt(i) - '0';
                if(numFlag){
                    preNum = 10 * num + preNum;
                }
                num = preNum;
                numFlag = true;
            }

            if(!(s.charAt(i) >= '0' && s.charAt(i) <= '9') && s.charAt(i) != ' ' || i == n){
                switch(pre){
                    case "+":
                        stack.push(num);
                        break;
                    case "-":
                        stack.push(-num);
                        break;
                    case "*":
                        stack.push(stack.pop() * num);
                        break;
                    case "/":
                        stack.push(stack.pop() / num);
                        break;
                    default:
                        stack.push(num);
                }

                pre = s.charAt(i)+"";
                num = 0;//下一个数字
                numFlag = false;
            }

            i++;
        }

        int res=0;
        while(!stack.isEmpty()){
            res += stack.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        Calculate t3 = new Calculate();
        System.out.println(t3.calculate("1-334"));

        String[] cal = new String[]{"+","-","*","/"};
        List<String> s =Arrays.asList("+","-","*","/");

//        Character.isDigit(s.charAt(i));
    }
}
