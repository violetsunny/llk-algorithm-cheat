## [24. 反转链表](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/)
同:[206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/description/)
### 题目描述

输入一个链表，反转链表后，输出新链表的表头。

### 解法一: 头插法

利用头插法解决。

````java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode p = head;
        ListNode q = head.next;
        while (q != null) {
            p.next = dummy.next;
            dummy.next = p;
            p = q;
            q = p.next;
        }
        p.next = dummy.next;
        return p;
    }
}
````

### 解法二：递归

````java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);//递归到最后
        head.next.next = head;//当前下一个的下一个指向当前，比如2.next指向1
        head.next = null;//当前的下一个改为null，比如：1.next指向null
        return node;//返回最后节点,不能是head，因为此时的head指向的null
    }
}
````

### （记住）解法三：原地反转
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;//拿出当前节点
        ListNode pre = null;//要弄一个前置节点
        while(cur!=null){
            ListNode temp = cur.next;//把next节点拎出来
            cur.next = pre;//当前指向前置
            pre = cur;//当前变成下一次的前置
            cur = temp;//next变成下次的当前
        }
        return pre;//pre是最后的，cur是null
    }
}
```