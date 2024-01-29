package com.example.spinnerdemo.repository;

import com.example.spinnerdemo.model.Spinner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpinnerRepo extends JpaRepository<Spinner, Long> {

}