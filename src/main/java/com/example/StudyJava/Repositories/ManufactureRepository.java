package com.example.StudyJava.Repositories;

import com.example.StudyJava.Models.Manufacture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {
}
