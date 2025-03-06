## [61. 扑克牌中的顺子](https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/)


### 题目描述

从扑克牌中随机抽 `5` 张牌，判断是不是一个顺子，即这 5 张牌是不是连续的。

`2～10` 为数字本身，`A` 为`1`，`J` 为 `11`，`Q` 为 `12`，`K` 为 `13`，大小王可以看做任意数字。

为了方便，大小王均以 `0` 来表示，并且假设这副牌中大小王均有两张。

**样例 1**

```
输入：[8,9,10,11,12]

输出：true
```

**样例 2**

```
输入：[0,8,9,11,12]

输出：true
```

### 解法一: 排序

- 对数组排序；
- 计算出 0 的个数 `zeroCount`；
- 从第一个不是 0 的数字开始遍历，与后一个数字比较，如果相等，直接返回 `false`；否则累计 `gap`；
- 判断 `zeroCount` 是否大于等于 `gap`。

$O(n*logn)$
```java
import java.util.Arrays;

class Solution {

    /**
     * 判断是否是连续的数字
     *
     * @param numbers 数组
     * @return 是否是顺子
     */
    public boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return false;
        }
        int zeroCount = 0;
        Arrays.sort(numbers);
        for (int e : numbers) {
            if (e > 0) {
                break;
            }
            ++zeroCount;
        }

        int p = zeroCount, q = p + 1, n = numbers.length;
        int gap = 0;
        while (q < n) {
            if (numbers[p] == numbers[q]) {
                return false;
            }
            gap += (numbers[q] - numbers[p] - 1);
            p = q;
            ++q;
        }
        return gap <= zeroCount;

    }
}
```
### 解法二: 哈希表

#### 帅地写法:

如果要成为顺子，要满足两个条件：
1. 不存在重复的数，大小王除外
2. 最大值 - 最小值 < 5,大小王除外

```java
import java.util.*;

class Solution {

    /**
     * 判断是否是连续的数字
     *
     * @param numbers 数组
     * @return 是否是顺子
     */
    public boolean isContinuous(int[] numbers) {
        Set<Integer> visted = new HashSet<>();
        int min = 20;
        int max = -1;
        for (int num : numbers) {
            if (num == 0) {
                continue;
            }
            if (visted.contains(num)) {
                return false;
            }
            visted.add(num);
            min = Math.min(min,num);
            max = Math.max(max,num);
        }
        return max - min < 5;// 0 6 7 0 9 true. 9-6=3 < 5
    }
}
```
