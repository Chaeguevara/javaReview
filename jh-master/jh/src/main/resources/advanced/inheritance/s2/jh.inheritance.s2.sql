--Employee Table

CREATE TABLE inheritance_s2_employee (
  EMPLOYEE_ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) DEFAULT NULL,
  PRIMARY KEY (EMPLOYEE_ID)
)

CREATE TABLE inheritance_s2_executive (
  EMPLOYEE_ID int(11) NOT NULL,
  ROLE varchar(255) DEFAULT NULL,
  PRIMARY KEY (EMPLOYEE_ID),
  CONSTRAINT FK_EMP FOREIGN KEY (EMPLOYEE_ID) REFERENCES inheritance_s2_employee (EMPLOYEE_ID)
) 
