# Task Manager Application
Task manager application with springboot and angular UI. It uses MySQL as backend.

# task-manager-springboot-angular
Repository URL
https://github.com/arunds/task-manager-springboot-angular.git

springboot application with docker, mysql and nginx

## Build & Run Angular Application
task-manager-springboot-angular\task-manager-ui> __ng serve__  

## Build and push docker file
task-manager-springboot-angular\task-manager-service> __mvn install dockerfile:build__  
task-manager-springboot-angular\task-manager-service> __mvn dockerfile:push__  

## Build & Run Spring Boot Application
task-manager-springboot-angular\task-manager-service> __mvn spring-boot:run__  


#After dockerizing the application, you can run both service and UI using docker compose from the root directory
task-manager-springboot-angular> __docker-compose up__  


## After the container started we can acccess the angular application in the below URL
IP Adress of the docker VM  
http://192.168.99.100:4200/

## Add Task Screen
![Add Task](screenshots/add-task.PNG?raw=true "Add Task Screen")

## View Task Screen

![View Task Screen](screenshots/view-task.PNG?raw=true "View Task Screen")

## Update Task Screen

![Update Task Screen](screenshots/update-task.PNG?raw=true "Update Task Screen")

Below is the mysql workbench data details

![MySql Workbench Screen](screenshots/mysql-db.PNG?raw=true "Database Screen")


