package com.znaji.productmanagement.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.stereotype.Component;


@ConfigurationProperties("jdbc")

@Getter
public class JdbcProperties {


    @ConstructorBinding
    public JdbcProperties(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    private final String driverClassName;
    private final String url;
    private final String username;
    private final String password;

}
