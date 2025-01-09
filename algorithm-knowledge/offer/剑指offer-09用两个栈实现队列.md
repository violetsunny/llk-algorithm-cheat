## [09.1 用两个栈实现队列](https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)

### 题目描述

请用栈实现一个队列，支持如下四种操作：

- push(x) – 将元素 x 插到队尾。
- pop(x) – 将队首的元素弹出，并返回该元素。
- peek() – 返回队首元素。
- empty() – 返回队列是否为空。

**注意：**

- 你只能使用栈的标准操作：`push to top`，`peek/pop from top`, `size` 和 `is empty`；
- 如果你选择的编程语言没有栈的标准库，你可以使用 list 或者 deque 等模拟栈的操作；
- 输入数据保证合法，例如，在队列为空时，不会进行 `pop` 或者 `peek` 等操作；

**样例**

```java
MyQueue queue = new MyQueue();

queue.push(1);
queue.push(2);
queue.peek();  // returns 1
queue.pop();   // returns 1
queue.empty(); // returns false
```

### 解法

`push` 操作，每次都存入 `s1`；
`pop` 操作，每次从 `s2` 取：

- `s2` 栈不为空时，不能将 `s1` 元素倒入；
- `s2` 栈为空时，需要一次将 `s1` 元素全部倒入。

```java
class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        peek();//先交换保证stack2有数
        return stack2.pop();
    }

    public int peek() {
        if(stack2.isEmpty()){//空的才需要将stack1放入，否则stack2还是之前的队列顺序不能放入
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
```
