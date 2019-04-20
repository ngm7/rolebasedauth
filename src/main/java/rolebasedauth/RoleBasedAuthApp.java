package rolebasedauth;

import rolebasedauth.resources.Resource;
import rolebasedauth.roles.AdminRole;
import rolebasedauth.roles.ReaderRole;
import rolebasedauth.roles.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class RoleBasedAuthApp {

    private static List<User> users;
    private static List<Resource> resources;

    RoleBasedAuthApp() {
        users = new ArrayList<>();
        resources = new ArrayList<>();
    }

    public static void main(String args[]){
        RoleBasedAuthApp app = new RoleBasedAuthApp();
        app.run();
    }

    public static Boolean hasAccess(User user, Resource resource, Action action) {
        return user.hasAccess(resource, action);
    }

    public static void AssignRoleToUser(User user, Role role) {
        for (Resource resource: resources) {
            AssignRoleForResourceToUser(user, resource, role);
        }
    }

    public static void AssignRoleForResourceToUser(User user, Resource resource, Role role) {
        user.assignRoleForResource(resource, role);
    }

    private void run() {
        // create a resource
        Resource dataStore = new Resource("dataStore");
        dataStore.assignAllowableActions(new HashSet<>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE)));
        resources.add(dataStore);

        Resource dataStore2 = Resource.Builder.newInstance()
                .Name("datastore2")
                .AllowedActions(dataStore.getAllowedActions())
                .build();

        // create a user, assign resources and roles to user
        User adminOne = new User("adminOne");
        adminOne.assignRoleForResource(dataStore, new AdminRole());
        users.add(adminOne);

        User adminTwo = User.Builder.newInstance()
                .Name("adminTwo")
                .ResourceRoleMap(adminOne.getResourceRoleMap()).build();
        adminTwo.assignRoleForResource(dataStore, new ReaderRole());
        users.add(adminTwo);

        System.out.print("Can Naman nuke a datastore? " + hasAccess(users.get(0), resources.get(0), Action.NUKE));
    }

}

/* Assumptions:
1. A user can have different roles for different resources
2. There are only 4 types of Actions: CREATE, READ, UPDATE, DELETE
3. A role can perform an assortment of above actions.
4. A user can have multiple roles assigned, but only one per resource.
 */