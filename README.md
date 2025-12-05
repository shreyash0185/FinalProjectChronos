
# Final Capstone Project — Distributed Job Scheduling & Execution System

This project is a complete microservices-based system that allows users to:
	•	Authenticate using JWT
	•	Create scheduled jobs
	•	Process jobs asynchronously using Kafka
	•	Execute workload through a dedicated Executor-Service
	•	View execution logs and job status

⸻

## 📌 Project Features
	•	User authentication with JWT
	•	API Gateway for request routing + token validation
	•	Job creation and management
	•	Cron-based scheduling
	•	Kafka-based event streaming
	•	Distributed job execution
	•	Separate databases for each service
	•	Docker Compose setup for complete environment
	•	Eureka service discovery

⸻

## 📁 Microservices Included

Service	Responsibility
API Gateway	Routes all client requests & validates JWT
Auth-Service	Signup, Login, JWT generation & validation
Job-Service	Job creation, metadata storage, status API
Scheduler-Service	Cron scheduler, triggers Kafka events
Executor-Service	Reads Kafka events and executes jobs
Eureka Server	Service discovery
Kafka & Zookeeper	Message broker for asynchronous communication


⸻

## 🧱 High-Level Architecture

                         ┌───────────────┐
                         │     CLIENT     │
                         │ (UI / Postman) │
                         └───────┬────────┘
                                 │
                     ┌────────────────────────┐
                     │      API GATEWAY       │
                     └───────┬────────────────┘
                             │ Auth & Routing
                             ▼
                ┌─────────────────────────────┐
                │         AUTH-SERVICE        │
                └───────────────┬────────────┘
                                │
                      ┌──────────────────┐
                      │ Authentication DB │
                      └──────────────────┘

      ┌────────────────────────────────────────────────────┐
      │                AFTER SUCCESSFUL LOGIN              │
      └────────────────────────────────────────────────────┘

                     ┌────────────────────────┐
                     │       JOB-SERVICE      │
                     └──────────┬─────────────┘
                                │ Stores Job Metadata
                                ▼
                       ┌────────────────────────┐
                       │        JOB DB          │
                       └────────────────────────┘

                     ┌────────────────────────┐
                     │   SCHEDULER-SERVICE    │
                     └──────────┬─────────────┘
                                │ Triggers Cron
                                ▼
                     ┌────────────────────────┐
                     │         KAFKA          │
                     │   (job-execute topic)  │
                     └──────────┬─────────────┘
                                │
                     ┌────────────────────────┐
                     │   EXECUTOR-SERVICE     │
                     └──────────┬─────────────┘
                                │ Writes Logs
                                ▼
                     ┌────────────────────────┐
                     │   EXECUTION LOG DB      │
                     └────────────────────────┘


⸻

## 💡 High-Level Concepts Used
	•	Microservices
	•	API Gateway pattern
	•	Service discovery (Eureka)
	•	Kafka event-driven architecture
	•	Asynchronous messaging
	•	JWT-based authentication
	•	Cron scheduling
	•	Database-per-service pattern
	•	Dockerized deployment using Docker Compose
	•	Distributed processing with Executor-Service

⸻

## ⚙️ Tech Stack
	•	Backend: Java 17, Spring Boot
	•	Security: JWT Authentication
	•	Messaging: Apache Kafka
	•	DB: MySQL
	•	Discovery: Spring Cloud Eureka
	•	Gateway: Spring Cloud Gateway
	•	Build Tool: Maven
	•	Containers: Docker + Docker Compose

⸻


Access Eureka Dashboard

http://localhost:8761


⸻

## 🧪 Testing the Flow

⸻

1️⃣ Signup

POST /auth/signup

Example:

{
  "username": "shreyash",
  "password": "12345"
}


⸻

2️⃣ Login

POST /auth/login

Response:

{
  "token": "eyJhbGciOiJIUzI1..."
}


⸻

3️⃣ Create a Job

POST /jobs
Authorization: Bearer <token>

Example:

{
  "jobType": "REPORT",
  "schedule": "*/5 * * * *",
  "params": {
    "reportType": "daily-sales"
  }
}


⸻

4️⃣ Scheduler triggers job via Kafka
	•	Topic: job-execute
	•	Message: { jobId: job-100, jobType: REPORT }

⸻

5️⃣ Executor-Service processes job
	•	Generates report
	•	Stores logs
	•	Updates job status

⸻

6️⃣ Get Job Status

GET /jobs/job-100/status
Authorization: Bearer <token>


⸻

## 🌟 Complete Real-Time Scenario (Short Summary)

	1.	User logs in → API Gateway → Auth-Service
	2.	User creates job-100 → Gateway → Job-Service
	3.	Job metadata stored → Job DB
	4.	Scheduler tracks timing → Scheduler-Service
	5.	Time arrives → Scheduler publishes → Kafka
	6.	Executor consumes event → Executor-Service
	7.	Executor runs job → logs saved → Execution DB
	8.	User checks job status → Gateway → Job-Service

⸻
