package org.trello.trello.pages.team;

import org.trello.trello.pages.IIdentifiable;
import org.trello.trello.pages.PageObject;
import org.trello.trello.pages.home.BoardsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.trello.trello.driver.DriverFactory.getDriver;

public final class TeamPage extends PageObject implements IIdentifiable {

    private static final String TEAM_NAME = "//div[@class='js-current-details']/descendant::h1";
    private static final String TEAM_SETTINGS = "a[data-tab=\"settings\"]";
    private static final String XPATH_BOARD_TILE = "//div[contains(@title, '%s')]//ancestor::a[@class='board-tile']";
    private static final int ID_INDEX = 0;
    private static final String URL_REGEX = "/[\\w]+";
    private static final String EDIT_TEAM_PROFILE_BUTTON = "//span[@name='edit']/ancestor::button";
    private static final String TEAM_DESCRIPTION = "//div[@class='js-current-details']/descendant::p";

    @FindBy(xpath = TEAM_NAME)
    private WebElement teamName;

    @FindBy(css = TEAM_SETTINGS)
    private WebElement teamSettings;

    @FindBy(xpath = EDIT_TEAM_PROFILE_BUTTON)
    private WebElement editTeamProfileButton;

    @FindBy(xpath = TEAM_DESCRIPTION)
    private WebElement teamDescription;

    public TeamPage(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return teamName.isDisplayed() && teamSettings.isDisplayed();
    }

    @Override
    public String handleUrl() throws URISyntaxException {
        wait.until(ExpectedConditions.urlMatches(URL_REGEX));
        String currentUri = new URI(driver.getCurrentUrl()).getPath();
        return Paths.get(currentUri).getName(ID_INDEX).toString();
    }

    public String getTeamName() {
        action.waitForVisibility(teamName);
        if (isDisplayed()) {
            return action.getElementText(teamName);
        } else {
            return "Error: Team not found.";
        }
    }

    public String getTeamDescription() {
        action.waitForVisibility(teamDescription);
        if (teamDescription.isDisplayed()) {
            return action.getElementText(teamDescription);
        } else {
            return "Error: Team does not have description.";
        }
    }

    /**
     * Navigates to settings tab and opens the new page.
     *
     * @return A team settings object.
     */
    public TeamSettings goToSettings() {
        action.click(teamSettings);
        return new TeamSettings(getDriver());
    }

    public BoardsPage openBoard(final String name) {
        isDisplayed();
        By boardTile = By.xpath(String.format(XPATH_BOARD_TILE, name));
        action.waitForVisibility(driver.findElement(boardTile));
        action.click(driver.findElement(boardTile));
        return new BoardsPage(driver);
    }

    /**
     * Open the form to edit a team's profile.
     *
     * @return A team profile form object.
     */
    public TeamProfileForm editTeamProfile() {
        action.click(editTeamProfileButton);
        return new TeamProfileForm(driver);
    }
}
