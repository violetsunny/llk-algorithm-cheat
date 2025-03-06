## [矩阵中的最长递增路径](https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/description/)

给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。

 

示例 1：
````
9  9  4
|
6  6  8
|
2——1  1
````
输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
输出：4
解释：最长递增路径为 [1, 2, 6, 9]。
示例 2：
````
3——4——5
      | 
3  2  6

2  2  1
````

输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
输出：4
解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
示例 3：

输入：matrix = [[1]]
输出：1
 

提示：

- m == matrix.length
- n == matrix[i].length
- 1 <= m, n <= 200
- 0 <= matrix[i][j] <= 231 - 1

### 解法：记忆化深度优先搜索+回溯

关系式：
````
memo[i][j]=max{memo[x][y]}+1
其中 (x,y) 与 (i,j) 在矩阵中相邻，并且 matrix[x][y]>matrix[i][j]
````

```java
import java.util.Arrays;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length, n = matrix[0].length;
        int[][] visited = new int[m][n];//将上次的结果存下来，如果下次符合条件可以直接取上次的+1
        int max = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {//如果找不到需要回溯
                max = Math.max(max, hasPath(matrix, i, j, visited));//每次的值都可以是一个起点，所以每次统计的结果都要比大小
            }
        }

        return max;
    }


    private int hasPath(int[][] matrix, int i, int j, int[][] visited) {
        if (visited[i][j] != 0) return visited[i][j];//可能上次已经计算过

        int hasPath = 1;//至少一个，让四个方向的结果依次比大小
        if (i + 1 < matrix.length && matrix[i + 1][j] > matrix[i][j]) {
            hasPath = Math.max(hasPath, hasPath(matrix, i + 1, j, visited) + 1);//如果是上次计算过可以拿结果+1
        }
        if (i - 1 >= 0 && matrix[i - 1][j] > matrix[i][j]) {
            hasPath = Math.max(hasPath, hasPath(matrix, i - 1, j, visited) + 1);
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] > matrix[i][j]) {
            hasPath = Math.max(hasPath, hasPath(matrix, i, j + 1, visited) + 1);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > matrix[i][j]) {
            hasPath = Math.max(hasPath, hasPath(matrix, i, j - 1, visited) + 1);
        }

        visited[i][j] = hasPath;//当前计算过的结果存下来
        return hasPath;
    }
}
```

### 解法：动态规划+拓扑排序
1. memo[i][j] 表示i行j列的值在路径中最长的递增序列长度。
2. 关系式：
````
memo[i][j]=max{memo[x][y]}+1
其中 (x,y) 与 (i,j) 在矩阵中相邻，并且 matrix[x][y]>matrix[i][j]
````
3. 边界：至少一个


>找到出度为0的是数也就是局部最大值，往次大值递推。能够循环最多的次数也就是路径最长的递增序列。

````java
import java.util.*;

class Solution {
    // 定义四个方向的偏移量，分别表示上、下、左、右
    public int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int longestIncreasingPath(int[][] matrix) {
        // 检查矩阵是否为空
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;// 矩阵的行数
        int columns = matrix[0].length;// 矩阵的列数

        // 存储每个位置的出度，出度表示可以向比它大的元素移动的方向的数量
        int[][] outdegrees = new int[rows][columns];
        // 计算每个位置的出度
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                for (int[] dir : dirs) {
                    int newRow = i + dir[0], newColumn = j + dir[1];
                    // 判断相邻位置是否在矩阵范围内且比当前位置的值大
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] > matrix[i][j]) {
                        ++outdegrees[i][j];
                    }
                }
            }
        }
        // 存储出度为 0 的位置
        Queue<int[]> queue = new LinkedList<int[]>();
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (outdegrees[i][j] == 0) {//出度为0，表示局部最大值，也就是当前值在四个相邻方向上是最大的
                    queue.offer(new int[]{i, j});
                }
            }
        }
        int ans = 0;
        // 拓扑排序
        while (!queue.isEmpty()) {//广度优先搜索(BFS)
            ++ans;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] cell = queue.poll();
                int row = cell[0], column = cell[1];
                // 检查相邻位置是否在矩阵范围内且比当前位置的值小
                for (int[] dir : dirs) {
                    int newRow = row + dir[0], newColumn = column + dir[1];
                    if (newRow >= 0 && newRow < rows && newColumn >= 0 && newColumn < columns && matrix[newRow][newColumn] < matrix[row][column]) {
                        --outdegrees[newRow][newColumn];//如果当前比相邻的大，就减一
                        // 如果出度减为 0，则将其加入队列
                        if (outdegrees[newRow][newColumn] == 0) {//找到次大值，依次从最大值往次大值递推
                            queue.offer(new int[]{newRow, newColumn});
                        }
                    }
                }
            }
        }
        return ans;
    }
}
````
