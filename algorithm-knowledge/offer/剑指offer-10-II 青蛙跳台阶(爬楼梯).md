## [10- II. 青蛙跳台阶问题](https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/)
同：[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/description/)
### 题目描述

一只青蛙一次可以跳上`1`级台阶，也可以跳上`2`级。求该青蛙跳上一个`n`级的台阶总共有多少种跳法（先后次序不同算不同的结果）。

### （记住）解法：动态规划

跳上 `n` 级台阶，可以从 `n-1` 级跳 `1` 级上去，也可以从 `n-2` 级跳 `2` 级上去。所以

```
f(n) = f(n-1) + f(n-2)
比如：
f(1) = {1}
f(2) = {1 2}, {2}
f(3) = {1 3}, {1 2 3},{2 3}
f(4) = {1 2 4},{2 4}, {1 3 4},{1 2 3 4},{2 3 4}
f(5) = {1 3 5},{1 2 3 5},{2 3 5}, {1 2 4 5},{2 4 5},{1 3 4 5},{1 2 3 4 5},{2 3 4 5}
由上可以看出:
f(3) = f(1) + f(2);也就是将3放入f(1)中{1 3},3放入f(2)中{1 2 3},{2 3}
f(4) = f(2) + f(3);
f(5) = f(3) + f(4);
也就看出f(5)就是将5元素放入f(3)中加上f(4)中，组合得出的值，也就是f(3)跳两步加上f(4)跳一步
```

```java
class Solution {

    /**
     * 青蛙跳台阶
     *
     * @param target 跳上的那一级台阶
     * @return 多少种跳法
     */
    public int JumpFloor(int target) {
        if (target < 2) {
            return target;
        }
        int a = 1, b = 2;
        for (int i = 3; i <= target; ++i) {
            b = a + b;
            a = b - a;
        }
        return b;
    }
}
```