package com.xiaomi.browser.security.api;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author liufangtao
 * @date 2023-12-20 14:21
 */
public class Alg3ForRemoveArrayRepeatValue {


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int len = removeDuplicates3(nums);
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + ",");
        }

    }

    public static int removeDuplicates(int[] nums) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        Iterator<Integer> iterrator = set.iterator();
        int len = 0;
        while (iterrator.hasNext()) {
            nums[len] = iterrator.next();
            len++;
        }
        return len;
    }

    public static int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 1;
        int fast = 1;
        int len = nums.length;
        while (fast < len) {
            if (nums[fast] == nums[fast - 1]) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return slow;

    }


    /**
     * 可以支持重复2次
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates3(int[] nums) {

        if (nums == null) {
            return 0;
        }
        if (nums.length < 3) {
            return nums.length;
        }
        int len = nums.length;
        int slow = 2;
        int fast = 2;
        while (fast < len) {
            if (nums[fast] == nums[slow - 2]) {
                fast++;
            } else {
                nums[slow] = nums[fast];
                fast++;
                slow++;
            }

        }
        return slow;

//        Map<Integer, Integer> map = new LinkedHashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            Integer count = map.get(nums[i]);
//            if (count == null) {
//                count = 0;
//            }
//            count = count+1;
//            map.put(nums[i], count);
//        }
//        int len = 0;
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            int count = entry.getValue();
//            if (count > 2) {
//                count = 2;
//            }
//            for (int i = 0; i < count; i++) {
//                nums[len] = entry.getKey();
//                len++;
//            }
//        }
//        return len;

    }


}
