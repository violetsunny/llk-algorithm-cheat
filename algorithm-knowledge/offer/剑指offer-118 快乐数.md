## [202. 快乐数](https://leetcode.cn/problems/happy-number/description/)

编写一个算法来判断一个数 n 是不是快乐数。
````
「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
````

````
示例 1：

输入：n = 19
输出：true
解释：
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
示例 2：

输入：n = 2
输出：false
````

提示：

- 1 <= n <= 231 - 1

### 解法：遍历 + 去重
1. 第一种情况结果为1
2. 第二种情况结果死循环
3. 第三种情况无限计算：不会出现，因为按照快乐数的计算方式，计算结果会往下掉要么循环要么为1。比如999999=486
````java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();
        while (n != 1 && !visited.contains(n)) {
            visited.add(n);
            int total = 0;
            while (n > 0) {
                int d = n % 10;
                n = n / 10;
                total += d * d;
            }
            n = total;
        }
        return n == 1;
    }
}
````

### 解法：快慢指针（双指针）
同时进行快乐数计算，如果快指针和慢指针相遇，说明计算不出，如果快指针到达1说明结束。
````java
class Solution {
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
````
