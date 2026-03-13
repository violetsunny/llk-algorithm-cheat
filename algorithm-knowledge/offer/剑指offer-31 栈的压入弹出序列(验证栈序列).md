## [31. 栈的压入、弹出序列](https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)
同[946.验证栈序列](https://leetcode.cn/problems/validate-stack-sequences/description/)

### 题目描述

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。

假设压入栈的所有数字均不相等。

例如序列 `1,2,3,4,5` 是某栈的压入顺序，序列 `4,5,3,2,1` 是该压栈序列对应的一个弹出序列，但 `4,3,5,1,2` 就不可能是该压栈序列的弹出序列。

注意：若两个序列为空或长度不等则视为并不是一个栈的压入、弹出序列。

**样例**

```
输入：[1,2,3,4,5]
      [4,5,3,2,1]

输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1

```

### （记住）更好的写法：模拟
如果压入后的数和需要弹出的数相等，就说明可以弹出；

如果不相等就压栈，然后再继续比较是否相等；

最后stk为空，就是pushed压入后最后popped都能弹出，就是正确顺序。
```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new LinkedList<>();
        int j = 0;
        for (int v : pushed) {
            //压入栈中，按照相等就弹出，不相等就压栈，这就是弹出序列
            stk.push(v);
            while (!stk.isEmpty() && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return stk.isEmpty();
    }
}
````