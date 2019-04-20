package rolebasedauth;

import rolebasedauth.roles.AdminRole;
import rolebasedauth.roles.ReaderRole;

import java.util.ArrayList;
import java.util.List;

public class RoleBasedAuthApp {

    private static List<User> users;
    private static List<Resource> resources;

    RoleBasedAuthApp() {
        users = new ArrayList<User>();
        resources = new ArrayList<Resource>();
    }

    public static void main(String args[]){
        RoleBasedAuthApp app = new RoleBasedAuthApp();
        Setup();

        System.out.print("Can Naman delete a datastore? " + hasAccess(users.get(0), resources.get(0), Action.DELETE));
    }

    public static Boolean hasAccess(User user, Resource resource, Action action) {
        return user.hasAccess(resource, action);
    }

    private static void Setup() {
        // create a resource
        Resource dataStore = new Resource("dataStore");
        resources.add(dataStore);

        // create a user, assign resources and roles to user
        User naman = new User("naman");
        naman.assignRoleForResource(dataStore, new AdminRole());
        users.add(naman);

        User ashish = new User("ashish");
        ashish.assignRoleForResource(dataStore, new ReaderRole());
        users.add(ashish);
    }

}

/* Assumptions:
1. A user can have different roles for different resources
2. There are only 4 types of Actions: CREATE, READ, UPDATE, DELETE
3. A role can perform an assortment of above actions.
4. A user can have multiple roles assigned
 */