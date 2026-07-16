# [Unique Paths II](https://leetcode.com/problems/unique-paths-ii/)

🟡 Medium

**Topics:** Array, Dynamic Programming, Matrix

## Problem Summary

This problem asks us to find the number of unique paths a robot can take from the top-left corner to the bottom-right corner of a grid. The robot can only move right or down. The grid may contain obstacles (marked as `1`), which the robot cannot pass through. Spaces are marked as `0`.

## Approach

This problem is a classic application of Dynamic Programming. We can define a DP state `dp[i][j]` representing the number of unique paths to reach cell `(i, j)`.

1.  **Initialization**:
    *   First, check if the starting cell `(0, 0)` itself is an obstacle. If `obstacleGrid[0][0] == 1`, then there are no paths, so we immediately return `0`.
    *   Initialize `dp[0][0] = 1` since there's one way to reach the starting cell (by being there).
2.  **Fill First Row and Column**:
    *   For the first row (`i=0`, `j > 0`): `dp[0][j] = dp[0][j-1]` if `obstacleGrid[0][j] == 0`. If an obstacle is encountered, all subsequent cells in that row are unreachable from the left, so their `dp` value will remain `0`.
    *   For the first column (`j=0`, `i > 0`): `dp[i][0] = dp[i-1][0]` if `obstacleGrid[i][0] == 0`. Similarly, an obstacle blocks paths for subsequent cells in that column.
3.  **Fill Remaining Cells**:
    *   For `i > 0` and `j > 0`:
        *   If `obstacleGrid[i][j] == 1` (an obstacle), then `dp[i][j] = 0` (no paths can go through it).
        *   Otherwise (it's a space), `dp[i][j] = dp[i-1][j] + dp[i][j-1]`. This means the number of ways to reach `(i, j)` is the sum of ways to reach it from the cell directly above (`(i-1, j)`) and the cell directly to its left (`(i, j-1)`).
4.  **Result**: The final answer will be `dp[m-1][n-1]`, representing the number of unique paths to the bottom-right corner.

## Algorithm Walkthrough

Let's use the example `obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]`.
Dimensions: `m = 3`, `n = 3`.

1.  **Check `obstacleGrid[0][0]`**: It's `0`, so we proceed.
2.  **Initialize `dp` table**: Create a `3x3` DP table, initially all zeros.
    ```
    dp = [[0, 0, 0],
          [0, 0, 0],
          [0, 0, 0]]
    ```
3.  **Set `dp[0][0]`**: `dp[0][0] = 1`.
    ```
    dp = [[1, 0, 0],
          [0, 0, 0],
          [0, 0, 0]]
    ```
4.  **Fill First Row**:
    *   `j=1`: `obstacleGrid[0][1] = 0`. `dp[0][1] = dp[0][0] = 1`.
    *   `j=2`: `obstacleGrid[0][2] = 0`. `dp[0][2] = dp[0][1] = 1`.
    ```
    dp = [[1, 1, 1],
          [0, 0, 0],
          [0, 0, 0]]
    ```
5.  **Fill First Column**:
    *   `i=1`: `obstacleGrid[1][0] = 0`. `dp[1][0] = dp[0][0] = 1`.
    *   `i=2`: `obstacleGrid[2][0] = 0`. `dp[2][0] = dp[1][0] = 1`.
    ```
    dp = [[1, 1, 1],
          [1, 0, 0],
          [1, 0, 0]]
    ```
6.  **Fill Remaining Cells (`i > 0, j > 0`)**:
    *   `i=1, j=1`: `obstacleGrid[1][1] = 1` (obstacle). So, `dp[1][1] = 0`.
        ```
        dp = [[1, 1, 1],
              [1, 0, 0],
              [1, 0, 0]]
        ```
    *   `i=1, j=2`: `obstacleGrid[1][2] = 0`. `dp[1][2] = dp[0][2] + dp[1][1] = 1 + 0 = 1`.
        ```
        dp = [[1, 1, 1],
              [1, 0, 1],
              [1, 0, 0]]
        ```
    *   `i=2, j=1`: `obstacleGrid[2][1] = 0`. `dp[2][1] = dp[1][1] + dp[2][0] = 0 + 1 = 1`.
        ```
        dp = [[1, 1, 1],
              [1, 0, 1],
              [1, 1, 0]]
        ```
    *   `i=2, j=2`: `obstacleGrid[2][2] = 0`. `dp[2][2] = dp[1][2] + dp[2][1] = 1 + 1 = 2`.
        ```
        dp = [[1, 1, 1],
              [1, 0, 1],
              [1, 1, 2]]
        ```
7.  **Result**: The value at `dp[2][2]` is `2`. This is the number of unique paths.

## Complexity Analysis

*   **Time Complexity**: `O(m * n)`
    We iterate through each cell of the `m x n` grid exactly once to fill the DP table.
*   **Space Complexity**: `O(m * n)`
    We use an auxiliary `m x n` DP table to store the number of paths to each cell. (It's possible to optimize space to `O(n)` by only keeping track of the current and previous row, or even `O(1)` if in-place modification of `obstacleGrid` is allowed).