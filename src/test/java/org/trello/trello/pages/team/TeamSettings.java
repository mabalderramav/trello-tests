package org.trello.trello.pages.team;

import org.trello.trello.pages.PageObject;
import org.trello.trello.pages.home.BoardsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class TeamSettings extends PageObject {

    private static final String DELETE_TEAM_BUTTON = "a.quiet-button";
    private static final String CONFIRM_DELETE_BUTTON = "input[class=\"js-confirm full negate\"]";

    @FindBy(css = DELETE_TEAM_BUTTON)
    private WebElement deleteTeamButton;

    @FindBy(css = CONFIRM_DELETE_BUTTON)
    private WebElement confirmDeleteButton;

    public TeamSettings(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return deleteTeamButton.isDisplayed();
    }

    /**
     * Deletes a team permanently.
     *
     */
    public void deleteTeam() {
        action.click(deleteTeamButton);
        action.click(confirmDeleteButton);
        new BoardsPage(driver);
    }
}
