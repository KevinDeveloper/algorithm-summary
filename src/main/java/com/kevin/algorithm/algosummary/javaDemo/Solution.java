package com.kevin.algorithm.algosummary.javaDemo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Solution
 * @Description:
 * @Author: Kevin
 * @Date: 2018/11/21 13:46
 */
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums.length < 1) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                break;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        private void print() {
            ListNode listNode = this;
            while (listNode != null) {
                System.out.print(listNode.val + "->");
                listNode = listNode.next;
            }
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode resultNode = new ListNode(0);
        ListNode node = resultNode;
        int nextVal = 0;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                nextVal += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                nextVal += l2.val;
                l2 = l2.next;
            }
            int curNodeVal = nextVal % 10;
            nextVal = nextVal / 10;
            resultNode.val = curNodeVal;
            if (l1 != null || l2 != null) {
                resultNode.next = new ListNode(0);
                resultNode = resultNode.next;
            }
        }
        if (nextVal > 0) {
            resultNode.next = new ListNode(nextVal);
        }
        return node;

    }

    public void testListNode() {
        /**
         *
         * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
         * Output: 7 -> 0 -> 8
         * Explanation: 342 + 465 = 807.
         */
        ListNode n1 = new ListNode(3);
        ListNode n2 = new ListNode(4);
        n2.next = n1;
        ListNode n3 = new ListNode(2);
        n3.next = n2;

        ListNode h0 = new ListNode(1);
        ListNode h1 = new ListNode(4);
        //h1.next = h0;
        ListNode h2 = new ListNode(6);
        h2.next = h1;
        ListNode h3 = new ListNode(5);
        h3.next = h2;

        ListNode result = addTwoNumbers(n3, h3);

        result.print();

    }

    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int length = s.length();
        int i = 0, j = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        while (j < length) {
            if (hashMap.containsKey(s.charAt(j))) {
                i = Math.max(hashMap.get(s.charAt(j)) + 1, i);
            }
            maxLength = Math.max(maxLength, j - i + 1);
            hashMap.put(s.charAt(j), j);
            j++;
        }
        return maxLength;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {2, 7, 11, 15};
        int target = 123;
        int[] result = solution.twoSum(numbers, target);
        System.out.println("result = " + Arrays.toString(result));
        solution.testListNode();
        System.out.println("result = " + solution.lengthOfLongestSubstring("abcabcbb"));


    }
}
