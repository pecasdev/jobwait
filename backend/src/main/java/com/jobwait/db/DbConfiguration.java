package com.jobwait.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource")
public class DbConfiguration {

    private String url = "";
    private String username = "";
    private String password = "";

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String newUrl) {
        this.url = newUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

}