package com.emer.commerce.custom;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.emer.commerce.domain.BaseEntity;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

public class SoftDeleteRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SoftDeleteRepository<T, ID> {

    private final EntityManager entityManager;

    public SoftDeleteRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void softDelete(T entity) {
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            baseEntity.setDeleted_at(LocalDateTime.now());
            entityManager.merge(baseEntity);
        }
        throw new UnsupportedOperationException("Entity must be an instance of BaseEntity");
    }
    
}
