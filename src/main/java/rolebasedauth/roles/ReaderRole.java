package rolebasedauth.roles;

import rolebasedauth.Action;

public class ReaderRole extends Role{
    ReaderRole(String name) {
        this.name = name;
        this.actions.add(Action.READ);
    }
}
