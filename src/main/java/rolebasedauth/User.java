package rolebasedauth;

import rolebasedauth.roles.Role;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private String name;
    private String uid;
    private Map<Resource, Role> resourceRoleMap;

    User(String name) {
        this.name = name;
        this.uid = generateUid();
        resourceRoleMap = new HashMap<>();
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

    public String generateUid(){
        return UUID.randomUUID().toString();
    }
}
