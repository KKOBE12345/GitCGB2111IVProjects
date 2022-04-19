package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Array2Sum {
    public int[] twoSum(int[] nums,int target){
        Map<Integer,Integer> storeNums=new HashMap<>(nums.length,1);
        int[] res=new int[2];
        for(int i=0;i<nums.length;i++){
            int another=target-nums[i];
            Integer anotherIndex=storeNums.get(another);
            if(null!=anotherIndex){
                res[0]=anotherIndex;
                res[1]=i;
                break;
            }else {
                storeNums.put(nums[i],i);
            }
        }
        return res;
    }
}
