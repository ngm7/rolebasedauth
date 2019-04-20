package rolebasedauth.resources;

import rolebasedauth.Action;

import java.util.HashSet;

public class Resource {
    private String name;
    private HashSet<Action> allowedActions;

    public Resource(String name) {
        this.name = name;
        allowedActions = new HashSet<>();
    }

    private Resource(Builder builder) {
        this.name = builder.name;
        this.allowedActions = builder.allowedActions;
    }

    public HashSet<Action> getAllowedActions() {
        return allowedActions;
    }

    public void assignAllowableActions(HashSet<Action> actions) {
        for (Action action : actions) {
            assignAction(action);
        }
    }

    public void assignAction(Action action) {
        try {
            allowedActions.add(action);
        } catch (Exception ex) {
            // log.error(Cannot add action for Resource
        }
    }

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
