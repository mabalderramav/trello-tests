package org.trello.trello.pages.home;

import org.trello.trello.pages.PageObject;
import org.trello.trello.pages.menus.SideBar;
import org.openqa.selenium.WebDriver;

/**
 * Home page base.
 */
public abstract class HomePage extends PageObject {
    public HomePage(final WebDriver driver) {
        super(driver);
    }

    /**
     * Gets Trello side bar component.
     *
     * @return Trello side bar.
     */
    public SideBar getSideBar() {
        return new SideBar(driver);
    }
}
