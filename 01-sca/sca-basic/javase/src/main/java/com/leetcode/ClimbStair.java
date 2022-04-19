package com.leetcode;

import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class ClimbStair {
    private static Map<Integer,Long> storeMap=new HashMap<>();
    public static Long climb(Integer n){
        if(n==1) return 1L;
        if(n==2) return 2L;
        if(null!=storeMap.get(n)){
            return storeMap.get(n);
        }
        else {
            Long res=(climb(n-1)+climb(n-2));
            storeMap.put(n, res);
            return res;
        }
    }

    public static int climb02(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        int res=0;
        int pre=2;
        int prePre=1;
        for(int i=3;i<=n;++i){
            res=pre+prePre;
            prePre=pre;
            pre=res;
        }
        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list01 = new ArrayList<>();
        int[] res=new int[3];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                for(int k=j+1;k<nums.length;k++){
                    List<Integer> list=new ArrayList();
                    if(nums[i]+nums[j]+nums[k]==0){
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);

                    }

                }
            }
        }
//        HashSet<Integer> set = new HashSet<>(list01.size());
//        List<List> newList = Lists.newArrayList();
//        list01.forEach(p->{
//            if(set.add(p)){
//                newList.add(p);
//            }
//        });

//        list01 = new ArrayList<>(newList);



        return list01;
    }

    public static void main(String[] args) {
//        Long total = climb(100);
//        System.out.println(total);
        int[] sum={-1,0,1,2,-1,-4};
        List<List<Integer>> lists = threeSum(sum);
        System.out.println(lists);



    }
}
