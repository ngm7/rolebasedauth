package rolebasedauth;

import rolebasedauth.resources.Resource;
import rolebasedauth.roles.Role;

import java.util.HashMap;
import java.util.Map;

/*
The User class represents our user. The user can have a name and stores the
resources and corresponding roles assigned to the user.
 */
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
        try {
            resourceRoleMap.put(resource, role);
        } catch (Exception ex) {
            // log.error(Error trying to assign role for resource)
            throw ex;
        }
    }

    public Boolean hasAccess(Resource resource, Action action) {
        try {
            if (resourceRoleMap.containsKey(resource)) {
                if (resource.isActionValid(action)) {
                    return resourceRoleMap.get(resource).canPerformAction(action);
                }
            }
            return false;
        } catch (Exception ex) {
            // log.error(Error trying to compute access)
            throw ex;
        }
    }

    public void removeRoleForResource(Resource resource, Role role) {
        try {
            if (resourceRoleMap.containsKey(resource)) {
                resourceRoleMap.remove(resource, role);
            }
            else {
                System.out.println(String.format("User - {} does not hold any role for resource - {} to remove", this.name, resource.getName()));
            }
        } catch (Exception ex) {
            System.out.println(String.format("Error is attempting to remove Role - {} for Resource - {} from User - {}", role, resource.getName(), this.name));
            throw ex;
        }
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
