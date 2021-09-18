package org.jpwh.helloworld;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

public class HelloWorldJPA {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");

}
