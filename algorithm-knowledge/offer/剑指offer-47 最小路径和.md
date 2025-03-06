## [64. 最小路径和](https://leetcode.cn/problems/minimum-path-sum/description/)

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。


````
示例 1：


输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
输出：7
解释：因为路径 1→3→1→1→1 的总和最小。
示例 2：

输入：grid = [[1,2,3],[4,5,6]]
输出：12
````

提示：

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 200
- 0 <= grid[i][j] <= 200

### 解法：动态规划

```java
res[i][j] = Math.min(res[i - 1][j], res[i][j - 1]) + grid[i][j];
```

````java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] row : dp) {
            Arrays.fill(row, -1); // -1 表示没有计算过
        }

        return min(grid, 0, 0, dp);
    }

    private int min(int[][] grid, int i, int j, int[][] dp) {
        if (i > grid.length - 1 || j > grid[0].length - 1) {
            return Integer.MAX_VALUE;
        }
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return grid[i][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        dp[i][j] = Math.min(min(grid, i + 1, j, dp), min(grid, i, j + 1, dp)) + grid[i][j];
        ;
        return dp[i][j];
    }
}
````

#### 其他写法

````java
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length, columns = grid[0].length;
        int[][] dp = new int[rows][columns];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < rows; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < columns; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1];
    }
}
````

#### 空间优化

````java
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int columns = grid[0].length;
        int[] dp = new int[columns];
        Arrays.fill(dp, Integer.MAX_VALUE);//需要初始化，不然选不到最小的
        dp[0] = 0;
        for (int i = 0; i < rows; i++) {
            dp[0] = dp[0] + grid[i][0];//初始化第一个值，这样才好选择最小
            for (int j = 1; j < columns; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[columns - 1];
    }
}
````