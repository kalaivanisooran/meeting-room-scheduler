package cts.rabobank.glassdoorscheduler.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import cts.rabobank.glassdoorscheduler.exception.CustomOauthException;
import cts.rabobank.glassdoorscheduler.service.UserInfoService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServer  extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder pwdEncoder;

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}
	
	
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
				.withClient("glassdoor")
				.secret(pwdEncoder.encode("glassdoor"))
				.authorizedGrantTypes("password", "refresh_token")
				//.accessTokenValiditySeconds(20000)
				//.refreshTokenValiditySeconds(60*60*24*7)
				.scopes("read","write")
				.authorities("ADMIN","GUEST")
				.autoApprove(true);
				
	}
	
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager)
				  .userDetailsService(userInfoService)
				  .exceptionTranslator(exception -> {
					System.out.println("Exception Type :" +exception);  
		            if (exception instanceof OAuth2Exception) {
		                OAuth2Exception oAuth2Exception = (OAuth2Exception) exception;
		                return ResponseEntity.status(oAuth2Exception.getHttpErrorCode()).body(new CustomOauthException(oAuth2Exception.getMessage()));
		            } else {
		                throw exception;
		            }
				  });
	}

	

}

