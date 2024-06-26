package cn.ming.smartmedic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private BigInteger id;

    private String title;

    private String content;

    private String description;

    private BigInteger userId;

    private String userName;

    private String createTime;

    private String updateTime;

    private Integer isDeleted;

    private String published;

    private String firstPicture;

}
