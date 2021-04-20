package org.trello.trello.hooks;

import io.cucumber.java.After;
import org.trello.trello.pages.board.BoardPage;
import org.trello.trello.pages.board.MenuBoard;

import static org.trello.trello.driver.DriverFactory.getDriver;

public final class BoardHooks {

    private static final int CLEAN_CONTEXT_ORDER_BOARD_UI = 20;

    /**
     * Delete a Board if it was created by UI.
     */
    @After(value = "@deleteBoardUi", order = CLEAN_CONTEXT_ORDER_BOARD_UI)
    public void deleteBoardByUI() {
        BoardPage board = new BoardPage(getDriver());
        MenuBoard menuBoard = new MenuBoard(getDriver());
        if (!board.isDisplayed()) {
            return;
        }
        if (!menuBoard.isDisplayed()) {
            menuBoard = board.displayMenu();
        }
        menuBoard.moreMenuOptions()
                .closeBoard()
                .permanentlyDelete();
    }
}
