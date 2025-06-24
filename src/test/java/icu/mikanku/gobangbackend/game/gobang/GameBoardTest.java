package icu.mikanku.gobangbackend.game.gobang;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private static final Logger log = LoggerFactory.getLogger(GameBoardTest.class);

    @Test
    void place() {
        GameBoard board = new GameBoard();
        // 检查棋盘内能否填满棋子
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(board.place(i, j, 1));
            }
        }
        log.info("棋盘内可以填满棋子");
        /** 检查玩家二不能下入玩家一的子上 **/
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertFalse(board.place(i, j, 2));
            }
        }
        log.info("玩家二不能下在玩家一的棋子上");
        /** 检查边界 */
        for (int i = 15; i < 30; i++) {
            for (int j = 15; j < 30; j++) {
                assertFalse(board.place(i, j, 2));
            }
        }
        log.info("棋盘外不能下棋");
    }

    @Test
    void checkWin() {
        GameBoard board = new GameBoard();
        // 检查横向能不能赢
        board.place(0,0, 1);
        board.place(1,0, 1);
        board.place(2,0, 1);
        board.place(3,0, 1);
        board.place(4,0, 1);
        assertTrue(board.checkWin(4, 0, 1));

        board = new GameBoard();
        board.place(0,0, 1);
        board.place(1,0, 1);
        board.place(2,0, 1);
        board.place(3,0, 1);
        assertTrue(board.checkWin(4, 0, 1));
        assertFalse(board.checkWin(0, 0, 1));

        board = new GameBoard();
        board.place(0, 0, 2);
        board.place(0, 1, 2);
        board.place(0, 3, 2);
        board.place(0, 4, 2);
        assertTrue(board.checkWin(0, 2, 2));
    }

    @Test
    void countDirection() {
        GameBoard board = new GameBoard();

        board.place(0, 1, 1);
        board.place(0, 2, 1);
        assertEquals(2, board.countDirection(0, 3, 1, 0, -1));
    }
}