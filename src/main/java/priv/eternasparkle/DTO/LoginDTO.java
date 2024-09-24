package priv.eternasparkle.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录 DTO
 *
 * @author EternaSparkle
 * @version 1.0.0
 * @date 2024/09/24
 */
@Data
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
