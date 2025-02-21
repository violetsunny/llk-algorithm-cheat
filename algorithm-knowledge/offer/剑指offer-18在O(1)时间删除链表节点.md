## [18.1 在 O(1)时间删除链表节点](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/)

### 题目描述

给定单向链表的一个节点指针，定义一个函数在 `O(1)` 时间删除该节点。

假设链表一定存在，并且该节点一定不是尾节点。

**样例**

```
输入：链表 1->4->6->8
      删掉节点：第2个节点即6（头节点为第0个节点）

输出：新链表 1->4->8
```

### 解法

判断要删除的节点是否是尾节点：

- 若是，那么需要遍历链表，找到节点的前一个节点，让前一个节点指向 `null`，时间复杂度为 `O(n)`；
- 若不是，把下一个节点的值赋给该节点，该节点指向下下个节点，时间复杂度为 `O(1)`。

题目中说明了节点不是尾节点，那么符合第二种情况。

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

    /**
     * 删除链表的节点
     *
     * @param val 要删除的节点
     */
    public void deleteNode(ListNode head,int val) {
        if(head == null){
            return null;
        }
        if(head.val==val){
            return head.next;
        }
        ListNode temp = head.next;
        ListNode pre = head;
        while(temp!=null){
            if(temp.val == val){
                pre.next = temp.next;
                return head;
            }
            temp = temp.next;
            pre = pre.next;
        }
        
        return head;
    }
}
```

### 解法: 替换

这个是只给要删除节点
````java
class Solution {
    /**
     * 删除链表的节点
     *
     * @param node 要删除的节点
     */
    public void deleteNode(ListNode node) {
        //想删除当前，但是不知道前面的没办法重新指向。
        //那我们就把后面那个顶替当前的，删后面那个。
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
````
