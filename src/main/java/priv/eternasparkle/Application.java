package priv.eternasparkle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/25
 */
@SpringBootApplication
@MapperScan("priv.eternasparkle.mapper")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
        System.out.println("+......................+");
        System.out.println("[Application is RUNNING..]");
        System.out.println("+......................+");
    }
}
