package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaDocumentoDTO {
    private Long cjdId;
    private Long facCajaCajId;
    private Long tpdId;
    private String tpdDescripcion;
    private Long facClasedocumentocldid;

    public FacCajaDocumentoDTO (Long cjdId, Long facCajaCajId, Long tpdId, String tpdDescripcion, Long facClasedocumentocldid){
        this.cjdId = cjdId;
        this.facCajaCajId = facCajaCajId;
        this.tpdId = tpdId;
        this.tpdDescripcion = tpdDescripcion;
        this.facClasedocumentocldid = facClasedocumentocldid;
    }
    
}

