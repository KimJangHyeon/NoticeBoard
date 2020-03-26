package com.wisefashion.noticeboard.Constant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    @Bean
    public int entityPerPage() {
        return 10;
    }
}
