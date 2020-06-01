package com.bootcamp.security.mssecurity;

import java.security.Key;
import java.security.Principal;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class MsSecurityApplication extends WebSecurityConfigurerAdapter {
	
	
	public static void main(String[] args) {
		SpringApplication.run(MsSecurityApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login**", "/user", "/userInfo").authenticated().and().logout()
				.logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {

		return principal;
	}

	@RequestMapping("/userInfo")
	public String userInfo(Principal principal) {
		final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
		final Authentication authentication = oAuth2Authentication.getUserAuthentication();
		// Manually getting the details from the authentication, and returning them as
		// String.
		return authentication.getDetails().toString();
	}

	@PostMapping("/placeOrder")
	public String placeOrder() {
		System.out.println("Inside method call....");
		
		  String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
		  SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		  byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
	        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		long nowMillis = System.currentTimeMillis(); 
		JwtBuilder builder = Jwts.builder().setIssuedAt(new Date(nowMillis)).setSubject(String.valueOf(1))  
				  .setIssuer("MsSecurityService")
				  .claim("email", "devzenith@gmail.com")
				  .signWith(signatureAlgorithm, signingKey)
				  .setExpiration(new Date(nowMillis));
		String jwtGenerated = builder.compact();
		System.out.println("JWT Generated is " + jwtGenerated);
		String status=jwtGenerated;
		return status;

	}

}
