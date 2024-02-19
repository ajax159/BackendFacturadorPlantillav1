package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaSerieNotDTO {
    private Long serId;
    private String serSerie;
        public FacCajaSerieNotDTO(Long serId, String serSerie){
            this.serId = serId;
            this.serSerie = serSerie;
        }
}
