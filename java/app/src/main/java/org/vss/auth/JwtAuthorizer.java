package org.vss.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import jakarta.ws.rs.core.HttpHeaders;
import org.vss.exception.AuthException;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import java.io.IOException;

// A JWT (https://datatracker.ietf.org/doc/html/rfc7519) based authorizer,
public class JwtAuthorizer implements Authorizer {


	private static final String BEARER_PREFIX = "Bearer ";
	private static final int MAX_USER_TOKEN_LENGTH = 120;

	// `pemFormatRSAPublicKey` is RSA public key used by JWT Auth server for creating signed JWT tokens.
	// Refer to OpenSSL(https://docs.openssl.org/1.1.1/man1/rsa/) docs for generating valid key pairs.
	// Example:
	// * To generate private key, run : `openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048`
	// * To generate public key, run: `openssl rsa -pubout -in private_key.pem -out public_key.pem`

	@Override
	public AuthResponse verify(HttpHeaders headers) throws AuthException {

		try {
			String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
			if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER_PREFIX)) {
				throw new AuthException("Missing or invalid Authorization header.");
			}

			// Extract token by excluding BEARER_PREFIX.
			String token = authorizationHeader.substring(BEARER_PREFIX.length());

			// Verify the token.
   			HttpClient client = HttpClient.newHttpClient();
        	HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://cloud-api.thunderstack.org/api/verify"))
            .header("Authorization", "Bearer " + token) // Pass the token in the Authorization header
            .GET()
            .build();

			// Send the request
			HttpResponse<String> response;
			try {
				response = client.send(request, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				throw new AuthException("Error while sending HTTP request: " + e.getMessage());
			}
			// Check the response status code
			if (response.statusCode() != 200) {
				throw new AuthException("Failed to verify token ");
			}

			String responseBody = response.body();
        	try (JsonReader jsonReader = Json.createReader(new StringReader(responseBody))) {
				JsonObject jsonObject = jsonReader.readObject();

				// Extract the "sub" field
				String userId = jsonObject.getString("sub", null);

				if (userId == null || userId.isBlank()) {
					throw new AuthException("Invalid token response from verification API.");
				}

				// Return the AuthResponse with the userToken
				return new AuthResponse(userId);
        	}			

		} catch (JWTVerificationException e) {
			throw new AuthException("Invalid JWT token.");
		}
	}

	private PublicKey loadPublicKey(String pemFormatRSAPublicKey) throws Exception {
		String key = pemFormatRSAPublicKey
				.replaceAll("\\n", "")
				.replace("-----BEGIN PUBLIC KEY-----", "")
				.replace("-----END PUBLIC KEY-----", "");

		byte[] keyBytes = Base64.getDecoder().decode(key);

		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		return keyFactory.generatePublic(spec);
	}
}
