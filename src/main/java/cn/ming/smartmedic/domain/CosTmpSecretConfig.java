package cn.ming.smartmedic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CosTmpSecretConfig {

    private String secretId;

    private String secretKey;

    private String secretToken;


}
