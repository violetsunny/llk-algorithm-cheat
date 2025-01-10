# [字符串的排列](https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/)

## 题目描述



<p>输入一个字符串，打印出该字符串中字符的所有排列。</p>

<p>&nbsp;</p>

<p>你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。</p>

<p>&nbsp;</p>

<p><strong>示例:</strong></p>

<pre><strong>输入：</strong>s = &quot;abc&quot;
<strong>输出：[</strong>&quot;abc&quot;,&quot;acb&quot;,&quot;bac&quot;,&quot;bca&quot;,&quot;cab&quot;,&quot;cba&quot;<strong>]</strong>
</pre>

<p>&nbsp;</p>

<p><strong>限制：</strong></p>

<p><code>1 &lt;= s 的长度 &lt;= 8</code></p>



## 解法



### 方法一：回溯 + 哈希表

我们设计一个函数 dfs(i)，表示当前排列到了第 i 个位置，我们需要在第 i 个位置上填入一个字符，这个字符可以从 s[i..n-1] 中任意选择。

函数 dfs(i) 的执行过程如下：

-   如果 i = n-1，说明当前排列已经填满，将当前排列加入答案，返回。
-   否则，我们需要在 s[i..n-1] 中选择一个字符填入第 i 个位置，我们可以使用哈希表记录哪些字符已经被填过，从而避免重复填入相同的字符。
-   在 s[i..n-1] 中选择一个字符填入第 i 个位置，然后递归执行函数 dfs(i+1)，即填入第 i+1 个位置。
-   回溯，撤销选择，即将第 i 个位置的字符填回原位。

我们在主函数中调用函数 dfs(0)，即从第 0 个位置开始填入字符。最后返回答案数组即可。

时间复杂度 O(n! * n)，空间复杂度 O(n)。其中 n 是字符串 s 的长度。需要进行 n! 次排列，每次排列需要 O(n) 的时间复制字符串。


```java
class Solution {
    private List<String> ans = new ArrayList<>();
    private char[] cs;

    public String[] permutation(String s) {
        cs = s.toCharArray();
        dfs(0);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(int i) {
        if (i == cs.length - 1) {
            ans.add(String.valueOf(cs));
            return;
        }
        Set<Character> vis = new HashSet<>();
        for (int j = i; j < cs.length; ++j) {
            if (vis.add(cs[j])) {
                swap(i, j);
                dfs(i + 1);
                swap(i, j);
            }
        }
    }

    private void swap(int i, int j) {
        char t = cs[i];
        cs[i] = cs[j];
        cs[j] = t;
    }
}
```
剪枝方法：
````
   aab
 a   a   b
a b a b a a
b a b a a a

可以看到如果相同的在同层，是不需要再访问的。也就是剪枝操作。
````

```java
import java.util.*;

class Solution {
    List<String> ans;
    List<Character> path;
    boolean[] visited;

    public String[] permutation(String s) {
        this.ans = new ArrayList<>();
        this.path = new ArrayList<>();
        this.visited = new boolean[s.length()];
        char[] ch = s.toCharArray();
        Arrays.sort(ch);//排序了才知道相邻是否相等
        dfs(ch,0);
        String[] ss = new String[ans.size()];
        for(int i=0;i<ans.size();i++){
            ss[i] = ans.get(i);
        }
        return ss;
    }   

    private void dfs(char[] arr, int k) {
        if (arr.length == k) {
            ans.add(listToString(path));
            return;
        }
        //进行N叉树搜索
        for (int i = 0;i<arr.length;i++){
            // 剪枝，是否相邻字符，并且前一个字符没有访问过，是同一层就不要再递归访问。
            if (i > 0 && arr[i] == arr[i - 1] && visited[i - 1] = false) {
                continue;
            }
            if (visited == false) {//没有访问才行
                visited = true;
                path.add(arr[i]);
                dfs(arr, k + 1);
                //回溯
                path.remove(path.size() - 1);
                visited = false;
            }
        }
    }

    private String listToString(List<Character> path) {
        StringBuilder sb = new StringBuilder();
        for (Character p : path) {
            sb.append(p);
        }
        return sb.toString();
    }
}
```