package festival.srv.service;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Singleton
public class JwtService {

	public String generateJwt() {
		Set<String> roles = new HashSet<>(
				Arrays.asList("admin", "writer")
		);

		return Jwt
				.issuer("Festival-jwt")
				.subject("Festival-app")
				.groups(roles)
				.expiresAt(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + (60 * 60 * 24))
				.sign();
	}
}
