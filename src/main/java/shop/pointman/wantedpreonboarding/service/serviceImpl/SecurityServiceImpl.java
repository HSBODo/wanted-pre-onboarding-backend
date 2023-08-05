package shop.pointman.wantedpreonboarding.service.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shop.pointman.wantedpreonboarding.service.SecurityService;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Value("${secret.encrypt.key}")
    private  String SECRET_ENCRYPT;

    private static String iv;
    private static Key keySpec;

    /**
     * JWT 토큰 발행
     * @param email : 이메일
     * @return String : 토큰
     * */
    @Override
    public String getJWTToken(String email) {


        Date currentDate = new Date(System.currentTimeMillis());
        Long expiration = 1000* 60L * 60L * 2L; //유효시간 2시간
        Date expirationDate = new Date(currentDate.getTime()+ expiration);
        // Header
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        // Payload
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", email);

        return    Jwts.builder()
                .setSubject("Login")
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256,SECRET_ENCRYPT)
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .compact();
    }

    /**
     * 토큰 파싱
     * @param token : JWT토큰
     * @return String : 데이터
     * */
    @Override
    public String getDataFromJWTToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_ENCRYPT)
                .parseClaimsJws(token)
                .getBody();
        String data = claims.get("data", String.class);
        return data;
    }

    @Override
    @PostConstruct
    public void AES256Util() throws Exception {

        this.iv = SECRET_ENCRYPT.substring(0, 16);
        byte[] keyBytes = new byte[16];
        byte[] b = SECRET_ENCRYPT.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        this.keySpec = keySpec;
    }

    @Override
    public String encrypt(String password) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] encrypted = c.doFinal(password.getBytes("UTF-8"));
        String enStr = Base64.getEncoder().encodeToString(encrypted);
        return enStr;
    }

    @Override
    public String decrypt(String password) throws Exception {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
        byte[] byteStr = Base64.getDecoder().decode(password.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }
}
