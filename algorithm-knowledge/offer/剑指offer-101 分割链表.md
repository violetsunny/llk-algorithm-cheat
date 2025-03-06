## [分隔链表](https://leetcode.cn/problems/partition-list/description/)
给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

你应当 保留 两个分区中每个节点的初始相对位置。

````
1->4->3->2->5->2  3
1-2->2->4->3->5
````

````
示例 1：


输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]
示例 2：

输入：head = [2,1], x = 2
输出：[1,2]
````

提示：

- 链表中节点的数目在范围 [0, 200] 内
- -100 <= Node.val <= 100
- -200 <= x <= 200

### 解法：遍历
````java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode pre = new ListNode(-201);
        ListNode temp = pre;

        ListNode pre2 = new ListNode(-201);
        ListNode temp2 = pre2;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                ListNode node = cur;
                temp.next = node;
                temp = temp.next;
            } else {
                ListNode node = cur;
                temp2.next = node;
                temp2 = temp2.next;
            }
            cur = cur.next;
        }

        temp2.next = null;//这个很重要，需要将cur的next给null,不然程序会认为链表没有完成
        temp.next = pre2.next;//pre的尾加上pre2的头
        return pre.next;
    }
}
````