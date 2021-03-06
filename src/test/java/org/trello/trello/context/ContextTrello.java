package org.trello.trello.context;

import org.trello.core.context.Context;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class ContextTrello extends Context {

    private Map<String, UserTrello> userMap;
    private String userActive;

    public ContextTrello() {
        super();
        this.userMap = new HashMap<>();
    }

    public void saveUser(final String userKeyword) {
        saveUser(new UserTrello(userKeyword));
    }

    public void saveUser(final UserTrello user) {
        userActive = user.getKeyword();
        if (userMap.containsKey(user.getKeyword())) {
            return;
        }
        userMap.put(user.getKeyword(), user);
    }

    public Collection<UserTrello> getUsers() {
        return userMap.values();
    }

    public UserTrello getUser() {
        return getUser(userActive);
    }

    public UserTrello getUser(final String userKey) {
        return userMap.get(userKey);
    }
}
