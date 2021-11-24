package Z_LeeCode.Q2_两数相加;
/*
给你两个*非空*的链表，表示两个 非负 的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
示例 2：

输入：l1 = [0], l2 = [0]
输出：[0]
示例 3：

输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]

提示：

每个链表中的节点数在范围 [1, 100] 内
0 <= Node.val <= 9
题目数据保证列表表示的数字不含前导零
 */

import java.util.Random;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Q2_Solution1 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //head指定复制list的头部索引（即list）
        ListNode head = null;
        //tail用于在list进行遍历
        ListNode current = null;
        //carry为两数相加的进位
        int carry = 0;

        //当两个链表有一方不为空时或者进位不为0时
        while (l1 != null | l2 != null | carry != 0) {
            //对为空的链表进行补零0操作
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;

            //第一次对结果链表进行赋值
            if (head == null) {
                head = current = new ListNode(sum % 10);
            } else {
                //使用current遍历链表
                current.next = new ListNode(sum % 10);
                current = current.next;
            }

            //计算进位
            carry = sum / 10;

            //对l1和l2进行链接
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

        }
        return head;
    }


    public static void main(String[] args) {
        //当引用变量是null值时，没有为其分配位置空间，因此ListNode current = listNode只是代表均为null，没有共同的指向（因为是null值)
        ListNode  listNode1 = null;
        ListNode  listNode2 = null;
        ListNode current1 = null;
        ListNode current2 = null;
        int flag1 = 1;
        int flag2 = 1;
        Random random1 = new Random(1);
        for (int i =0; i < 3; i++) {
            int n1 = random1.nextInt(8) + 1;
            if (flag1 > 0) {
                //当为其分配空间时，可以按照下面进行赋值，他俩指向相同
                current1 = listNode1 = new ListNode(n1);
                /*
                current1 = current1.next;
                这个语句会造成空指针异常，因为current1.next为null值，复制之后current1为null，和listNode1指向不一致。
                 */
                flag1 = 0;
                System.out.print(n1);
                continue;
            }
            current1.next = new ListNode(n1);
            current1 = current1.next;
            System.out.print(n1);
        }
        System.out.print("\n*****");

        Random random2 = new Random(2);
        for (int i =0; i < 5; i++) {
            int n2 = random2.nextInt(8) + 1;
            if (flag2 > 0) {
                System.out.println("Flag2");
                current2 = listNode2 = new ListNode(n2);
                flag2 = 0;
                System.out.print(n2);
                continue;
            }
            current2.next = new ListNode(n2);
            current2 = current2.next;
            System.out.print(n2);
        }
        System.out.print("\n*****");
//        while (listNode1 != null) {
//            System.out.print(listNode1.val);
//            listNode1 = listNode1.next;
//        }
//        System.out.println("\n*****");
//        while (listNode2 != null) {
//            System.out.print(listNode2.val);
//            listNode2 = listNode2.next;
//        }
//        System.out.println("\n*****");
        ListNode listNode3 = Q2_Solution1.addTwoNumbers(listNode1, listNode2);
        System.out.println(listNode3);
        while (listNode3 != null) {
            System.out.print(listNode3.val);
            listNode3 = listNode3.next;
        }
    }
}

