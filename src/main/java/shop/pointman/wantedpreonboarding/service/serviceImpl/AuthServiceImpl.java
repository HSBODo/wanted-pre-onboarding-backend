package shop.pointman.wantedpreonboarding.service.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.AuthService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Value("${secret.jwt.key}")
    private  String SECRET_ENCRYPT;
    /**
     * email 형식 검사
     * @param email : 이메일
     * @return boolean : 이메일 형식 여부
     * */
    @Override
    public boolean isValidationEmail(String email) {
        boolean validation = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            validation = true;
        }

        return validation;
    }
    /**
     * password 길이 검사
     * @param password : 비밀번호
     * @return boolean : 비밀번호 길이 검사
     * */
    @Override
    public boolean isValidationPassword(String password) {
        boolean validation = false;

        if (password.length()>=8){
            validation = true;
        }
        return validation;
    }

    @Override
    public boolean isJoin(Account account) {
        return true;
    }
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
}
