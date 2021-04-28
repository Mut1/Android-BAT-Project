package com.leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 
// 👍 3198 👎 0

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.lang.reflect.Array;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P15ThreeSum {
    public static void main(String[] args) {
        Solution solution = new P15ThreeSum().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length < 3) return ans;
            Arrays.sort(nums);//排序
            for (int i = 0; i < nums.length - 2; i++) {
                if (nums[i] > 0) break;
                if (i > 0 && nums[i - 1] == nums[i]) continue;
                int target=0-nums[i];
                int l=i+1;
                int k=nums.length-1;
                while (l<k){
                    if (target==nums[l]+nums[k]){
                        ans.add(new ArrayList<>(Arrays.asList(nums[i],nums[l],nums[k])));
                        l++;
                        k--;
                        while (l<k&&nums[l-1]==nums[l]) l++;
                        while (l<k&&nums[k+1]==nums[k]) k--;
                    }

                    else if(nums[l]+nums[k]<target){
                        l++;
                    }
                    else if (nums[l]+nums[k]>target){
                        k--;
                    }
                }

            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}