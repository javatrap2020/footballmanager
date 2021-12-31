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
   "createdAt": 20211231,
   "name":"Football",
   "teamIdentifier":"One1",
   "updatedAt": 20211231,
   "teamId": 1
}

