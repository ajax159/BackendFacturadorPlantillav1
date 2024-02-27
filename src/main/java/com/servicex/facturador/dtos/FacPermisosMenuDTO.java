package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacPermisosMenuDTO {
    private Long menId;
    private Long modId;
        public FacPermisosMenuDTO(Long menId, Long modId){
            this.menId = menId;
            this.modId = modId;
        }
}
