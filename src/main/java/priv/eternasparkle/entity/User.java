/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package priv.eternasparkle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.sql.Timestamp;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private Integer deptId;
    private String headImg;
    @TableField(exist = false)
    private Integer roleId;
    private String nickName;
    private String email;
    private String phone;
    private String age;
    private String sex;
    private String no;
    private Role role;
    public User(){

    }
    public Role getPrimaryRole(){
        if (role==null){
            Role r = new Role();
            r.setId(-1);
            r.setRoleName("未分配角色");
            return r;
        }
        return role;
    }

    public Integer getPrimaryRoleId(){
        Role r = getPrimaryRole();
        setRoleId(r.getId());
        return r.getId();
    }

    public User(String line){
        String[] data = line.split(",");
        this.id = Integer.valueOf(data[0]);
        this.username = data[1];
        this.password = data[2];
/*      this.roleId = Integer.valueOf(data[3]);*/
        this.nickName = data[4];
    }
}
