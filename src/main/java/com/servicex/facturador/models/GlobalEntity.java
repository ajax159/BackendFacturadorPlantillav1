package com.servicex.facturador.models;



import jakarta.persistence.*;


import org.springframework.format.annotation.DateTimeFormat;

import com.servicex.facturador.util.CapitalizeFirstLetter;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

//import java.security.Timestamp;

@MappedSuperclass
public abstract class GlobalEntity {

    @Column(name = "glb_estado_est_id", columnDefinition = "int default 1")
    protected  Long glbEstadoEstId;
    @Column(name = "created_by")
    protected  Long createdBy;
    @Column(name = "updated_by")
    protected Long updatedBy;
    @Column(name = "created_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime updatedAt;
    @Column(name = "gec_id")
    protected Long gecId;
    @Column(name = "emp_id")
    protected Long empId;


    @PrePersist//Antes de Registrar
    public void beforeSave() {
        this.toUpperCase();
        this.createdAt = LocalDateTime.now();
    }
    @PreUpdate//Antes de Actualizar
    public void beforeUpdate() {
        this.toUpperCase();
        this.updatedAt = LocalDateTime.now();
    }
    @PostLoad // Modificar el registro antes de regresarlo
    public void afterLoad(){
        this.capitalizeFields();
    }

    public void toLowerCase() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null) {
                        field.set(this, value.toLowerCase());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void toUpperCase() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType().equals(String.class)) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null) {
                        field.set(this, value.toUpperCase());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public void capitalizeFields() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.getType() == String.class && field.isAnnotationPresent(CapitalizeFirstLetter.class)) {
                try {
                    field.setAccessible(true);
                    String value = (String) field.get(this);
                    if (value != null) {
                        field.set(this, value.substring(0, 1).toUpperCase() + value.substring(1));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
