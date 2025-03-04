# [1 ～ n 整数中 1 出现的次数](https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/)
同：[233. 数字 1 的个数](https://leetcode.cn/problems/number-of-digit-one/description/)

## 题目描述

<p>输入一个整数 <code>n</code> ，求1～n这n个整数的十进制表示中1出现的次数。</p>

<p>例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>6</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 <= n < 2^31</code></li>
</ul>

<p>注意：本题与主站 233 题相同：<a href="https://leetcode.cn/problems/number-of-digit-one/">https://leetcode.cn/problems/number-of-digit-one/</a></p>


## 解法

### 方法一：动态规划（数位DP）
- 比如510223中1的个数，就是500000中1的个数+10000中1的个数+200中1的个数+20中1的个数+3中1的个数。
  500000就是第6位0-5，后面5位都是0-9。
- 依次拆解</br>
  比如pos等于(0,0,false)时，会有10个dsf(-1，0，false)=0+1+0+0+0+0+0+0+0+0;</br>
  &emsp;&emsp; pos等于(0,1,false)时，会有10个dsf(-1，1，false)=1+2+1+1+1+1+1+1+1+1; </br>
  从第一位1的个数依次加上。
- 算500000中其实会把10000，200等都会算一遍，就可以保存起来避免重复计算。


时间复杂度: $O(\log n)$。


```java
class Solution {
    private int[] a = new int[12];//最多12位，每位的数
    private int[][] dp = new int[12][12];

    public int countDigitOne(int n) {
        int i = 0;//多少位
        for (; n > 0; n /= 10) {//初始化每位的数
            a[i++] = n % 10;
        }
        return dfs(i, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {//是否收到n限制，比如123，如果120了后面只能到121，122，123。否则像10可以到99
        if (pos < 0) {
            return cnt;
        }
        if (!limit && dp[pos][cnt] != 0) {
            return dp[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        dp[pos][cnt] = ans;
        return ans;
    }
}
```


### 方法二：数学公式
````
bit 是当前位；
当前位的数：cur = (n / bit) % 10 ;
低位数（当前位到个位）： low = n % bit;
高位数（前面位到当前位）： high = n / bit / 10; 

501223中1.
如果bit是百位上:
cur=(501223/100)%10=2;
low=501223%100=223;
high=501223/100/10=501;

5 0 1 2 2 3
     cur
0-501 * 0-99

如果bit是千位上，cur=1
5 0 1 2 2 3
   cur  
0-49 * 0-999
50 * 0-223

如果bit是千位上，cur=0
5 0 1 2 2 3
 cur    
0-4 * 0-9999

````

<P>公式：</P>
$cur = (n / bit) \% 10 $, $low = n \% bit$, $high = n / bit / 10$
- cur > 1  => $(high + 1) * bit$
- cur == 1 => $(high * bit)+(1 + low)$
- cur == 0 => $high * bit$

````java
class Solution {

    public int countDigitOne(int n) {
        long bit = 1;
        long sum = 0;
        while (bit <= n) {
            long cur = (n / bit) % 10;//当前位
            long low = n % bit;//低位
            long high = (n / bit) / 10;//高位

            if (cur > 1) {
                sum += (high + 1) * bit;
            } else if (cur == 1) {
                sum += (high * bit) + (1 + low);
            } else {
                sum += high * bit;
            }
            bit = bit * 10;
        }
        return (int) sum;
    }
}
````

