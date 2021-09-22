package org.jpwh.shared;

import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class ImprovedH2Dialect extends H2Dialect {
    public ImprovedH2Dialect(){
        super();
        //Function Customization?
        registerFunction("lpad",new StandardSQLFunction("lpad", StandardBasicTypes.STRING));

    }
}
