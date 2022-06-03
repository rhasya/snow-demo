package com.snow.demo;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jose.jwk.gen.OctetSequenceKeyGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.util.Base64Utils;

import javax.crypto.SecretKey;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EncDecTests {

    @Autowired
    private JwtEncoder encoder;
    @Autowired
    private JwtDecoder decoder;

    public void makeRSAKey() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair keyPair = generator.generateKeyPair();
        try (PrintWriter pw = new PrintWriter("public.key")) {
            pw.println("-----BEGIN PUBLIC KEY-----");
            pw.println(Base64Utils.encodeToString(keyPair.getPublic().getEncoded()));
            pw.println("-----END PUBLIC KEY-----");
        }
        try (PrintWriter pw = new PrintWriter("private.key")) {
            pw.println("-----BEGIN PRIVATE KEY-----");
            pw.println(Base64Utils.encodeToString(keyPair.getPrivate().getEncoded()));
            pw.println("-----END PRIVATE KEY-----");
        }
    }

    public void testOctetKey() throws JOSEException {
        OctetSequenceKeyGenerator gen = new OctetSequenceKeyGenerator(256);
        OctetSequenceKey key = gen.algorithm(JWSAlgorithm.HS256).keyID("key").generate();
        SecretKey secretKey = key.toSecretKey();
        byte[] bytes = secretKey.getEncoded();

        String b64Key = Base64Utils.encodeToString(bytes);
        System.out.println(b64Key);
        assertEquals("NONE", secretKey.getAlgorithm());
    }

    @Test
    public void createEncoder() {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(36000))
                .subject("admin")
                .claim("scope", "ADMIN")
                .build();
        String token = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        assertNotNull(token);

        // decode
        Jwt jwt = decoder.decode(token);
        assertEquals("admin", jwt.getSubject());
        assertEquals("ADMIN", jwt.getClaim("scope"));
    }
}
