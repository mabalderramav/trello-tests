package org.trello.trello.pages.board;

import org.trello.trello.pages.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public final class ArchivedItemsBoard extends PageObject {

    private static final String LIST_TEXT_PARAMETERS = "list";
    private static final String ARCHIVE_TITTLE = "h3.board-menu-header-title";
    private static final String SWITCH_BUTTON = "a.archive-controls-switch";
    private static final String ARCHIVED_LIST = "div.item-name";

    @FindBy(css = ARCHIVE_TITTLE)
    private WebElement tittle;

    @FindBy(css = SWITCH_BUTTON)
    private WebElement switchButton;

    @FindBy(css = ARCHIVED_LIST)
    private List<WebElement> archivedLists;

    public ArchivedItemsBoard(final WebDriver driver) {
        super(driver);
    }

    /**
     * Indicates if the actual page is displayed.
     *
     * @return true if actual page is displayed, else false.
     */
    @Override
    public boolean isDisplayed() {
        action.waitForVisibility(tittle);
        return tittle.isDisplayed();
    }

    public ArchivedItemsBoard switchItems() {
        action.waitForVisibility(switchButton);
        if (switchButton.getText().contains(LIST_TEXT_PARAMETERS)) {
            action.click(switchButton);
        }
        return this;
    }

    public List<String> archivedItemsList() {
        action.waitForVisibilityOfAllElements(archivedLists);
        return archivedLists.stream().map(WebElement::getText).collect(Collectors.toList());
    }

}
