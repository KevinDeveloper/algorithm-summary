package com.xiaomi.browser.security.api;

/**
 * @author liufangtao
 * @date 2023-12-20 14:21
 */
public class Alg2ForRemoveArrayTargetValue {


    public static void main(String[] args) {
        int[] nums = {4, 5};
        int len = removeElement(nums, 4);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }

    }

    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (right >= 0 && nums[right] == val) {
                if (right == 0) {
                    break;
                }
                right--;
            }
            while (left <= nums.length - 1 && nums[left] != val) {
                if (left == nums.length - 1) {
                    break;
                }
                left++;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right--;

            }
            System.out.println("=right=" + right + ",left=" + left);

        }
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                len++;
            } else {
                break;
            }

        }
        return len;


    }


}
