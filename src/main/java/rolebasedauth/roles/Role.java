package rolebasedauth.roles;

import java.util.HashSet;
import rolebasedauth.Action;

// use builder pattern
public abstract class Role {
    HashSet<Action> actions;

    Role() {
        this.actions = new HashSet<Action>();
    }

    public void assignAction(Action action) {
        try {
            actions.add(action);
        } catch (Exception ex) {
            // log.error(cannot add action)
        }
    }

    public void removeAction(Action action) {
        try {
            actions.remove(action);
        } catch (Exception ex) {
            // log.error(cannot remove action)
        }
    }
    public Boolean canPerformAction(Action action) {
        return actions.contains(action);
    }
}


