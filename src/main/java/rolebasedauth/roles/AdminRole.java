package rolebasedauth.roles;

import rolebasedauth.Action;

import java.util.Arrays;
import java.util.HashSet;

/*
A role pre-created to signify users that can perform all CRUD actions.
 */
public class AdminRole extends Role {
    public AdminRole() {
        this.actions = new HashSet<>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE));
    }
}