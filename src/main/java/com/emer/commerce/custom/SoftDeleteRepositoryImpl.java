package com.emer.commerce.custom;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.emer.commerce.domain.BaseEntity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class SoftDeleteRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SoftDeleteRepository<T, ID> {

    public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    @Override
    @Transactional
    public void restore(T entity) {
        entity.setDeleted_at(null);
    }

    @Override
    @Transactional
    public void softDelete(T entity) {
            entity.setDeleted_at(LocalDateTime.now());
    }
}
