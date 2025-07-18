## [字符串相加](https://leetcode.cn/problems/add-strings/description/)

给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。


````
示例 1：

输入：num1 = "11", num2 = "123"
输出："134"
示例 2：

输入：num1 = "456", num2 = "77"
输出："533"
示例 3：

输入：num1 = "0", num2 = "0"
输出："0"
````



提示：

- 1 <= num1.length, num2.length <= 10^4
- num1 和num2 都只包含数字 0-9
- num1 和num2 都不包含任何前导零

### （记住）解法：遍历
```java
class Solution {
    public String addStrings(String num1, String num2) {
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;//进位
        StringBuilder sb = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? num1.charAt(l1) - '0' : 0;
            int y = l2 >= 0 ? num2.charAt(l2) - '0' : 0;//超过长度按0处理
            int sum = x + y + carry;
            carry = sum / 10;//进位
            sum = sum % 10;
            sb.append(sum);
            l1--;
            l2--;
        }
        //有进位
        if (carry == 1) {
            sb.append(carry);
        }

        return sb.reverse().toString();//将拼接结果倒过来
    }
}
```