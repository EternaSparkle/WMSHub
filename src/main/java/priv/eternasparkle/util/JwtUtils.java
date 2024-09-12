package priv.eternasparkle.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtils {


    final static String secret = "a1abbbcccdddeeefffggghhhiiijjjab";
    final static long expireTimeLEN = 60L * 60L * 1000L;

    private static Date IssueDate(){
        return new Date();
    }
    private static Date ExpireDate(Date date){
        long ms = date.getTime();
        long exp = ms + expireTimeLEN;
        return new Date( exp );
    }


    public static String genarateJWT(Map<String,Object> userInfo){
        byte[] buff = secret.getBytes();
        SecretKey secretKey = Keys.hmacShaKeyFor(buff);
        Date issueDate = IssueDate();
        Date expireDate = ExpireDate( issueDate );
        String TOKEN = Jwts.builder()
                .setClaims( userInfo )
                .setIssuedAt( issueDate )
                .setExpiration( expireDate )
                //.setSubject("alecter")
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return TOKEN;
    }
    public static Claims parseJWT(String token){
        byte[] KEYS = secret.getBytes();
        Claims claims = Jwts.parser()
                .setSigningKey( KEYS )
                .parseClaimsJws( token )
                .getBody();
        return claims;
    }

    public static void checkExpire(Claims claims){
        Date curTime = new Date();
        Date expireTime = claims.getExpiration();
        boolean before = expireTime.before( curTime );
        if( before ){
            throw new RuntimeException("??? Token ?????????..");
        }
    }

}
