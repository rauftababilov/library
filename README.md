# Library app

## Rules
1. The name of the publishing house must be unique.
2. There is only one copy of a book.
3. The book has only one publishing house.
4. Only a user with the ROOT role can create new users.
5. All new users have only the ADMIN role.
6. Unable to delete a user with the ROOT role.
7. User update is prohibited.
8. All entities, except for audit records, have soft delete logic. They cannot be deleted completely, they can only be marked as deleted.
9. The client's name must be unique.
10. The name of the author may not be unique.

## Initial data

ROOT user:
* username: admin 
* password: admin

## Instructions

To run the application, execute the command:
 ```
  docker-compose up
 ```
