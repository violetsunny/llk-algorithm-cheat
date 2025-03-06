## [62. 圆圈中最后剩下的数字](https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)
同：[1823. 找出游戏的获胜者](https://leetcode.cn/problems/find-the-winner-of-the-circular-game/description/)

### 题目描述

`0, 1, …, n-1` 这 `n `个数字 `(n>0)` 排成一个圆圈，从数字 `0` 开始每次从这个圆圈里删除第 `m` 个数字。

求出这个圆圈里剩下的最后一个数字。

**样例**

```
输入：n=5 , m=3

输出：3
```

### 解法一：环形链表（循环数组）

利用循环数组存放每个数字，每走一步，判断对应位置的数是否是 `-1`，`-1` 的话不计步数，这样一直走 `m` 步。将该位数字置为 `-1`。

当共有 `n-1` 个数被置为 `-1` 时，输出唯一的不为 `-1` 的那个数。

说明：

- 构建循环链表也可以，每走 `m` 步，把所在节点删掉。最后剩下一个节点时返回。
- 这种解法每删除一个数字需要 `m` 步计算，共有 `n` 个数字，因此总的时间复杂度为 `O(mn)`，空间复杂度为 `O(n)`。

```java
class Solution {

    /**
     * 求圆圈最后一个数字
     *
     * @param n n个数 [0..n-1]
     * @param m 每次删除第 m 个数
     * @return 最后一个数字
     */
    public int lastRemaining(int n, int m) {
        int cnt = 0;
        int s = -1;
        int[] nums = new int[n];
        for (int i = 1; i < n; ++i) {
            nums[i] = i;
        }

        int e = -1;
        while (cnt < n - 1) {
            int i = 0;
            while (i < m) {
                e = (e + 1) % n;
                if (nums[e] != -1) {
                    ++i;
                }
            }

            ++cnt;
            nums[e] = -1;// 用-1标注删除
            s = e;
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != -1) {
                return nums[i];
            }
        }
        return 0;
    }
}
```

### 解法二：数学（约瑟夫环问题）(推荐)

我们这样分析：

第一次被删除的圆圈的编号是 `m`。那么剩下的数字依次是：

```
1   2   3  ...  m-2   m-1  m+1 ...  n
```

由于下一次（共有 `n-1` 个数）是从 m+1 开始，因此我们对 m+1 的编号改为 1，依次改：

```
old ->  new

m+1 ->  1
m+2 ->  2
.
.
.
n-1 ->  n-m-1
n   ->  n-m
1   ->  n-m+1    
.
.
.
m-2 ->  n-2
m-1 ->  n-1
m   ->  n    ---m是删除的数
```

我们假设子问题 `x'` 是最终解，那么对应到原问题 `x` 应该是什么呢？

```
new ->  old

1   ->  m
2   ->  m+1
3   ->  m+2
.
.
.
n-1-m ->  n
n-m   ->  1
n-m+1 ->  2
.
.
.
n-1   ->  m-2
n     ->  m-1
 
x'  ->  x
```

```
x = (x' + m) % n
```

所以就有一个递推式：

```
f(i) = (f(i - 1) + m) % i;
```

算法的时间复杂度为 `O(n)`，空间复杂度为 `O(1)`。

```java
class Solution {

    /**
     * 求圆圈最后一个数字
     *
     * @param n n个数 [0..n-1]
     * @param m 每次删除第 m 个数
     * @return 最后一个数字
     */
    public int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        int res = 0;
        for (int i = 2; i <= n; ++i) {
            res = (res + m) % i;
        }
        return res;
    }
}
```

#### 帅地写法
```java
class Solution {

    /**
     * 求圆圈最后一个数字
     *
     * @param n n个数 [0..n-1]
     * @param m 每次删除第 m 个数
     * @return 最后一个数字
     */
    public int lastRemaining(int n, int m) {
        if (n == 0) {
            return 0;
        }
        //递归从上往下 f(i) = (f(i - 1) + m) % i;
        return (lastRemaining(n-1,m) + m) % n;
    }
}
```

### 解法三：模拟 + 队列

````java
class Solution {
    public int findTheWinner(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);// 先放入
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {//跳到第k个删掉
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }
}

````
