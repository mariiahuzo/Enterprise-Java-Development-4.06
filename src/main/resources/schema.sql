CREATE SCHEMA lab_4_06;
USE lab_4_06;
CREATE TABLE employees (
  employee_id BIGINT NOT NULL,
  department varchar (55),
  name varchar (55),
  status varchar (55),
  primary key (employee_id)
);

CREATE TABLE patients (
  patient_id BIGINT not null auto_increment,
  name varchar(55),
  date_of_birth datetime(6),
  admitted_by varchar(55),
  primary key (patient_id),
  foreign key (admitted_by) references doctor (employee_id)
);