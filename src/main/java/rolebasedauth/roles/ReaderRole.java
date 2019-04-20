package rolebasedauth.roles;

import rolebasedauth.Action;

public class ReaderRole extends Role{
    public ReaderRole() {
        this.actions.add(Action.READ);
    }
}
