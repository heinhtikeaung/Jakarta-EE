package com.hha.library;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

@DataSourceDefinition(
		name = "java:app/DataSource",
		url = "jdbc:hsqldb:mem:testDB",
		user = "sa",
		password = "sa",
		className = "org.hsqldb.jdbc.JDBCPool")
@ApplicationScoped
@FacesConfig(version = Version.JSF_2_3)
public class ApplicationConfig {

}
