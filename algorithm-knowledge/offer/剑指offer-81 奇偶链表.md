# [奇偶链表](https://leetcode.cn/problems/odd-even-linked-list/description/)
给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。

第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。

请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。

你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题。

 
````
示例 1:
输入: head = [1,2,3,4,5]
输出: [1,3,5,2,4]
示例 2:
输入: head = [2,1,3,5,6,4,7]
输出: [2,3,6,7,1,5,4]
 
````
提示:
- n ==  链表中的节点数
- 0 <= n <= 104
- -106 <= Node.val <= 106

## （记住）解法：双指针
````java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode d = head;//奇数
        ListNode dHead = d;
        ListNode o = head.next;//偶数
        ListNode oHead = o;
        while (d != null && o != null && o.next != null) {//o.next!=null才能让d是最后一个
            d.next = o.next;
            d = o.next;
            if (d != null) {
                o.next = d.next;
                o = d.next;
            }
        }

        d.next = oHead;//奇数的最后一个重新指向偶数的头
        return dHead;
    }
}
````