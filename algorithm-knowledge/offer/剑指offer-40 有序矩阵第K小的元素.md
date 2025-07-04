## [有序矩阵中第 K 小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-sorted-matrix/description/)

给你一个 n x n 矩阵 matrix ，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
请注意，它是 排序后 的第 k 小元素，而不是第 k 个 不同 的元素。

你必须找到一个内存复杂度优于 O(n2) 的解决方案。

````
示例 1：
输入：matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
输出：13
解释：矩阵中的元素为 [1,5,9,10,11,12,13,13,15]，第 8 小元素是 13
示例 2：
输入：matrix = [[-5]], k = 1
输出：-5
````

提示：
- n == matrix.length
- n == matrix[i].length
- 1 <= n <= 300
- -109 <= matrix[i][j] <= 109
- 题目数据 保证 matrix 中的所有行和列都按 非递减顺序 排列
- 1 <= k <= n2


进阶：
- 你能否用一个恒定的内存(即 O(1) 内存复杂度)来解决这个问题?
- 你能在 O(n) 的时间复杂度下解决这个问题吗?这个方法对于面试来说可能太超前了，但是你会发现阅读这篇文章（ this paper ）很有趣。

### 解法：归并排序+大根堆
这个矩阵的每一行均为一个有序数组。问题即转化为从这 n 个有序数组中找第 k 大的数，可以想到利用归并排序的做法，归并到第 k 个数即可停止。
一般归并排序是两个数组归并，而本题是 n 个数组归并，所以需要用小根堆维护，以优化时间复杂度。
````java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }
}
````

### （记住）解法：二分查找
检查min=left+(right-left)>>1位置的数肯定左斜角肯定都是小于matrix[mid]
- 时间复杂度：O(nlog(r−l))，二分查找进行次数为 O(log(r−l))，每次操作时间复杂度为 O(n)。
- 空间复杂度：O(1)。
````java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);//中点
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;//两边挪动
            }
        }
        return left;
    }

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;//从左下角开始查找
        int j = 0;
        int num = 0;//统计范围内满足的num
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num = num + i + 1;//往右代表前面的I行都满足
                j++;//往右
            } else {
                i--;//往上
            }
        }
        return num >= k;
    }
}
````
