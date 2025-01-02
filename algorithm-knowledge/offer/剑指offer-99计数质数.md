## [计数质数](https://leetcode.cn/problems/count-primes/description/)

给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。


````
示例 1：

输入：n = 10
输出：4
解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
示例 2：

输入：n = 0
输出：0
示例 3：

输入：n = 1
输出：0
````

提示：

- 0 <= n <= 5 * 106

### 解法：遍历
双层遍历时，比如n=x*x 那其实只需要遍历到x即可。就是根号n
````java
class Solution {
    public int countPrimes(int n) {
        if(n < 2){
            return 0;
        }
        int res = 0;
        for(int i=2;i<n;i++){//质数从2开始
            boolean flag = true;
            for(int j=2;j*j<=i;j++){
                if(i%j == 0){
                    flag = false;//有能整除的就不行
                    break;
                }
            }
            if(flag){
                res++;
            }
        }

        return res;
    }
}
````

### 解法：数学-埃氏筛
如果x为质数，那x的倍数，如果2x,3x,4x肯定就是。那可以将后续先标记。这样可以跳过很多数
````java
class Solution {
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);

        for (int i = 2; i * i < n; i++) {//只需要循环根号n即可
            if (prime[i]) {
                for (int j = i * i; j < n; j += i) {//倍数跳
                    prime[j] = false;
                }
            }
        }

        int res = 0;
        for (int i = 2; i < n; i++) {
            res += prime[i] ? 1 : 0;
        }
        return res;
    }
}
````

### 解法：数学-线性筛

````java
import java.util.ArrayList;

class Solution {
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                if (i * primes.get(j) <= 0) {
                    break;
                }
                prime[i * primes.get(j)] = false;
            }
        }
        return primes.size();
    }
}
````