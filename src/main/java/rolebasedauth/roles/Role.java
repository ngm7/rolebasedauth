package rolebasedauth.roles;

import java.util.HashSet;
import rolebasedauth.Action;

// use builder pattern
public abstract class Role {
    String name;
    HashSet<Action> actions;

    public void assignAction(Action action) {
        try {
            actions.add(action);
        } catch (Exception ex) {
            // log.error(cannot add action)
        }
    }

    public Boolean canPerformAction(Action action) {
        return actions.contains(action);
    }
}


