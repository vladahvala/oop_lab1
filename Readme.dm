Implemented features:
Servlets-based REST API (/patients, /doctors, /diagnoses, /treatments, /executions)
JDBC data access layer with PostgreSQL
MVC architecture (Controller / Service / DAO / Model)
JWT authentication with role-based access control (Doctor / Nurse)
CORS and request filtering via Servlet Filters
Logging using Log4j2
DTO mapping using MapStruct
Lombok for reducing boilerplate code
GoF design patterns:
Strategy Pattern (treatment execution by role)
Factory Pattern (service creation)
React frontend for interacting with REST API
Deployment-ready for Apache Tomcat
Database schema (~5 tables):
patients
doctors
nurses
diagnoses
treatments
treatment_executions