package org.trello.trello.pages.team;

import org.trello.core.ui.pages.forms.FormFieldsEnum;
import static org.trello.core.ui.pages.forms.FormFieldsEnum.DESCRIPTION;
import static org.trello.core.ui.pages.forms.FormFieldsEnum.NAME;
import static org.trello.core.ui.pages.forms.FormFieldsEnum.TYPE;
import org.trello.core.ui.pages.forms.FormPage;
import org.trello.core.ui.pages.forms.IFillerField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public final class TeamForm extends FormPage<TeamPage> {

    private static final String TEAM_NAME_INPUT = "input[data-test-id=\"header-create-team-name-input\"]";
    private static final String TEAM_TYPE_DISPLAY = "#teamTypeSelect > div > div";
    private static final String TEAM_TYPE_LIST = "div[data-test-id*=\"header-create-team-type-input-%s\"]";
    private static final String CREATE_TEAM_BUTTON = "button[data-test-id=\"header-create-team-submit-button\"]";
    private static final String TEAM_DESCRIPTION_INPUT = "textarea[id*=\"create-team-org-description\"]";

    @FindBy(css = TEAM_NAME_INPUT)
    private WebElement teamName;

    @FindBy(css = CREATE_TEAM_BUTTON)
    private WebElement createButton;

    @FindBy(css = TEAM_TYPE_DISPLAY)
    private WebElement teamTypeDisplay;

    @FindBy(css = TEAM_DESCRIPTION_INPUT)
    private WebElement teamDescription;

    public TeamForm(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return createButton.isDisplayed();
    }

    @Override
    protected Map<FormFieldsEnum, IFillerField> getFields() {
        Map<FormFieldsEnum, IFillerField> data = new HashMap<>();
        data.put(NAME, this::setName);
        data.put(TYPE, this::setType);
        data.put(DESCRIPTION, this::setDescription);
        return data;
    }

    public TeamForm setName(final String name) {
        action.setInputField(teamName, name);
        return this;
    }

    public TeamForm setType(final String type) {
        action.click(teamTypeDisplay);
        WebElement typeOption = driver.findElement(By.cssSelector(String.format(TEAM_TYPE_LIST, type.toLowerCase())));
        action.click(typeOption);
        return this;
    }

    public TeamForm setDescription(final String description) {
        action.setInputField(teamDescription, description);
        return this;
    }

    @Override
    public TeamPage submit() {
        action.click(createButton);
        return new TeamPage(driver);
    }
}
