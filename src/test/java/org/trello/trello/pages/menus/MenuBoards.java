package org.trello.trello.pages.menus;

import org.trello.core.Environment;
import org.trello.trello.pages.board.BoardClosed;
import org.trello.trello.pages.board.BoardForm;
import org.trello.trello.pages.board.BoardPage;
import org.trello.trello.pages.team.TeamPage;
import org.trello.core.ui.pages.WebObject;
import org.trello.core.ui.pages.forms.FormPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class MenuBoards extends WebObject {

    private static final String FIND_BOARDS = "input[data-test-id='header-boards-menu-search']";
    private static final String CREATE_BOARD = "button[data-test-id='header-boards-menu-create-board']";
    private static final String CLOSED_BOARDS = "button[data-test-id='header-boards-menu-open-closed']";
    private static final String PERSONAL_BOARD = "//span[@name='board']/parent::span/parent::div/"
            + "following-sibling::div//div[contains(text(), '%s')]";
    private static final String TEAM_BOARDS = "//span[text()='%s']/ancestor::a/parent::div/"
            + "following-sibling::div//div[contains(text(), '%s')]";
    private static final String TEAM_INFO = "//span[contains(text(), '%s')]/ancestor::div/a";

    @FindBy(css = FIND_BOARDS)
    private WebElement findBoards;

    @FindBy(css = CREATE_BOARD)
    private WebElement createBoardButton;

    @FindBy(css = CLOSED_BOARDS)
    private WebElement closedBoardsButton;

    public MenuBoards(final WebDriver driver) {
        super(driver);
    }

    /**
     * Indicates if the actual page is displayed.
     *
     * @return true if actual page is displayed, else false.
     */
    @Override
    public boolean isDisplayed() {
        return findBoards.isDisplayed();
    }

    public BoardPage openPersonalBoard(final String boardName) {
        Environment env = Environment.getInstance();
        String personalBoard = String.format(PERSONAL_BOARD, boardName);
        WebElement boardElement = driver.findElement(By.xpath(personalBoard));
        action.waitForPageLoadComplete(env.getReducedTime());
        action.click(boardElement);
        return new BoardPage(driver);
    }

    public BoardPage openTeamBoard(final String teamName, final String boardName) {
        String teamBoard = String.format(TEAM_BOARDS, teamName, boardName);
        WebElement boardElement = driver.findElement(By.xpath(teamBoard));
        boardElement.click();
        return new BoardPage(driver);
    }

    public FormPage<?> createBoard() {
        action.click(createBoardButton);
        return new BoardForm(driver);
    }

    public BoardClosed goToClosedBoards() {
        action.click(closedBoardsButton);
        return new BoardClosed(driver);
    }

    public TeamPage goToTeamPage(final String teamName) {
        String teamNameLoc = String.format(TEAM_INFO, teamName);
        WebElement teamElement = driver.findElement(By.xpath(teamNameLoc));
        action.click(teamElement);
        return new TeamPage(driver);
    }
}
