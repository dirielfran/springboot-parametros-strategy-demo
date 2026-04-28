package com.example.parametrosdemo.repository;

import com.example.parametrosdemo.entity.ParametroActividadEntity;
import com.example.parametrosdemo.entity.ParametroConfigId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametroActividadRepository extends JpaRepository<ParametroActividadEntity, ParametroConfigId> {}
