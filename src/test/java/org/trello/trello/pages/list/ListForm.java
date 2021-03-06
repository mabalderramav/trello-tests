package org.trello.trello.pages.list;

import org.trello.core.ui.pages.forms.FormFieldsEnum;
import org.trello.core.ui.pages.forms.FormPage;
import org.trello.core.ui.pages.forms.IFillerField;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

import static org.trello.core.ui.pages.forms.FormFieldsEnum.NAME;

public final class ListForm extends FormPage<ListPage> {

    private static final String LIST_NAME = "input.list-name-input";
    private static final String ADD_LIST_BUTTON = "input.primary.mod-list-add-button";

    @FindBy(css = LIST_NAME)
    private WebElement lisNameInput;

    @FindBy(css = ADD_LIST_BUTTON)
    private WebElement addListButton;

    public ListForm(final WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return true;
    }

    @Override
    protected Map<FormFieldsEnum, IFillerField> getFields() {
        Map<FormFieldsEnum, IFillerField> data = new HashMap<>();
        data.put(NAME, this::setName);
        return data;
    }

    public ListForm setName(final String listName) {
        action.setInputField(lisNameInput, listName);
        return this;
    }

    @Override
    public ListPage submit() {
        action.click(addListButton);
        return new ListPage(driver);
    }
}
