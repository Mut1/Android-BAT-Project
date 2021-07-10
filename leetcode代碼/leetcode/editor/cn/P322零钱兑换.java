package com.leetcode.editor.cn;
//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：coins = [1], amount = 1
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：coins = [1], amount = 2
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 231 - 1 
// 0 <= amount <= 104 
// 
// Related Topics 广度优先搜索 数组 动态规划 
// 👍 1345 👎 0

import java.util.Arrays;

public class P322CoinChange {
    public static void main(String[] args) {
        Solution solution = new P322CoinChange().new Solution();
        // TO TEST
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //备忘录
       // int[] memo;

        public int coinChange(int[] coins, int amount) {

//            memo = new int[amount + 1];
//            Arrays.fill(memo, -666);
//            return dp(coins, amount);

        }

//        private int dp(int[] coins, int amount) {
//            if (amount == 0) return 0;
//            if (amount < 0) return -1;
//            //查看备忘录 防止重复计算
//            if (memo[amount] != -666)
//                return memo[amount];
//            int res = Integer.MAX_VALUE;
//            for (int coin : coins) {
//                //计算子问题结果
//                int subProblem = dp(coins, amount - coin);
//                //子问题无解，则跳过
//                if (subProblem == -1) continue;
//                //在子问题中选择最优解，然后加一
//                res = Math.min(res, subProblem + 1);
//            }
//            //把计算结果存入备忘录
//            memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
//            return memo[amount];
//        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}