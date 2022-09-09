package com.project.it.repository;

import com.project.it.entity.Studies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudiesRepository extends JpaRepository<Studies, Long> {
}
