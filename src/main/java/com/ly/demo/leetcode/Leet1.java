package com.ly.demo.leetcode;

import com.ly.demo.exception.SHException;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author liuyang
 * @Date 2023/5/25 14:18
 **/
public class Leet1 {

    public static void main(String[] args) {
        System.out.println(Leet1.twoSum(new int[]{2, 7, 11, 15}, 9));
    }



    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> integerMap = new HashMap<>(nums.length - 1);
        integerMap.put(nums[0],0);
        for (int i = 1; i < nums.length; i++) {
            int num = target - nums[i];
            if (integerMap.containsKey(num)) {
                return new int[]{integerMap.get(num),i};
            }
            integerMap.put(nums[i],i );
        }
        return null;
    }

}