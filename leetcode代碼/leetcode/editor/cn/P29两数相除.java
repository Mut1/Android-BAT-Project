package com.leetcode.editor.cn;
//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 数学 二分查找 
// 👍 576 👎 0

public class P29DivideTwoIntegers{
    public static void main(String[] args) {
        Solution solution = new P29DivideTwoIntegers().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int dividend, int divisor) {
        long res=0;
        long x=dividend;
        long y=divisor;
        boolean isNeg=(x < 0 && y > 0) || (x > 0 && y < 0);
        if (x<0) x=-x;
        if (y<0) y=-y;

        //二分查找  [x/y]结果属于[0,x] 所以对x进行二分查找
        long l=0;
        long r=x;
        while (l<r){
            long mid=l + r + 1 >> 1;
            if (quickMulti(mid,y)<=x)
                l=mid;
            else{
                r=mid-1;}
        }
        res = isNeg ? -l : l;

        // 判断是否溢出
        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return (int)res;
    }
        /**
         * 快速乘法
         *
         * @param a 乘数
         * @param b 被乘数
         * @return 积
         */
    private long quickMulti(long a, long b){
        long result = 0;

        while (b > 0) {
            if ((b & 1) == 1) {
                // 当前最低位为1，结果里加上a
                result += a;
            }
            // 被乘数右移1位，相当于除以2
            b >>= 1;
            // 乘数倍增，相当于乘以2
            a += a;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}