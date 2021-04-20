package org.trello.trello.pages;

import org.trello.trello.context.EndPointsEnum;
import org.trello.trello.pages.board.BoardPage;
import org.trello.trello.pages.team.TeamPage;

import static org.trello.trello.driver.DriverFactory.getDriver;

public final class IdentifiableFactory {

    private IdentifiableFactory() {

    }

    public static IIdentifiable getIdentifiable(final EndPointsEnum element) {
        switch (element) {
            case BOARD:
                return new BoardPage(getDriver());
            case TEAM:
                return new TeamPage(getDriver());
            default:
                throw new IllegalArgumentException();
        }
    }
}
