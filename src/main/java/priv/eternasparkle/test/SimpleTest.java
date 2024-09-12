package priv.eternasparkle.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SimpleTest {
    public static void main(String[] args){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String password = encoder.encode("123");
        System.out.println(password);

        String ps1 ="$2a$10$xEjndJN8sh6B5hlaEKv0D.RVDX7Nrelgk9Yi7/nhGBy2qbPUhGyoy";
        boolean ret = encoder.matches("123",ps1);
        System.out.println("匹配结果："+ret);
    }
}
