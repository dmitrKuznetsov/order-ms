# Order Microservice
This RESTful application:
* Supports CRUD operations for orders.
* Uses PostgreSQL database for persisting data

## Local development
For starting database run command:
```bash
docker-compose -f docker-compose-postgres.yml -p orderms-postgres up -d
```
Use OpenAPI to test features

### Access to pgAdmin
1. Visit: http://localhost:5050/browser/
2. Login: admin@admin.com  
   Password: root
3. Register server:
   Host name: <Docker IP>  
   Username: order-mc-user  
   Password: order-mc-pass  

## Open API
UI: http://localhost:8080/swagger-ui.html  
JSON file: http://localhost:8080/v3/api-docs  
Yaml file: http://localhost:8080/v3/api-docs.yaml

# Technological stack
- SpringBoot as a skeleton framework
- SpringBoot Web starter
- PostgreSQL database as a database for saving orders and order items
- SpringBoot MyBatis starter
- Flyway database migration tool
