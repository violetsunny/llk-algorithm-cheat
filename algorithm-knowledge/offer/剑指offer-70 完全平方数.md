## [完全平方数](https://leetcode.cn/problems/perfect-squares/description/)

给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

 
````
示例 1：

输入：n = 12
输出：3
解释：12 = 4 + 4 + 4
示例 2：

输入：n = 13
输出：2
解释：13 = 4 + 9
````
 
提示：

- 1 <= n <= 104

### （记住）解法：动态规划
1. $f(i)$ 表示最少需要多少个数的平方来表示整数 i。
2. 状态转移方程: $f(i) = 1 + MIN(i-j^2)[1, \sqrt{i}]$; 1到根号i之间 $[1, \sqrt{i}]$
3. 边界：$f(0)=0$

````java
class Solution {
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {//为什么不直接i=n开始，因为需要[1,n]去填充前面f(i)的值
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {//[1, 根号i]
                minn = Math.min(minn, f[i - j * j]);//i=n时,取[1, 根号n]之间最小的数+1
            }
            f[i] = minn + 1;//至少一个
        }
        return f[n];
    }
}
````

### 解法：数学-四平方和定理
四平方和定理证明了任意一个正整数都可以被表示为至多四个正整数的平方和。这给出了本题的答案的上界。

同时四平方和定理包含了一个更强的结论：
1. 当且仅当 $n!=4^k ×(8m+7)$ 时，n 可以被表示为至多三个正整数的平方和。
2. 因此，当 $n=4^k ×(8m+7)$ 时，n 只能被表示为四个正整数的平方和。 此时我们可以直接返回 4。

当 $n !=4^k ×(8m+7)$ 时，我们需要判断到底多少个完全平方数能够表示 n，我们知道答案只会是 1,2,3 中的一个：

- 答案为 1 时，则必有 n 为完全平方数，这很好判断；

- 答案为 2 时，则有 $n=a^2+b^2$ ，我们只需要枚举所有的 $a(1≤a≤\sqrt{n})$，判断 $n−a^2$ 是否为完全平方数即可；

- 答案为 3 时，我们很难在一个优秀的时间复杂度内解决它，但我们只需要检查答案为 1 或 2 的两种情况，即可利用排除法确定答案。

````java
class Solution {
    public int numSquares(int n) {
        if (isPerfectSquare(n)) {
            return 1;//完全平方数
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;//比较复杂的，可以通过排除其他选项
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);//x开根的y，y^2是否等于x
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x) {
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
}
````
