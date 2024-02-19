package com.servicex.facturador.config._api;

import org.springframework.http.ResponseEntity;

import java.util.Collection;

public interface ApiResponse {

    public static final int ESTADO_ACTIVO = 1;

    default ResponseEntity<Object> showAll(Collection<?> collection) {
        return  new ResponseWrapper().showAll(collection);
    }

    default ResponseEntity<Object> showOne(Object object) {
        return new ResponseWrapper().showOne(object);
    }

    default ResponseEntity<Object> savedSuccessfully() {
        return new ResponseWrapper().savedSuccessfully();
    }

    default ResponseEntity<Object> savedSuccessfully(Object object) {
        return new ResponseWrapper().savedSuccessfully(object);
    }

    default ResponseEntity<Object> updateSuccessfully(Object object) {
        return new ResponseWrapper().updateSuccessfully(object);
    }



    default ResponseEntity<Object> deleteSuccessfully() {
        return new ResponseWrapper().deleteSuccessfully();
    }




}
