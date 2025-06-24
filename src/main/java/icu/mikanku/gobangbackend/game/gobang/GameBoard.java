package icu.mikanku.gobangbackend.game.gobang;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GameBoard {
    private final static int BOARD_SIZE = 15;
    private final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    // 坐标系往右下延伸 x 为列 y 为行
    private final int[][] CHECK_SIDE = {
            // 横向 [-2,0] [-1,0] [0,0] [1,0] [2,0]
            { 1, 0 },
            // 纵向 [0,-2] [0,-1] [0,0] [0,1] [0,2]
            { 0, 1 },
            // 正斜 [-2,-2] [-1,-1] [0,0] [1,1] [2,2]
            { 1, 1 },
            // 反斜 [-2,2] [-1,1] [0,0] [1,-1] [2,-2]
            { 1, -1 },

    };


    /**
     * 放置棋子
     * @param x x 坐标(列)
     * @param y y 坐标(行)
     * @param player 玩家 1 2
     * @return 是否可以下子
     */
    public boolean place(int x, int y, int player) {
        // 1. 检查坐标合理性
        if (x < 0 || y < 0 || x >= BOARD_SIZE || y >= BOARD_SIZE) {
            return false;
        }

        // 2. 设置棋子
        if (board[y][x] == 0) {
            board[y][x] = player;
            return true;
        }
        return false;
    }

    /**
     * 如果数量大于等于5就胜利
     * @param x 列
     * @param y 行
     * @param player 玩家 不能为 0
     * @return 是否胜利
     */
    public boolean checkWin(int x, int y, int player) {
        int count = 1;
        for (int[] side : CHECK_SIDE) {
            int dx = side[0];
            int dy = side[1];
            count += countDirection(x, y, player, dx, dy);
            count += countDirection(x, y, player, -dx, -dy);
        }
        return count >= 5;
    }

    /**
     * 计算方向上有几颗棋子
     * @param x 列
     * @param y 行
     * @param player 玩家
     * @param dx x 方向
     * @param dy y 方向
     * @return 棋子的数量
     */
    public int countDirection(int x, int y, int player, int dx, int dy) {
        int count = 0;
        for (int i = 1; i < 5; i++) {
            // 列
            int nx = x + dx * i;
            // 行
            int ny = y + dy * i;
            // 如果超过边界停止循环
            if (nx < 0 || ny < 0 || nx > BOARD_SIZE || ny > BOARD_SIZE) break;
            if (board[ny][nx] == player) {
                count ++;
            } else {
                break;
            }
        }
        return count;
    }

    public void print() {
        for (int[] row : board) {
            for (int column : row) {
                System.out.print(" " + column);
            }
            System.out.println();
        }
    }
}
