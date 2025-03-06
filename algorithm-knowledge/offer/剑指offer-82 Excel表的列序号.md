## [Excel表列序号](https://leetcode.cn/problems/excel-sheet-column-number/description/)
给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。
````
例如：

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...
 

示例 1:

输入: columnTitle = "A"
输出: 1
示例 2:

输入: columnTitle = "AB"
输出: 28
示例 3:

输入: columnTitle = "ZY"
输出: 701
 
````
提示：

- 1 <= columnTitle.length <= 7
- columnTitle 仅由大写英文组成
- columnTitle 在范围 ["A", "FXSHRXW"] 内

### 解法：26进制转10进制
````java
class Solution {
    public int titleToNumber(String columnTitle) {
        int n = columnTitle.length();
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = columnTitle.charAt(i);
            int num = c - 'A' + 1;
            count = count * 26 + num;
        }

        return count;
    }
}
````