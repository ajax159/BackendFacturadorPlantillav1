package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaUsuarioDTO {
    private Long cjuId;
    private Long facCajaCajId;
    private Long facUsuarioUsuId;
    private Long usuId;
    private String usuNombrecompleto;

    public FacCajaUsuarioDTO (Long cjuId, Long facCajaCajId, Long facUsuarioUsuId, Long usuId, String usuNombrecompleto){
        this.cjuId = cjuId;
        this.facCajaCajId = facCajaCajId;
        this.facUsuarioUsuId = facUsuarioUsuId;
        this.usuId = usuId;
        this.usuNombrecompleto = usuNombrecompleto;
    }
}
