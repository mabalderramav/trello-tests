package org.trello.trello.stepdefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.trello.trello.driver.SharedDriver;
import org.trello.trello.pages.home.BoardsPage;
import org.trello.trello.pages.home.sections.Section;
import org.trello.trello.pages.home.sections.TeamSection;

import static org.trello.trello.driver.DriverFactory.getDriver;
import static org.testng.Assert.assertTrue;

public class BoardsStepDef {

    private Section section;
    private final BoardsPage boardsPage;

    public BoardsStepDef(final SharedDriver sharedDriver) {
        this.boardsPage = new BoardsPage(getDriver());
    }

    /**
     * Navigates in the body of the home boards page and goes to the given section.
     *
     * @param name section name.
     */
    @When("I navigate to {string} section")
    public void navigateToSection(final String name) {
        section = boardsPage.getSection(name);
    }

    /**
     * Selects a board tile from a section.
     *
     * @param name board name.
     */
    @When("I select {string} board")
    public void selectBoardFromSection(final String name) {
        section.getBoard(name);
    }

    /**
     * Opens the boards page for the selected team.
     */
    @When("I open the boards of the team page")
    public void openBoardsTeamPage() {
        TeamSection teamSection = (TeamSection) section;
        teamSection.openTeamBoards();
    }

    /**
     * Verifies a team is not present.
     */
    @Then("I should not find the team")
    public void iShouldNotFindTheTeam() {
        TeamSection teamSection = (TeamSection) section;
        assertTrue(teamSection.teamNotExists());
    }
}
