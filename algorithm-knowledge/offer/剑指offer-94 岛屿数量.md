## [岛屿数量](https://leetcode.cn/problems/number-of-islands/description/)

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。


````
示例 1：

输入：grid = [
["1","1","1","1","0"],
["1","1","0","1","0"],
["1","1","0","0","0"],
["0","0","0","0","0"]
]
输出：1
示例 2：

输入：grid = [
["1","1","0","0","0"],
["1","1","0","0","0"],
["0","0","1","0","0"],
["0","0","0","1","1"]
]
输出：3
````

提示：

- m == grid.length
- n == grid[i].length
- 1 <= m, n <= 300
- grid[i][j] 的值为 '0' 或 '1'

### 解法：深度优先搜索(DFS)
通过深度搜索将符合条件的岛屿都涂一遍，最后外围循环总共进几次dfs就是几个岛屿
````java
class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);//将岛涂成2
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '1') {
            grid[row][col] = '2';

            dfs(grid, row - 1, col);
            dfs(grid, row + 1, col);
            dfs(grid, row, col - 1);
            dfs(grid, row, col + 1);
        }
    }
}
````

### 解法：广度优先搜索(BFS)
和DFS一样的道理，只是遍历方式不一样
````java
import java.util.LinkedList;

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);//将岛涂成2
                }
            }
        }

        return count;
    }

    private void bfs(char[][] grid, int row, int col) {
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == '1') {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{row, col});
            grid[row][col] = '2';

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int curRow = cur[0];
                int curCol = cur[1];

                if (curRow - 1 >= 0 && grid[curRow - 1][curCol] == '1') {
                    queue.offer(new int[]{curRow - 1, curCol});
                    grid[curRow - 1][curCol] = '2';//必须放的时候就刷值，不然等到获取再改的话，其他的点可能先拿到造成重复甚至循环
                }
                if (curCol - 1 >= 0 && grid[curRow][curCol - 1] == '1') {
                    queue.offer(new int[]{curRow, curCol - 1});
                    grid[curRow][curCol - 1] = '2';
                }
                if (curRow + 1 < grid.length && grid[curRow + 1][curCol] == '1') {
                    queue.offer(new int[]{curRow + 1, curCol});
                    grid[curRow + 1][curCol] = '2';
                }
                if (curCol + 1 < grid[0].length && grid[curRow][curCol + 1] == '1') {
                    queue.offer(new int[]{curRow, curCol + 1});
                    grid[curRow][curCol + 1] = '2';
                }
            }
        }
    }
}
````

### （记住）解法：并查集
通过并查集的union来合并树，最后多少树就是多少岛屿
````java
class Solution {

    class UnionFind {
        // 存储并查集
        private int[] elements;
        // 存储树的高度
        private int[] heights;

        UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            elements = new int[m * n];
            heights = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        // 初始都为-1
                        elements[i * n + j] = -1;
                        // 初始高度1
                        heights[i * n + j] = 1;
                    }
                }
            }
        }

        private int find(int x) {
            while (elements[x] != -1) {
                x = elements[x];
            }
            return x;
        }

        // 把两个数的根连起来，按秩合并优化
        public void union(int x, int y) {
            // x的根
            int rootx = find(x);
            // y的根
            int rooty = find(y);
            // 如果不是同一个根就连起来
            if (rootx != rooty) {
                // 矮树向高树合并
                if (heights[rootx] > heights[rooty]) {
                    elements[rooty] = rootx;
                } else if (heights[rootx] < heights[rooty]) {
                    elements[rootx] = rooty;
                } else {
                    // 如果高度相同，随便合并
                    elements[rootx] = rooty;
                    // 但是记得合并后高度加一
                    heights[rooty]++;
                }

            }
        }

        // 计算形成了多少颗树
        public int getCount() {
            int count = 0;
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] == -1) {//-1就是每个树的根节点
                    count++;
                }
            }
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * n + c, (r - 1) * n + c);
                    }
                    if (r + 1 < m && grid[r + 1][c] == '1') {
                        uf.union(r * n + c, (r + 1) * n + c);
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * n + c, r * n + c - 1);
                    }
                    if (c + 1 < n && grid[r][c + 1] == '1') {
                        uf.union(r * n + c, r * n + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }
}
````