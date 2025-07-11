## [17. 打印从 1 到最大的 n 位数](https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/)

### 题目描述

输入数字 `n`，按顺序打印出从 `1` 最大的 `n` 位十进制数。比如输入 `3`，则打印出 `1、2、3` 一直到最大的 3 位数即 999。

### 解法：数学
小数直接用这个
````java
class Solution {
    public int[] countNumbers(int cnt) {
        int n = (int) Math.pow(10, cnt);
        int[] res = new int[n - 1];
        for (int i = 1; i < n; i++) {
            res[i - 1] = i;
        }
        return res;
    }
}
````

## 进阶：

此题需要注意 n 位数构成的数字可能超出最大的 int 或者 long long 能表示的范围。因此，采用字符数组来存储数字。

### （记住）解法:全排列

利用递归全排列，设置每一位，设置完之后，打印出来。
数据大用这个，效率更好
```java
class Solution {
    // 用于存储最终结果的列表
    StringBuilder result = new StringBuilder();
    int n;
    char[] num;
    char[] loop = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public String countNumbers(int n) {
        this.n = n;
        
        //num数组用来表示字符串，比如n等于2，则num数组为['0''0']、['0''1']、['0''2']...后边是将它转为字符串并按照左边界的位置进行截取的
        num = new char[n];
        dfs(0);//从char数组的第0位开始
        // 去除最后一个逗号
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    private void dfs(int x) {
        //结束条件：当前x的下标越过char数组的最后一位下标n-1，此时记录结果
        if (x == n) {
            String s = removeLeadingZeros(String.valueOf(num));//防止将"0"、"00"、"000"加进来
            // 过滤掉 0
            if (!s.isEmpty()) {
                result.append(s).append(",");
            }
            return;
        }
        //为当前位设置 0 - 9 的数字，添加完后进入下一位
        for (char i : loop) {// 比如x=2,运行：0 0,0 1,0 2,0 3,0 4....
            num[x] = i;
            dfs(x + 1);
        }
    }

    // 去除字符串前导零的方法
    private String removeLeadingZeros(String s) {
        int i = 0;
        while (i < s.length() - 1 && s.charAt(i) == '0') {
            i++;
        }
        return s.substring(i);
    }
}
```