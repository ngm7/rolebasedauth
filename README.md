# rolebasedauth

Role Based Access Control:

Implement a role based auth system. System should be able to assign a role to user and remove a user from the role.

Entities are USER, ACTION TYPE, RESOURCE, ROLE

ACTION TYPE defines the access level(Ex: READ, WRITE, DELETE)

Access to resources for users are controlled strictly by the role.One user can have multiple roles. Given a user, action type and resource system should be able to tell whether user has access or not.


Please list down the assumptions made. Scope out things if you feel this takes more than 3 hours and add them to assumptions.

Note:

    Please no framework. REST APIs or DBs are not required. Use any object oriented programming language. Create classes etc by hand.
    Code should be maintainable and production ready. Follow the best practices of engineering and design patterns like creating models, PoJos etc. Keep them in separate files etc.
    A command line application with in memory models will suffice. Our main interest is in code organisation and logic. Please make sure you use OOP concepts & implementation as much as possible to keep the code clean & extendable. 
    No need to add any tests.
    You do not need to create provision to add/delete/update users or any other entities. You could pre-create them in your service class. Please dont focus on management part. Focus more on the usability & logic part.
