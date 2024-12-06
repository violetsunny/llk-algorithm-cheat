# 回文链表
给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。

 
````
示例 1：
输入：head = [1,2,2,1]
输出：true
示例 2：
输入：head = [1,2]
输出：false
 
````
提示：
- 链表中节点数目在范围[1, 105] 内
- 0 <= Node.val <= 9
 

进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

## 解法：取出+双指针

## 解法：递归
先把头给一个全局变量。然后进入递归到最后，然后往后弹和全局变量进行比较。也就前面和后面比较，然后往中间移动。

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
    ListNode org;
    public boolean isPalindrome(ListNode head) {
        //先把头给一个全局变量。然后进入递归到最后，然后往后弹和全局变量进行比较。也就前面和后面比较，然后往中间移动。
        org = head;
        return resver(head);
    }

    public boolean resver(ListNode head){
        if(head==null){
            return true;
        }
        if(!resver(head.next)){//递归到最后一个节点
            return false;
        }
        if(head.val!=org.val){//头节点和最后节点比较
            return false;
        }
        org = org.next;//头往后，递归会弹出往前
        return true;
    }
}
````
