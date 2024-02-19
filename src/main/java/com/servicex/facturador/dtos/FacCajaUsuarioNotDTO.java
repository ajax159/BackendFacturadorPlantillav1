package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaUsuarioNotDTO {
    private Long usuId;
    private String usuNombrecompleto;

    public FacCajaUsuarioNotDTO (Long usuId, String usuNombrecompleto){
        this.usuId = usuId;
        this.usuNombrecompleto = usuNombrecompleto;
    }
}
