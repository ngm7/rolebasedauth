package rolebasedauth.roles;

import rolebasedauth.Action;

import java.util.Arrays;
import java.util.HashSet;

public class AdminRole extends Role {
    public AdminRole() {
        this.actions = new HashSet<Action>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE));
    }
}