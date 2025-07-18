# [基本计算器 II](https://leetcode.cn/problems/basic-calculator-ii/description/)
你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。

整数除法仅保留整数部分。

你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。

注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。

````
示例 1：

输入：s = "3+2*2"
输出：7
示例 2：

输入：s = " 3/2 "
输出：1
示例 3：

输入：s = " 3+5 / 2 "
输出：5
````
提示：

- 1 <= s.length <= 3 * 105
- s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
- s 表示一个 有效表达式
- 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
- 题目数据保证答案是一个 32-bit 整数

### （记住）解法：栈
遇到 * / 再计算好后放入栈，最后再加(减的化，放入时放负数)。
````java
class Solution {
    public int calculate(String s) {
        //加 压栈 减 压负数栈  乘除与栈顶计算后压栈
        Stack<Integer> stack = new Stack<>();
        //s = s.trim();
        int n = s.length() - 1;
        int i = 0;
        int num = 0;
        boolean numFlag = false;//1数字
        String pre = "";
        while (i <= n) {
            if ((s.charAt(i) >= '0' && s.charAt(i) <= '9')) {
                int preNum = s.charAt(i) - '0';
                if (numFlag) {
                    preNum = 10 * num + preNum;//进位
                }
                num = preNum;
                numFlag = true;
            }

            if (!(s.charAt(i) >= '0' && s.charAt(i) <= '9') && s.charAt(i) != ' ' || i == n) {
                switch (pre) {
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

                pre = s.charAt(i) + "";
                num = 0;//下一个数字
                numFlag = false;
            }

            i++;
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
````