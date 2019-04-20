package rolebasedauth;

import java.util.HashSet;
import java.util.UUID;

public class Resource {
    String name;
    String uid;
    HashSet<Action> allowedActions;

    Resource(String name) {
        this.name = name;
        this.uid = UUID.randomUUID().toString();
        allowedActions = new HashSet<>();
    }

    public HashSet<Action> getAllowedActions() {
        return allowedActions;
    }

    public void assignActions(HashSet<Action> actions) {
        for (Action action : actions) {
            assignAction(action);
        }
    }

    public void assignAction(Action action) {
        try {
            allowedActions.add(action);
        } catch (Exception ex) {
            // log.error(Cannot add action for Resource
        }
    }

    public Boolean isActionValid(Action action) {
        return allowedActions.contains(action);
    }
}
