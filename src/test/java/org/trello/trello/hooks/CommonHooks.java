package org.trello.trello.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import org.trello.core.api.RequestManager;
import org.trello.trello.context.ContextTrello;
import org.trello.trello.context.EndPointsEnum;
import org.trello.trello.driver.SharedDriver;
import org.trello.trello.pages.menus.Header;
import org.trello.trello.utils.AssertGroup;
import org.testng.asserts.SoftAssert;

import static org.trello.trello.driver.DriverFactory.getDriver;

public final class CommonHooks {

    private static final int CLEAN_CONTEXT_ORDER = 20;
    private static final int LOGOUT_ORDER = 15;
    private static final int SOFT_ASSERT_ORDER = 25;
    private final ContextTrello context;
    private final RequestManager requestManager;
    private Header header;
    private AssertGroup assertGroup;

    public CommonHooks(final SharedDriver sharedDriver, final ContextTrello context,
                       final RequestManager requestManager, final AssertGroup assertGroup) {
        this.context = context;
        this.requestManager = requestManager;
        this.header = new Header(getDriver());
        this.assertGroup = assertGroup;
    }

    /**
     * Deletes Items if they were created by UI or API.
     */
    @After(value = "@cleanData", order = CLEAN_CONTEXT_ORDER)
    public void cleanTestsData() {
        context.getUsers().forEach(user -> {
            requestManager.setApiCredentials(user.getKeyword());
            for (EndPointsEnum item : EndPointsEnum.values()) {
                user.getIdsByKey(item).forEach(id -> requestManager
                        .init(context)
                        .delete(item.getEndPoint().concat(id)));
            }
        });
    }

    @After(order = LOGOUT_ORDER)
    public void logoutAccount() {
        header.getMenuProfile().logOutSession();
    }

    /**
     * Initialize a SoftAssert instead of HardAssert for a test.
     */
    @Before(value = "@softAssert")
    public void initializeSoftAssert() {
        assertGroup.setAssertGroup(new SoftAssert());
    }

    /**
     * Assert all assertions stored from a test.
     */
    @After(value = "@softAssert", order = SOFT_ASSERT_ORDER)
    public void assertAll() {
        assertGroup.assertAll();
    }
}
