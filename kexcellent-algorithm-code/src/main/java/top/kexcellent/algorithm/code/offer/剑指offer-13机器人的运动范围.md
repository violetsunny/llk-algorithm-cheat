## [13. 机器人的运动范围](https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)

### 题目描述

地上有一个 m 行和 n 列的方格。

一个机器人从坐标 `0,0` 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格。

但是不能进入行坐标和列坐标的数位之和大于 k 的格子。

请问该机器人能够达到多少个格子？

**样例 1**

```
输入：k=7, m=4, n=5

输出：20
```

**样例 2**

```
输入：k=18, m=40, n=40

输出：1484

解释：当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
      但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
```

**注意**:

1. 0<=m<=50
2. 0<=n<=50
3. 0<=k<=100

### 解法: 回溯

从坐标(0, 0) 开始移动，当它准备进入坐标(i, j)，判断是否能进入，如果能，再判断它能否进入 4 个相邻的格子 (i-1, j), (i+1, j), (i, j-1), (i, j+1)。

```java
class Solution {

    /**
     * 计算能到达的格子数
     *
     * @param threshold 限定的数字
     * @param rows 行数
     * @param cols 列数
     * @return 能到达的格子数
     */
    public int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        return getCount(threshold, rows, cols, 0, 0, visited);
    }

    private int getCount(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        if (check(threshold, rows, cols, i, j, visited)) {
            visited[i][j] = true;
            return 1 + getCount(threshold, rows, cols, i + 1, j, visited)
                    + getCount(threshold, rows, cols, i - 1, j, visited)
                    + getCount(threshold, rows, cols, i, j + 1, visited)//i-1 j-1 可以不用搜索，因为这个threshold规则
                    + getCount(threshold, rows, cols, i, j - 1, visited);
        }
        return 0;
    }

    private boolean check(int threshold, int rows, int cols, int i, int j, boolean[][] visited) {
        return i >= 0 && i < rows && j >= 0 && j < cols
                && !visited[i][j] && (getDigitSum(i) + getDigitSum(j) <= threshold);
    }

    private int getDigitSum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += (val % 10);
            val /= 10;
        }
        return sum;
    }
}
```