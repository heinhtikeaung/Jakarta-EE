package com.hha.orders;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinition(
		name = "java:app/DataSource",
		url = "jdbc:hsqldb:mem:testDB",
		user = "sa",
		password = "sa",
		className = "org.hsqldb.jdbc.JDBCPool"
)
@ApplicationScoped
public class AppConfig {

}
