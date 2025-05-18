## [有效的回旋镖](https://leetcode.cn/problems/valid-boomerang/description/)

给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。

回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。


````
示例 1：

输入：points = [[1,1],[2,3],[3,2]]
输出：true
示例 2：

输入：points = [[1,1],[2,2],[3,3]]
输出：false
````

提示：

- points.length == 3
- points[i].length == 2
- 0 <= xi, yi <= 100

### （记住）解法：数学-向量叉积
两个向量叉积为0 ，表示共线。
````java
class Solution {
    public boolean isBoomerang(int[][] points) {
        int n = points.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] vec = createV(points[i], points[j]);

                for (int k = 0; k < n; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    int[] vec1 = createV(points[i], points[k]);

                    return cross(vec, vec1) != 0;
                }

            }
        }

        return false;
    }

    //构建向量
    private int[] createV(int[] points1, int[] points2) {
        return new int[]{points1[0] - points2[0], points1[1] - points2[1]};
    }

    //叉积
    private int cross(int[] points1, int[] points2) {
        return points1[0] * points2[1] - points2[0] * points1[1];
    }
}
````