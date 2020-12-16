package com.hha.pos;

import javax.annotation.sql.DataSourceDefinition;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

@DataSourceDefinition(
		name = "java:app/DataSource",
		className = "org.hsqldb.jdbc.JDBCPool",
		url = "jdbc:hsqldb:file:/C:\\Users\\DELL\\Desktop\\database\\posDB",
		user = "sa",
		password = "sa")
@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class AppConfig {

}
