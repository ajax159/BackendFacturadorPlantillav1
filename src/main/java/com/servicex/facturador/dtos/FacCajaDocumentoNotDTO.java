package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaDocumentoNotDTO {
    private Long tpdId;
    private String tpdDescripcion;

    public FacCajaDocumentoNotDTO (Long tpdId, String tpdDescripcion){
        this.tpdId = tpdId;
        this.tpdDescripcion = tpdDescripcion;

    }
    
}

