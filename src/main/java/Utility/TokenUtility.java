package Utility;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;


import com.snappad.model.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenUtility {
	static Long expireDate=86400000L;
	static String stringKey="AF231275";
	static byte[] keyBytes= DatatypeConverter.parseBase64Binary(stringKey);
	static Key key=new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

	
	public static String createToken(UserModel user){
		JwtBuilder builder= Jwts.builder().setId(user.getUsermobilenum()).setIssuedAt(new Date()).setIssuer(user.getUsername())
				.signWith(SignatureAlgorithm.HS256, key);
		builder.setExpiration(new Date(new Date().getTime()+expireDate));
		return builder.compact();
	}
	public static void isValid(String token){
		Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		System.out.println(claims.getExpiration());
		System.out.println(claims.getId());

	}
}
