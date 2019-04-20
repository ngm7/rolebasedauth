package rolebasedauth.roles;

import rolebasedauth.Action;

import java.util.HashSet;

public class GenericRole extends Role {
    public GenericRole(HashSet<Action> actions) {
        this.actions = actions;
    }
}
