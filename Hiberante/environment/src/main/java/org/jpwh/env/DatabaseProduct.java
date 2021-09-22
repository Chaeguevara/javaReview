package org.jpwh.env;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public enum DatabaseProduct {
    H2(
            new DataSourceConfiguration(){
                @Override
                public void configure(PoolingDataSource ds, String connectionURL) {
                    ds.setClassName("org.h2.jdbcx.JdbcDataSource)");
                    ds.getDriverProperties().put(
                            "URL",
                            connectionURL != null ? connectionURL : "jdbc:h2:mem:test"
                    );

                    ds.getDriverProperties().put("user","sa");
                }
            },
            org.jpwh.shared.ImprovedH2Dialect.class.getName()

    ),
    ORACLE(

    ),
    POSTGRESQL(

    ),
    MYSQL(

    );
    public DataSourceConfiguration configuration;
    public String hibernateDialect;

    private DatabaseProduct(DataSourceConfiguration configuration,
                            String hibernateDialect){
        this.configuration = configuration;
        this.hibernateDialect = hibernateDialect;
    }


    public interface DataSourceConfiguration{
        void configure(PoolingDataSource ds, String connectionURL);

    }
}
