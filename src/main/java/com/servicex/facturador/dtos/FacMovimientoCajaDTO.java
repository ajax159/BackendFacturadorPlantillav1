package com.servicex.facturador.dtos;
import lombok.Data;
import java.time.LocalDate;
@Data
public class FacMovimientoCajaDTO {
    protected Long mcaId;
    LocalDate mcaFechaapertura;
    LocalDate mcaFechacierre;
    protected Long empId;
    private String cajDescripcion;
    private String usuNombrecompleto;
    private Long mcaMoneda;
    protected Long glbEstadoEstId;
    private String moneda;
    private String estado;

    public FacMovimientoCajaDTO (Long mcaId, LocalDate mcaFechaapertura,LocalDate mcaFechacierre,Long empId,String cajDescripcion,String usuNombrecompleto,Long mcaMoneda,Long glbEstadoEstId,String moneda,String estado){
        this.mcaId = mcaId;
        this.mcaFechaapertura = mcaFechaapertura;
        this.mcaFechacierre = mcaFechacierre;
        this.empId = empId;
        this.cajDescripcion = cajDescripcion;
        this.usuNombrecompleto = usuNombrecompleto;
        this.mcaMoneda = mcaMoneda;
        this.glbEstadoEstId = glbEstadoEstId;
        this.moneda = moneda;
        this.estado = estado;
    }
}
