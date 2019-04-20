package rolebasedauth.resources;

import rolebasedauth.Action;
import java.util.HashSet;

/*
 A Resource class identifies the set of actions it allows any user to perform.
 */
public class Resource {
    /*
    The name of the resource.
     */
    private String name;

    /*
    The set of allowed Actions that a user can perform.
     */
    private HashSet<Action> allowedActions;

    /*
    The Resource constructor
     */
    public Resource(String name) {
        this.name = name;
        allowedActions = new HashSet<>();
    }

    /*
    The resource constructor for Builder
     */
    private Resource(Builder builder) {
        this.name = builder.name;
        this.allowedActions = builder.allowedActions;
    }

    /*
    Getter for allowedActions
     */
    public HashSet<Action> getAllowedActions() {
        return allowedActions;
    }

    /*
    Getter for Name
     */
    public String getName() {
        return name;
    }

    /*
    Assign a set of actions that can be performed on a resource.
     */
    public void assignAllowableActions(HashSet<Action> actions) {
        for (Action action : actions) {
            addAction(action);
        }
    }

    /*
    Add an action that can be performed on a resource.
     */
    public void addAction(Action action) {
        try {
            allowedActions.add(action);
        } catch (Exception ex) {
            System.out.println(String.format("Error trying to Add action - {} to the set of allowed action for Resource", action.toString(), this.name));
        }
    }

    /*
    Remove an action that can be performed on a resource
     */
    public void removeAction(Action action) {
        try {
            allowedActions.remove(action);
        } catch (Exception ex) {
            System.out.println(String.format("Error trying to Remove action - {} from the set of allowed action for Resource", action.toString(), this.name));
        }
    }

    /*
    Check if an action is even performable on a resource.
     */
    public Boolean isActionValid(Action action) {
        return allowedActions.contains(action);
    }

    // Resource Builder
    public static class Builder {

        /// instance fields
        private String name;
        private HashSet<Action> allowedActions;

        public static Builder newInstance()
        {
            return new Builder();
        }

        private Builder() {}

        public Builder Name(String name)
        {
            this.name = name;
            return this;
        }

        public Builder AllowedActions(HashSet<Action> allowedActions)
        {
            this.allowedActions = allowedActions;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public Resource build()
        {
            return new Resource(this);
        }
    }

}
