package org.vss.auth;

import jakarta.ws.rs.core.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vss.exception.AuthException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtAuthorizerTest {

	private JwtAuthorizer jwtAuthorizer;
	private HttpHeaders headers;

	private static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAysGpKU+I9i9b+QZSANu/\n" +
			"ExaA6w4qiQdFZaXeReiz49r1oDfABwKIFW9gK/kNnrnL9H8P+pYfj7jqUJ/glmgq\n" +
			"MsvBshbbD2FhxytSS0mhsbh6QxUhlanymPcSUUyKBD6v7W0CGUhS5luHlsCFn4ys\n" +
			"lFk4pavcBtGap0DTUc8yz0j/xnmSQbdjWgm0awbHN48uItRO3UhLAOetG+BzlWCR\n" +
			"8YsTa5piV8KgJpG/rwYTGXuu3lcCmnWwjmbeDq1zFFrCDDVkaIHkGJgRuFIDPXaH\n" +
			"yUw5H2HvKlP94ySbvTDLXWZj6TyzHEHDbstqs4DgvurB/bIhi/dQ7zK3EIXL8KRB\n" +
			"hwIDAQAB\n" +
			"-----END PUBLIC KEY-----";

	private static final String VALID_AUTH_HEADER = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0MTViODVlMC1hMDcxLTcwZmQtNDRkZi0yMmI5MTFlYWEwMzQiLCJqdGkiOiJhMzM3Nzc0Ni04ZmQ4LTRiMDAtODJlNy1mMTMxM2RiN2ZjM2YiLCJpYXQiOjE3MjE5ODIzNTB9.QSt3Lkfi987Cf6Ob0c2RYa83KLFGtTlwdCgEStwYDJ22pEDZMJ3K4rlk7DsLKYmztIY1XbJHQ2qcYNHIpX27Ba8FspcBKCY_hzUKz9ZGWvkY2mOGrU7K4s56zg79FKUYWQYd_ccy01uEIS0JbVQgcDKFjchiDYmiIxo4CQAg9bgfqYhTXRhHVrzSLpYovqWw943JkY6jZxQp04cTPfJ_0I6fqyTbaEC47wFFycIgpxbtIE1xl1cOgjKU1EoHtP3T6ulJQ0TxV7k7N3VbYCTxJRqJ7Xkr57nZWGzH2NzB2uDn4AlGJWEYNh7PWlF8oPLbNjBo8oHux4QHt-ZdgSktZQ";

	private static final String VALID_USER_ID = "415b85e0-a071-70fd-44df-22b911eaa034";

	@BeforeEach
	public void setUp() throws Exception {
		jwtAuthorizer = new JwtAuthorizer();
		headers = mock(HttpHeaders.class);
	}

	@Test
	public void testValidJwtToken() {
		when(headers.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn(VALID_AUTH_HEADER);

		AuthResponse authResponse = jwtAuthorizer.verify(headers);

		assertNotNull(authResponse);

		assertEquals(VALID_USER_ID, authResponse.getUserToken());
	}

	@Test
	public void testMissingAuthorizationHeader() {
		when(headers.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn(null);

		assertThrows(AuthException.class, () -> jwtAuthorizer.verify(headers));
	}

	@Test
	public void testInvalidAuthorizationHeader() {
		when(headers.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn("InvalidHeader");

		assertThrows(AuthException.class, () -> jwtAuthorizer.verify(headers));
	}

	@Test
	public void testInvalidJwtToken() {
		String invalidJwt = "Bearer invalid.jwt.token";
		when(headers.getHeaderString(HttpHeaders.AUTHORIZATION)).thenReturn(invalidJwt);

		assertThrows(AuthException.class, () -> jwtAuthorizer.verify(headers));
	}
}
