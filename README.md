# User Application - Spring Boot, Docker, and Kubernetes
This project is a Spring Boot application designed to manage and process user data. It uses a REST API to handle user creation, and processes the data in a Kubernetes cluster. The application is containerized using Docker and is ready for deployment to any Kubernetes environment.

These instructions will help you set up the project on your local machine for development, testing, or production deployment via Docker and Kubernetes.

Prerequisites
You will need to install the following tools before running this project:

- Java 17 (or higher) 
- Maven (for building the project)
- Docker (for containerizing the application)
- Kubernetes (Use Docker Desktop with Kubernetes enabled for local deployment)
- kubectl (for managing Kubernetes clusters)
  
Project Structure

── src                        # Application source code

── Dockerfile                 # Docker configuration file

── kubernetes/                # Kubernetes YAML configurations (Deployment, Service)
│   ├── deployment.yml
│   ├── service.yml

── README.md                  # Project documentation

── pom.xml                    # Maven build file

── target/                    # Compiled Spring Boot app (.jar)

# To Run  the Project Locally

Clone the repository:
Copy code
#git clone https://github.com/SharanyaPalli/user-application.git

Build the project: Make sure you have Maven installed, then run:

#commands to run
- mvn clean install
  
#Run the application:

- java -jar target/UserData-1.0-SNAPSHOT.jar

- Access the application: The Spring Boot application runs on http://localhost:8080. You can access the API endpoints from there.

# Run with Docker

- The application is already containerized and the image is available on Docker Hub.

#Pull the Docker Image :

 - docker pull sharanyapalli/user-data:v1
   
 #Run the Docker Container : 
 
- docker build -t sharanyapalli/user-data:v1 .      
- docker run -d -p 8080:8080 sharanyapalli/user-data:v1
- The application will now be accessible on http://localhost:8080.

# Deploy to Kubernetes

Follow these steps to deploy the application to a Kubernetes cluster.

1. Ensure Kubernetes is Running Locally (with Docker Desktop)
   
  - Make sure Kubernetes is enabled in Docker Desktop and that kubectl is configured properly. Verify by running:

    #kubectl cluster-info

2. Apply the Kubernetes Configurations
   
- Navigate to the folder containing the Kubernetes YAML files(i.e where the entire project is  kubernetes/deployment.yaml ,service.yaml) and apply the deployment 
  and service configuration:

  #kubectl apply -f kubernetes/deployment.yml
  #kubectl apply -f kubernetes/service.yml

3. Check the Pod Status

- To ensure the application is running, check the status of the pods:

  #kubectl get pods

4. Access the Application

- By default, Kubernetes exposes the service on port 30007 (as configured in service.yml). You can access it on:

  #http://localhost:30007

5. External Access (Optional)
 
 - To allow external access to the service from other machines:
 - If using a cloud provider (like AWS, Azure, or GCP), you'll need to expose your Kubernetes service using a load balancer or ingress.
 -  For local testing, you can port-forward Kubernetes services to your host machine:

   #kubectl port-forward service/user-application-service 30007:8080
   #Then, you can access it at http://<your-external-ip>:30007.

# Usage

-Here are the basic REST API endpoints you can use:

#Add User (POST): /api/addUser
- Body: {
    "userId":"197379",
    "name":"Test",
    "mobileNumber2gmai.com":"17234",
    "email":"test"
}

#Get All Users (GET): `/api/getUser



