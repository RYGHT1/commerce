package com.emer.commerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.emer.commerce.custom.SoftDeleteRepositoryImpl;

@Configuration
@EnableJpaRepositories(repositoryBaseClass = SoftDeleteRepositoryImpl.class, basePackages = "com.emer.commerce.repositories")
public class JpaConfig {

}
