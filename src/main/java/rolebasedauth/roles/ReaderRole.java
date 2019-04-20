package rolebasedauth.roles;

import rolebasedauth.Action;

/*
A role pre-created to signify users that can only perform READ operations.
 */
public class ReaderRole extends Role{
    public ReaderRole() {
        this.actions.add(Action.READ);
    }
}
