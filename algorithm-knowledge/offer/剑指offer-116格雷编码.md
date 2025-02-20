## [89. 格雷编码](https://leetcode.cn/problems/gray-code/description/)

n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
第一个整数是 0
一个整数在序列中出现 不超过一次
每对 相邻 整数的二进制表示 恰好一位不同 ，且
第一个 和 最后一个 整数的二进制表示 恰好一位不同
给你一个整数 n ，返回任一有效的 n 位格雷码序列 。


````
示例 1：

输入：n = 2
输出：[0,1,3,2]
解释：
[0,1,3,2] 的二进制表示是 [00,01,11,10] 。
- 00 和 01 有一位不同
- 01 和 11 有一位不同
- 11 和 10 有一位不同
- 10 和 00 有一位不同
  [0,2,3,1] 也是一个有效的格雷码序列，其二进制表示是 [00,10,11,01] 。
- 00 和 10 有一位不同
- 10 和 11 有一位不同
- 11 和 01 有一位不同
- 01 和 00 有一位不同
  示例 2：

输入：n = 1
输出：[0,1]
````

提示：

- 1 <= n <= 16

### 解法：位运算 + 公式
$公式：G(i) = i ⊕ (i / 2)$
````java 
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> list = new ArrayList<>();
        int m = (1 << n);
        for(int i = 0;i < m;i++){
            int res = (i >> 1) ^ i;//(i >> 1) ^ i，i>>1其实将i每一位向右移动一位，这时和i取异或，相当于和自己的后一位取余
            list.add(res);
        }

        return list;
    }
}
````

### 解法：归纳
````
n = 1  [0, 1]
n = 2  [00，01，11，10]
n = 3  [000, 001, 011, 010, 110, 111, 101, 100]

n = 2  [ 00,  01,  11,  10] 
n = 3  [000, 001, 011, 010] (前四个数)

n = 2  [ 00,  01,  11,  10] 
补   1 [100, 101, 111, 110] 
逆  序 [110, 111, 101, 100] （后四个数）

结果拼接
n = 3  [000, 001, 011, 010, 110, 111, 101, 100]
````


````java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(ret.get(j) | (1 << (i - 1)));//ret.get(j) | (1 << (i - 1)) 就是对上一个size - 1的最后一个数前一位补1，依次往前倒序放入
            }
        }
        return ret;
    }
}
````

#### 一样的写法

````java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        ret.add(0);
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--) {
                res.add(head + res.get(j));//由二进制移位改为十进制的加
            }
            head <<= 1;
        }
        return res;
    }
}
````