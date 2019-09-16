CREATE SCHEMA `prom_prom_hosp_admission` DEFAULT CHARACTER SET utf8mb4 ;

create user 'admin' identified by 'Password123';
 
GRANT ALL PRIVILEGES ON prom_hosp_admission.* TO 'admin'@'%' WITH GRANT OPTION;
