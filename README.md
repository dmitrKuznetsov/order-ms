# Order Microservice
This REST application:
* Support CRUD operations for orders
* Persist data in PostgreSQL database

## Local development
For starting database run command:
```bash
docker-compose -f docker-compose-postgres.yml -p orderms-postgres up -d    
```
### Access to pgAdmin
1. Visit: http://localhost:5050/browser/
2. Login: admin@admin.com  
   Password: root
3. Register server:
   IP: <Docker IP>  
   Username: order-mc-user  
   Password: order-mc-pass  

## Open API
Visit: http://localhost:8080/v3/api-docs