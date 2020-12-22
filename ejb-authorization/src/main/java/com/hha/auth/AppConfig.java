package com.hha.auth;

import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FormAuthenticationMechanismDefinition(loginToContinue = @LoginToContinue(
		loginPage = "login.xhtml"))
@DeclareRoles({"Admin", "Member"})
@ApplicationScoped
public class AppConfig {

}
