/**
 * LY.com Inc.
 * Copyright (c) 2004-2024 All Rights Reserved.
 */
package top.kexcellent.back.code;

import java.util.Stack;

/**
 * 逆波兰表达式
 * @author kanglele
 * @version $Id: EvalRPN, v 0.1 2024/12/3 15:21 kanglele Exp $
 */
public class EvalRPN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token:tokens){
            switch(token){
                case "+":
                    int numA = stack.pop() + stack.pop();
                    stack.push(numA);
                    break;
                case "-":
                    int numB = -stack.pop() + stack.pop();
                    stack.push(numB);
                    break;
                case "*":
                    int numC = stack.pop() * stack.pop();
                    stack.push(numC);
                    break;
                case "/":
                    int num1 = stack.pop();
                    int num2 = stack.pop();
                    int numD = num2 / num1;
                    stack.push(numD);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }

        return stack.pop();
    }
}
