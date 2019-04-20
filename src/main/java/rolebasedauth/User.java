package rolebasedauth;

import rolebasedauth.resources.Resource;
import rolebasedauth.roles.Role;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private Map<Resource, Role> resourceRoleMap;

    User(String name) {
        this.name = name;
        resourceRoleMap = new HashMap<>();
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.resourceRoleMap = builder.resourceRoleMap;
    }

    public String getName() {
        return name;
    }

    public Map<Resource, Role> getResourceRoleMap() {
        return resourceRoleMap;
    }

    public void assignRoleForResource(Resource resource, Role role) {
        resourceRoleMap.put(resource, role);
    }

    public Boolean hasAccess(Resource resource, Action action) {
        if (resourceRoleMap.containsKey(resource)) {
            if (resource.isActionValid(action)) {
                return resourceRoleMap.get(resource).canPerformAction(action);
            }
        }
        return false;
    }


    public static class Builder {
        /// instance fields
        private String name;
        private Map<Resource, Role> resourceRoleMap;

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

        public Builder ResourceRoleMap(Map<Resource, Role> resourceRoleMap) {
            this.resourceRoleMap = resourceRoleMap;
            return this;
        }

        // build method to deal with outer class
        // to return outer instance
        public User build()
        {
            return new User(this);
        }
    }
}
