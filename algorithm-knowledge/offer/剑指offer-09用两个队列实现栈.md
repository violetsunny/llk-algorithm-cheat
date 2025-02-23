## [09.2 用两个队列实现栈](https://leetcode.cn/problems/implement-stack-using-queues/description/)
同：[225. 用队列实现栈](https://leetcode.cn/problems/implement-stack-using-queues/description/)

### 题目描述

使用队列实现栈的下列操作：

- push(x) -- 元素 x 入栈
- pop() -- 移除栈顶元素
- top() -- 获取栈顶元素
- empty() -- 返回栈是否为空

**注意:**

- 你只能使用队列的基本操作-- 也就是 `push to back`, `peek/pop from front`, `size`, 和 `is empty` 这些操作是合法的。
- 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
- 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。

### 解法

- 出栈时，先将队列的元素依次移入另一个队列中，直到队列剩下一个元素。将该元素出队即可。
- 进栈时，将元素压入不为空的那一个队列即可。如果两队列都为空，随便压入其中一个队列。

```java
class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        top();
        return queue1.poll();
    }

    public int top() {
        if(queue1.isEmpty()){//如果pop后queue1是空的，要把queue1重新放进去
            while(!queue2.isEmpty()){
                queue1.add(queue2.poll());
            }
        }
        while(queue1.size() > 1){//保证queue1最后一个，也就栈顶
            queue2.add(queue1.poll());
        }
        return queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
```