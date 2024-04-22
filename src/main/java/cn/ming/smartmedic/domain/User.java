package cn.ming.smartmedic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private BigInteger id;

    private String nickname;

    private String name;

    private String password;

    private String email;

    private String avator;

    private Integer type;

    private String createTime;

    private String updateTime;

    private Integer isDeleted;
}
