## [排序链表](https://leetcode.cn/problems/sort-list/description/)

给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
````
示例 1：
输入：head = [4,2,1,3]
输出：[1,2,3,4]
示例 2：
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
示例 3：
输入：head = []
输出：[]
````

提示：
- 链表中节点的数目在范围 [0, 5 * 104] 内
- -105 <= Node.val <= 105


进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？

### （记住）解法：双指针+归并排序
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
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {//到尾tail了要切开，不然会重复
            head.next = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail) {//快慢指针找到中间点slow
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode left = sortList(head, mid);
        ListNode right = sortList(mid, tail);
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {
        ListNode tempHead = new ListNode(0);
        ListNode temp = tempHead;
        ListNode temp1 = left;
        ListNode temp2 = right;
        while (temp1 != null && temp2 != null) {//比较，小的先放
            if (temp1.val > temp2.val) {
                temp.next = temp2;
                temp2 = temp2.next;
            } else {
                temp.next = temp1;
                temp1 = temp1.next;
            }
            temp = temp.next;
        }
        while (temp1 != null) {//剩下的也需要再放入
            temp.next = temp1;
            temp1 = temp1.next;
            temp = temp.next;
        }
        while (temp2 != null) {
            temp.next = temp2;
            temp2 = temp2.next;
            temp = temp.next;
        }
        return tempHead.next;
    }
}
````