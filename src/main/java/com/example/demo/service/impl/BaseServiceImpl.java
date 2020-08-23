package com.example.demo.service.impl;

import com.example.demo.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    @Autowired
    protected abstract JpaRepository getJpaRepository();

    @Override
    public <S extends T> S save(S entity) {
        return (S)getJpaRepository().save(entity);
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entitys) {
        return getJpaRepository().saveAll(entitys);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getJpaRepository().findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return getJpaRepository().existsById(id);
    }

    @Override
    public Iterable<T> findAll() {
        return getJpaRepository().findAll();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return getJpaRepository().findAllById(ids);
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getJpaRepository().findAll(pageable);
    }

    @Override
    public Long count() {
        return getJpaRepository().count();
    }

    @Override
    public void deleteById(ID id) {
        getJpaRepository().deleteById(id);
    }

    @Override
    public void delete(T entity) {
        getJpaRepository().delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        getJpaRepository().deleteAll(entities);
    }
}
