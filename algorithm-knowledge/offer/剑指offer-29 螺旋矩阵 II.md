## [螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/description/)

给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。


````
示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]
````

提示：

- 1 <= n <= 20


````java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int num = 1;
        while (true) {
            //左往右，l移动
            for (int i = t, j = l; j <= r; j++) {
                res[i][j] = num++;
            }
            t++;//往下一层
            if (t > b) {
                break;
            }
            //上往下，t移动
            for (int i = t, j = r; i <= b; i++) {
                res[i][j] = num++;
            }
            r--;//往左一层
            if (l > r) {
                break;
            }
            //右往左，r移动
            for (int i = b, j = r; j >= l; j--) {
                res[i][j] = num++;
            }
            b--;//往上一层
            if (t > b) {
                break;
            }
            //下往上，b移动
            for (int i = b, j = l; i >= t; i--) {
                res[i][j] = num++;
            }
            l++;//往右一层
            if (l > r) {
                break;
            }
        }
        return res;
    }
}
````