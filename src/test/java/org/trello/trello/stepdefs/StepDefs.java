package org.trello.trello.stepdefs;

import io.cucumber.java.en.Given;
import org.trello.trello.context.ContextTrello;
import org.trello.trello.context.UserTrello;
import org.trello.trello.driver.SharedDriver;
import org.trello.trello.pages.PageObject;
import org.trello.trello.pages.login.LoginPage;

import static org.trello.trello.driver.DriverFactory.getDriver;

public final class StepDefs {

    private ContextTrello context;
    private LoginPage loginPage;

    public StepDefs(final SharedDriver sharedDriver, final ContextTrello context) {
        this.context = context;
        loginPage = new LoginPage(getDriver());
    }

    /**
     * Logins in trello page.
     *
     * @param userAccount keyword to get an user.
     */
    @Given("I log in with my Trello account as {string}")
    public void iLogInWithTrelloAccountAs(final String userAccount) {
        UserTrello user = new UserTrello(userAccount);
        PageObject actualPage = loginPage.setCredentials(user.getEmail(), user.getPassword()).submit();
        context.saveActualPage(actualPage);
        context.saveUser(user);
    }
}
