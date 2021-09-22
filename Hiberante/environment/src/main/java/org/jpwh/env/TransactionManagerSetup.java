package org.jpwh.env;


import bitronix.tm.resource.jdbc.PoolingDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.logging.Logger;

public class TransactionManagerSetup {
    public static final String DATASOURCE_NAME = "myDS";
    private static final Logger logger =
            Logger.getLogger(TransactionManagerSetup.class.getName());
    protected final Context context = new InitialContext();
    protected final PoolingDataSource datasource;
    public final DatabaseProduct databaseProduct;
}
