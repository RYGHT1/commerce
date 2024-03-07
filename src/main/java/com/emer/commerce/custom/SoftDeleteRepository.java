package com.emer.commerce.custom;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SoftDeleteRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    void softDelete(T entity);
}
