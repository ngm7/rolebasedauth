package rolebasedauth.roles;

import rolebasedauth.Action;

import java.util.Arrays;
import java.util.HashSet;

public class AdminRole extends Role {
    AdminRole(String name) {
        this.name = name;
        this.actions = new HashSet<Action>(Arrays.asList(Action.CREATE, Action.READ, Action.UPDATE, Action.DELETE));
    }
}