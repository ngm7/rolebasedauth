package rolebasedauth.roles;

import rolebasedauth.Action;
import java.util.HashSet;

/*
A generic role that doesn't have a pre-created set of actions.
 */
public class GenericRole extends Role {
    public GenericRole(HashSet<Action> actions) {
        this.actions = actions;
    }
}
