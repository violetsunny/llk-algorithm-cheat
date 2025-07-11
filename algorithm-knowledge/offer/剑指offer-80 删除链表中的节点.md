## [删除链表中的节点](https://leetcode.cn/problems/delete-node-in-a-linked-list/description/)
有一个单链表的 head，我们想删除它其中的一个节点 node。

给你一个需要删除的节点 node 。你将 无法访问 第一个节点  head。

链表的所有值都是 唯一的，并且保证给定的节点 node 不是链表中的最后一个节点。

删除给定的节点。注意，删除节点并不是指从内存中删除它。这里的意思是：

给定节点的值不应该存在于链表中。

链表中的节点数应该减少 1。

node 前面的所有值顺序相同。

node 后面的所有值顺序相同。


````
示例 1：
输入：head = [4,5,1,9], node = 5
输出：[4,1,9]
解释：指定链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9
示例 2：
输入：head = [4,5,1,9], node = 1
输出：[4,5,9]
解释：指定链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9
````

提示：
- 链表中节点的数目范围是 [2, 1000]
- -1000 <= Node.val <= 1000
- 链表中每个节点的值都是 唯一 的
- 需要删除的节点 node 是 链表中的节点 ，且 不是末尾节点

### （记住）解法：复制 + 替换
想删除当前，但是不知道前面的没办法重新指向。那我们就把后面那个顶替当前的，删后面那个。
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
    public void deleteNode(ListNode node) {
        //想删除当前，但是不知道前面的没办法重新指向。
        //那我们就把后面那个顶替当前的，删后面那个。
        node.val = node.next.val;
        node.next = node.next.next;//这个 node.next.next很重要，node = node.next是不行的，节点没有少，需要下下一个换掉下一个
    }
}
````