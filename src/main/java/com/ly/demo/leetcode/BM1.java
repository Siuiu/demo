package com.ly.demo.leetcode;

/**
 * @Author liuyang
 * @Date 2023/2/1 15:42
 **/
public class BM1 {
    public ListNode ReverseList(ListNode head) {
        head.next = null;//
        return head;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}