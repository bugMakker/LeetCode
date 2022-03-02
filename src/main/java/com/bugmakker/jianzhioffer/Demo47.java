package com.bugmakker.jianzhioffer;

/**
 *  在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 *  你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 *  给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * @author BugMakker
 * @date 2022/3/2 14:15
 *
 */
public class Demo47 {
    public static void main(String[] args) {
        int[][] array = {{1,2,5}, {3,2,1}};
        System.out.println(maxValue(array));
        System.out.println("============================");
        System.out.println(maxValuePlus(array));
        System.out.println("============================");
        System.out.println(maxValuePlusPlus(array));
    }

    public static int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 由题可知状态转移方程为 ↓
                // f(x,y) = max(f(x, y - 1), f(x - 1, y)) + data
                // 由于数组减一会存在越界问题所以使用三目表达式来计算
                // 即f(x,y) = max(f(x, y == 0 ? 0 : y - 1), f(x == 0 ? 0 : x - 1, y)) + data
                dp[i][j] = Math.max(dp[i][j == 0 ? 0 : j - 1], dp[i == 0 ? 0 : i - 1][j]) + grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    public static int maxValuePlus(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] array = new int[n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // 因为整体路线是单行的，所以可以使用一维滚动数组进行状态压缩，减少空间复杂度
                // 即f(y) = max(y, y == 0 ? 0 : y - 1) + data
                array[j] = Math.max(array[j], array[j == 0 ? 0 : j - 1]) + grid[i][j];
            }
        }
        return array[n - 1];
    }

    public static int maxValuePlusPlus(int[][] grid) {
        // 在原数组中修改，在每个位置放到当前位置的最大价值
        // 左上角由于是开始节点所以不进行运算
        // 假设当前二维数组只有一行，此时获取该行最后一位即最大值
        // 假设当前二维数组只有一列，此时获取该列最后一位即最大值
        // 假设当前二维数组为m*n列，此时根据其上左节点的最大值与当前位置的值组成当前为值的最大值
        // 直到走到m*n的右下角，此时获取从左上角到右下角所能获取的最大价值
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }
}
