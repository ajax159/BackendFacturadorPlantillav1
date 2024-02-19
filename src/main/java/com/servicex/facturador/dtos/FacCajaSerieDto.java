package com.servicex.facturador.dtos;
import lombok.Data;
@Data
public class FacCajaSerieDto {
    private Long cjsId;
    private Long facCajaCajId;
    private Long serId;
    private Long facTipodocumentostpdid;
    private String serSerie;

        public FacCajaSerieDto(Long cjsId, Long facCajaCajId, Long serId, Long facTipodocumentostpdid, String serSerie){
            this.cjsId = cjsId;
            this.facCajaCajId = facCajaCajId;
            this.serId = serId;
            this.facTipodocumentostpdid = facTipodocumentostpdid;
            this.serSerie = serSerie;
        }
}
