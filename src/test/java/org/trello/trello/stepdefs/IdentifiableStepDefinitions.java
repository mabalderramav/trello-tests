package org.trello.trello.stepdefs;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.When;
import org.trello.trello.context.ContextTrello;
import org.trello.trello.context.EndPointsEnum;
import org.trello.trello.pages.IIdentifiable;
import org.trello.trello.pages.IdentifiableFactory;
import org.trello.trello.utils.CommonValidations;

public final class IdentifiableStepDefinitions {

    private IIdentifiable identifiable;
    private ContextTrello context;

    public IdentifiableStepDefinitions(final ContextTrello context) {
        this.context = context;
    }

    @ParameterType("board|team")
    public EndPointsEnum element(final String element) {
        EndPointsEnum keyword = CommonValidations.verifyEndPointEnum(element);
        identifiable = IdentifiableFactory.getIdentifiable(keyword);
        return keyword;
    }

    /**
     * Saves the id of the element created.
     *
     * @param element value used to save the Id.
     */
    @When("I save the identifier of the {element} created")
    public void savesIDsInContext(final EndPointsEnum element) {
        context.getUser().saveIds(element, identifiable.getIdentifier());
    }
}
