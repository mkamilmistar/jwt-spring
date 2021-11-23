package com.jwt.userservice.configuration.security.config;

import com.jwt.userservice.util.RSAUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

@Configuration
public class KeyConfiguration {

	@Value("${security.jwt.private-key}")
	private String privateKey;

	@Value("${security.jwt.public-key}")
	private String publicKey;

	@Bean
	public KeyPair keypairBean() {

		PublicKey pubKey = RSAUtil.getPublicKey(publicKey);
		PrivateKey privKey = RSAUtil.getPrivateKey(privateKey);

        return new KeyPair(pubKey, privKey);
	}

}
