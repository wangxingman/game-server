package com.game.login.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("system.client")
public class ClientLoadProperties {
    private ClientProperties[] clients = {};
}
