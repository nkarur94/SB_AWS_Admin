package com.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.admin.entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Long> {

}
