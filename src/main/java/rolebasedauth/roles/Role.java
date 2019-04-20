package rolebasedauth.roles;

import java.util.HashSet;
import rolebasedauth.Action;

// use builder pattern
public abstract class Role {

    /*
     The set of actions this role can perform.
      */
    HashSet<Action> actions;

    /*
    Empty constructor
     */
    Role() {
        this.actions = new HashSet<>();
    }

    /*
    Constructor.
     */
    Role(HashSet<Action> actions) {
        this.actions = actions;
    }

    /*
    Get this role's list of assigned actions.
     */
    public HashSet<Action> getActions() {
        return actions;
    }

    /*
     Each role's actions can be added to suit the current flavor of administration's understanding
     For example - if the administration agrees that the ReaderRole should be allowed to also CREATE, they can mutate
     that role's set of actions.
     */
    public void assignAction(Action action) {
        try {
            actions.add(action);
        } catch (Exception ex) {
            // log.error(cannot add action)
        }
    }

    /*
     Each role's actions can be removed to suit the current flavor of administration's understanding
      */
    public void removeAction(Action action) {
        try {
            actions.remove(action);
        } catch (Exception ex) {
            // log.error(cannot remove action)
        }
    }

    /*
     Check if a role can perform a certain action
      */
    public Boolean canPerformAction(Action action) {
        return actions.contains(action);
    }
}


