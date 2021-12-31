# footballmanager
1.Build and run the project

mvn package

#------------------------------

2.Alternatively, you can run the app without "package" using:

mvn spring-boot:run

#------------------------------ Port starting at http://localhost:8087
localhost:8087/team/all

POST
localhost:8087/team/
{
   "id": 1,
   "createdAt": 2021-12-31,
   "name":"Football",
   "teamIdentifier":"One1",
   "updatedAt": 2021-12-31,
   "teamId": 1
}

