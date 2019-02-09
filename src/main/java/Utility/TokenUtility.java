package Utility;

import com.snappad.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


public class TokenUtility {
	static Long expireDate=86400000L;
	static String stringKey = "AF23127586131376leilioAmirALi*snappadd*thisisveryHardKey";
	static byte[] keyBytes= DatatypeConverter.parseBase64Binary(stringKey);
	static Key key=new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

	
	public static String createToken(UserModel user){
		JwtBuilder builder= Jwts.builder().setId(user.getUsermobilenum()).setIssuedAt(new Date()).setIssuer(user.getUsername())
				.signWith(key, SignatureAlgorithm.HS256);
		builder.setExpiration(new Date(new Date().getTime()+expireDate));
		return builder.compact();
	}

	public static boolean isValid(String token) {
		Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		System.out.println(claims.getExpiration());
		System.out.println(claims.getId());
		return claims.getExpiration().getTime() < new Date().getTime();

	}
}
