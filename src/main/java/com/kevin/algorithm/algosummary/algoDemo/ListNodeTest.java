package com.kevin.algorithm.algosummary.algoDemo;

import java.util.Objects;

/**
 * @ClassName: ListNodeTest
 * @Description:
 * @Author: Kevin
 * @Date: 2018/10/23 17:03
 */
public class ListNodeTest {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }


    }

    /**
     * 合并两个有序链表 - 递归实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = null;
        if (l1.val <= l2.val) {
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }

    /**
     * 合并两个有序链表 - 循环实现
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode head = new ListNode(0);
        while (Objects.nonNull(t1) && Objects.nonNull(t2)) {
            if (t1.val <= t2.val) {
                head.next = t1;
                head = head.next;
                t1 = t1.next;
            } else {
                head.next = t2;
                head = head.next;
                t2 = t2.next;
            }
        }
        while (Objects.nonNull(t1)) {
            head.next = t1;
            head = head.next;
            t1 = t1.next;
        }
        while (Objects.nonNull(t2)) {
            head.next = t2;
            head = head.next;
            t2 = t2.next;
        }
        return head;
    }

    /**
     * 反转链表 - 递归实现
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    /**
     * 反转链表 - 循环实现
     *
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    public void print(ListNode head) {
        ListNode h = head;
        System.out.println();
        while (h != null) {
            System.out.print(h.val + "->");
            h = h.next;
        }
        System.out.println();
    }

    public void TestPrint() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);

        ListNode t3 = new ListNode(3);
        ListNode t33 = new ListNode(3);
        ListNode t5 = new ListNode(5);
        ListNode t0 = new ListNode(0);

        l1.next = t3;
        t3.next = t33;
        t33.next = t5;
        //t5.next = t0;

        ListNode t4 = new ListNode(4);
        ListNode t6 = new ListNode(6);
        ListNode t66 = new ListNode(6);
        l2.next = t4;
        t4.next = t6;
        t6.next = t66;
        //t0.next = t66;


        print(l1);
        print(l2);
        ListNode sameNode = getFirstSameNode(l1, l2);

        ListNode l0 = mergeTwoLists(l1, l2);
        print(l0);
        l0 = reverseList2(l0);
        print(l0);

        ListNode node = new ListNode(2);
        t4 = new ListNode(4);
        t6 = new ListNode(6);
        t66 = new ListNode(6);
        node.next = t4;
        t4.next = t6;
        t6.next = t66;
        //t66.next = t4;

        boolean hasLoop = hasLoop(node);
        System.out.println("l2 hasloop=" + hasLoop);

    }

    /**
     * 判断链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasLoop(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                return false;
            }
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回环的入口节点
     * @param head
     * @return
     */
    public ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null)
            return null;
        //快指针每次走两步
        ListNode fast = head;
        //每次走一步
        ListNode slow = head;
        //因为fast每次要走两步，所有需要判断fast的下一个是否为空
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
            //判断是否相遇 相遇后让快指针从头开始走，每次都是走一步，第二次相遇的节点就是环的入口
            if (fast == slow) {
                fast = head;
                while (fast!= slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
            }
            if (fast == slow) {
                return slow;
            }
        }
        //要是没有相遇，此链表没有环返回空
        return null;

    }

    /**
     * 得到两个链表的第一个相交节点
     * 一长一短，长的先走相对步数，然后到两个一样长，再一起走，遇到的第一个相等的就是第一个相交点
     * @param l1
     * @param l2
     * @return
     */
    public ListNode getFirstSameNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode t1 = l1;
        ListNode t2 = l2;
        int len1 = 0;
        int len2 = 0;
        int diff = 0;
        while (t1 != null) {
            len1++;
            t1 = t1.next;
        }
        while (t2 != null) {
            len2++;
            t2 = t2.next;
        }
        if (len1 > len2) {
            diff = len1 - len2;
            t1 = l1;
            t2 = l2;
            while (diff > 0) {
                t1 = t1.next;
                diff--;
            }
        } else {
            diff = len2 - len1;
            t1 = l1;
            t2 = l2;
            while (diff > 0) {
                t2 = t2.next;
                diff--;
            }
        }
        while (t1 != null && t2 != null) {
            if (t1 == t2) {
                return t1;
            } else {
                t1 = t1.next;
                t2 = t2.next;
            }

        }
        return null;

    }

    public static void main(String[] args) {
        ListNodeTest nodeTest = new ListNodeTest();
        nodeTest.TestPrint();

    }


}
