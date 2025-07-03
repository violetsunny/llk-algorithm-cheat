# [06. 从尾到头打印链表](https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/)

### 题目描述

输入一个链表的头结点，按照 从尾到头 的顺序返回节点的值。

返回的结果用数组存储。

**样例**

```
输入：[2, 3, 5]
返回：[5, 3, 2]
```

### （记住）解法：栈

遍历链表，每个链表结点值 `push` 进栈，最后将栈中元素依次 `pop` 到数组中。

*时间复杂度$O(n)$，空间复杂度$O(n)$*
```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 从尾到头打印链表
     *
     * @param head 链表头结点
     * @return 结果数组
     */
    public int[] printListReversingly(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        int cnt = 0;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
            ++cnt;
        }

        int[] res = new int[cnt];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }
        return res;
    }
}
```

### 解法：反向输出数组
```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {

    /**
     * 从尾到头打印链表
     *
     * @param head 链表头结点
     * @return 结果数组
     */
    public int[] printListReversingly(ListNode head) {
        if(head == null){
            return new int[0];
        }
        ListNode temp = head;
        int count = 0;//从0开始，因为null算了一个
        while(temp != null) {
            temp = temp.next;
            count++;
        }
        int[] res = new int[count];
        while(head != null) {
            res[--count]=head.val;//从后往前赋值，--count因为下标从0开始
            head = head.next;
        }
        return res;
    }

}
```