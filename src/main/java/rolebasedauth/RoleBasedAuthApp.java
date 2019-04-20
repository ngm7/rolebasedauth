package rolebasedauth;

import rolebasedauth.resources.Resource;
import rolebasedauth.roles.GenericRole;
import rolebasedauth.roles.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
The Role Based Auth application. The assumptions made are:
1. The actions are all enums specified in the Action file: CREATE, READ, UPDATE, DELETE, NUKE
2. A user can have different roles for different resources
3. A resource has a set of AllowedActions to specify what actions make sense for a resource. (it probably doesn't make sense to nuke the billing datastore)
4. A role can perform a set of actions.
5. A user can have multiple roles assigned, but only one per resource.
 */
public class RoleBasedAuthApp {

    /*
    A list of users that the app has registered. This list could ideally be built with a REST API calls, but for brevity here,
    it is in-memory list.
     */
    private static List<User> users;

    /*
    A list of resources that the app is monitoring over. This list could ideally be built with a REST API calls, but for brevity here,
    it is in-memory list.
     */
    private static List<Resource> resources;

    /*
    Empty constructor
     */
    RoleBasedAuthApp() {
        users = new ArrayList<>();
        resources = new ArrayList<>();
    }

    /*
    Check if a given user has access to a resource for a given action.
     */
    public static Boolean hasAccess(User user, Resource resource, Action action) {
        return user.hasAccess(resource, action);
    }

    /*
    Assign a particular role to a user. This role will be applied to all the registered resources with this application.
     */
    public static void AssignRoleToUser(User user, Role role) {
        for (Resource resource: resources) {
            AssignRoleForResourceToUser(user, resource, role);
        }
    }

    /*
    Assign a given role for a given resource for the given user.
     */
    public static void AssignRoleForResourceToUser(User user, Resource resource, Role role) {
        if (resources.contains(resource) && users.contains(user)) {
            user.assignRoleForResource(resource, role);
        } else {
            // log error.
            System.out.println(String.format("The user - {} or the Resource- {} does not exist. Doing nothing.", user.getName(), resource.getName()));
        }
    }

    /*
    Remove a given role for a given resource for the given user.
     */
    public static void RemoveRoleForResourceToUser(User user, Resource resource, Role role) {
        if (resources.contains(resource) && users.contains(user)) {
            user.removeRoleForResource(resource, role);
        } else {
            // log error.
            System.out.println(String.format("The user - {} or the Resource- {} does not exist. Doing nothing.", user.getName(), resource.getName()));
        }
    }

    /*
    The run method comprises a demo scenario.
    A user is created, a resource is created.
    The user is assigned an admin role which cannot perform the NUKE action.
    A check is made to determine that the user cannot nuke
    Then the user's role is changed to Nukable.
     */
    private void run() {

        // Creating Roles - Admin can do CRUD operations.
        HashSet<Action> adminActions = new HashSet<>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE));
        GenericRole adminRole = new GenericRole(adminActions);

        // But the role who can Nuke is the real deal.
        HashSet<Action> nukeActions = new HashSet<>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE, Action.NUKE));
        GenericRole nukeRole = new GenericRole(nukeActions);

        // create a resource for demonstration
        Resource dataStore = new Resource("dataStore");
        dataStore.assignAllowableActions(new HashSet<>(Arrays.asList(Action.CREATE, Action.READ, Action.WRITE, Action.DELETE, Action.NUKE)));
        resources.add(dataStore);
        System.out.println(String.format("Registering {} resource", dataStore.getName()));

        // create a user - let's call him ourGuy and register him to the app.
        User ourGuy = new User("ourGuy");
        users.add(ourGuy);
        System.out.println(String.format("Registering {} user", ourGuy.getName()));

        // Give admin powers to our guy - Assign admin role to the user.
        System.out.println("Assigning adminRole to adminOne for DataStore");
        AssignRoleForResourceToUser(ourGuy, dataStore, adminRole);

        // Our guy cannot obviously Nuke the datastore because, meh Admin
        System.out.println(String.format("Can {} {} a {}? \n {}",
                ourGuy.getName(),
                Action.NUKE.toString(),
                dataStore.getName(),
                hasAccess(users.get(0), resources.get(0), Action.NUKE)));

        // Remove admin access from ourGuy.
        System.out.println(String.format("Removing admin role for ourGuy"));
        RemoveRoleForResourceToUser(ourGuy, dataStore, adminRole);

        // Assign NukeRole for ourGuy - Give him real powers.
        System.out.println(String.format("Giving NukeRole for ourGuy"));
        AssignRoleForResourceToUser(ourGuy, dataStore, nukeRole);

        // Our guy cannot obviously Nuke the datastore because, meh Admin
        System.out.print(String.format("Can {} {} a {}? \n {}",
                ourGuy.getName(),
                Action.NUKE.toString(),
                dataStore.getName(),
                hasAccess(users.get(0), resources.get(0), Action.NUKE)));

        System.out.println("Destruction ensued. Fin.");
    }

    public static void main(String args[]){
        RoleBasedAuthApp app = new RoleBasedAuthApp();
        app.run();
    }
}