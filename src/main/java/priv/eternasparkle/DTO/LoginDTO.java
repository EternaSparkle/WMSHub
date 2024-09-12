package priv.eternasparkle.DTO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author EternaSparkle
 * @Date 2024/09/03
 * @Time 14:53
 */
@Data
public class LoginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
