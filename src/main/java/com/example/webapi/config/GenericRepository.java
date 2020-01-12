package com.example.webapi.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface GenericRepository<T>
		extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T>, PagingAndSortingRepository<T, Integer> {

}
