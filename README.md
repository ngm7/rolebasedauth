# rolebasedauth

=================
Problem Statement
=================
Role Based Access Control:

1. Implement a role based auth system. System should be able to assign a role to user and remove a user from the role
2. Entities are USER, ACTION TYPE, RESOURCE, ROLE
3. ACTION TYPE defines the access level(Ex: READ, WRITE, DELETE)
4. Access to resources for users are controlled strictly by the role.One user can have multiple roles. Given a user, action type and resource system should be able to tell whether user has access or not.


Please list down the assumptions made. Scope out things if you feel this takes more than 3 hours and add them to assumptions.

===========
Assumptions
===========
1. The actions are all enums specified in the Action file: CREATE, READ, UPDATE, DELETE, NUKE
2. A user can have different roles for different resources
3. A resource has a set of AllowedActions to specify what actions make sense for a resource. (it probably doesn't make sense to nuke the billing datastore)
4. A role can perform a set of actions.
5. A user can have multiple roles assigned, but only one per resource.

==============
For the reader
==============

The RoleBasedAuthApp is the entry point for the application. It demonstrates a scenario that contains all the features required. Some structure to keep in mind:

1. roles/Role.java - contains class definition for an abstract class for Role.
	a. Each possible role is extensible by adding or removing the actions it can perform.
	b. Different role classes can be created based on the administration's discretion.

2. resources/Resources.java - contains class definition for a resource.
    a. This resource also specifies a list of allowable actions on that resource.
    b. This is deliberately not kept abstract since the use case is limited right now.
    c. The Builder pattern can be used extensively to create resources.

3. Action.java - contains a global set of Actions that the app allows
4. User.java - contains the class definition for a user.
    a. The user object keeps a map of the roles assigned to that user for each resource.
    b. A user can be assigned a role specific to a resource or be removed from that role for that resource.
    c. The builder patter can be used extensively to create users easily.
    d. A user can have multiple roles assigned to it - but only one per resource.


