package cn.ming.smartmedic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diseases {
    private BigInteger id;

    private String title;

    private String content;

    private String description;

    private BigInteger userId;

    private String userName;

    private String createTime;

    private String updateTime;

    private Integer isDeleted;

    private String Published;

}
