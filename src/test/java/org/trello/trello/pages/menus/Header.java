package org.trello.trello.pages.menus;

import org.trello.core.ui.pages.WebObject;
import org.trello.core.ui.pages.forms.FormPage;
import org.trello.trello.pages.board.BoardForm;
import org.trello.trello.pages.board.BoardPage;
import org.trello.trello.pages.team.TeamForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class Header extends WebObject {

    private static final String CREATE_MENU_BUTTON = "button[data-test-id=\"header-create-menu-button\"]";
    private static final String CREATE_BOARD_BUTTON = "button[data-test-id=\"header-create-board-button\"]";
    private static final String CREATE_TEAM_BUTTON = "button[data-test-id=\"header-create-team-button\"]";
    private static final String BOARDS_MENU_BUTTON = "button[data-test-id=\"header-boards-menu-button\"]";
    private static final String PROFILE_BUTTON = "button[data-test-id= 'header-member-menu-button']";
    private static final String NOTIFICATIONS_BUTTON = "button[data-test-id='header-notifications-button']";
    private static final String NOTIFICATION_PATH =
            "//*[@name='%s-member']/parent::*[@role='button']/following-sibling::div//a[text()='%s']";

    @FindBy(css = NOTIFICATIONS_BUTTON)
    private WebElement notificationBtn;

    @FindBy(css = CREATE_MENU_BUTTON)
    private WebElement creationButton;

    @FindBy(css = CREATE_BOARD_BUTTON)
    private WebElement createBoardButton;

    @FindBy(css = CREATE_TEAM_BUTTON)
    private WebElement createTeamButton;

    @FindBy(css = BOARDS_MENU_BUTTON)
    private WebElement headerMenuBoards;

    @FindBy(css = PROFILE_BUTTON)
    private WebElement profileButton;

    public Header(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return creationButton.isDisplayed();
    }

    public FormPage<?> createElement(final String entity) {
        action.click(creationButton);
        switch (entity) {
            case "board":
                action.click(createBoardButton);
                return new BoardForm(driver);
            case "team":
                action.click(createTeamButton);
                return new TeamForm(driver);
            default:
                throw new IllegalArgumentException(String.format("Invalid entity: <%s>", entity));
        }
    }

    public MenuBoards getMenuBoards() {
        action.waitForPageLoadComplete();
        action.waitForVisibility(headerMenuBoards);
        action.click(headerMenuBoards);
        return new MenuBoards(driver);
    }

    public MenuProfile getMenuProfile() {
        action.waitForVisibility(profileButton);
        action.click(profileButton);
        return new MenuProfile(driver);
    }

    public Header openNotifications() {
        action.waitForVisibility(notificationBtn);
        action.click(notificationBtn);
        return this;
    }

    public BoardPage selectBoardNotification(final String type, final String boardName) {
        String notificationPath = String.format(NOTIFICATION_PATH, type, boardName);
        By notifyLocator = By.xpath(notificationPath);
        action.waitForElementLocated(notifyLocator);
        WebElement notification = driver.findElement(notifyLocator);
        action.click(notification);
        return new BoardPage(driver);
    }
}
