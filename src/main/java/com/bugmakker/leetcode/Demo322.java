package com.bugmakker.leetcode;

/**
 * @author BugMakker
 * @date 2022/3/3 9:14
 */
public class Demo322 {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 8};
        int amount = 4;
        System.out.println(coinChange(coins, amount));
        System.out.println(coinChangePlus(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int currentAmount = i;
            int temp = 0;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > currentAmount) {
                    continue;
                }
                if (currentAmount == coins[j]) {
                    dp[i] = 1;
                    break;
                } else {
                    int index = currentAmount - coins[(j - 1) <= 0 ? 0 : j - 1];
                    int preDp = index < 0 ? -1 : dp[index];
                    int currentDp = dp[currentAmount - coins[j]];
                    if (preDp < 0 && currentDp < 0) {
                        continue;
                    } else if (preDp < 0) {
                        dp[i] = currentDp + 1;
                    } else if (currentDp < 0) {
                        dp[i] = preDp + 1;
                    } else {
                        dp[i] = Math.min(preDp, currentDp) + 1;
                    }
                    if (temp == 0) {
                        temp = dp[i];
                    }
                    dp[i] = Math.min(temp, dp[i]);
                    temp = dp[i];
                }
            }
            if (dp[i] <= 0) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }

    public static int coinChangePlus(int[] coins, int amount) {
        // 创建存储0 - amount的最少硬币数量
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int currentAmount = i;
            // 当前金额所需要的最少硬币数量
            int cureentCoinsCount = 0;

            for (int j = 0; j < coins.length; j++) {

                // 当前硬币面值大于当前金额
                if (coins[j] > currentAmount) {
                    continue;
                }
                // 当前硬币面值等于当前金额
                if (currentAmount == coins[j]) {
                    dp[i] = 1;
                    break;
                } else {
                    // 当前硬币面值小于当前金额
                    // 需要计算f(n) = f(amount - coins[j])
                    // 判断f(n)的金额是否可以兑换
                    if (dp[currentAmount - coins[j]] < 0) {
                        continue;
                    }
                    // 当前硬币加f(n)的硬币数量
                    dp[i] = dp[currentAmount - coins[j]] + 1;

                    // 判断当前硬币统计是否被初始化
                    if (cureentCoinsCount == 0) {
                        cureentCoinsCount = dp[i];
                    }
                    // 比较其他面值的硬币数量于当前硬币数量的最小值
                    cureentCoinsCount = Math.min(cureentCoinsCount, dp[i]);
                    // 将最小的面值赋值给pd数组
                    dp[i] = cureentCoinsCount;
                }
            }
            if (dp[i] <= 0) {
                dp[i] = -1;
            }
        }
        return dp[amount];
    }

}
