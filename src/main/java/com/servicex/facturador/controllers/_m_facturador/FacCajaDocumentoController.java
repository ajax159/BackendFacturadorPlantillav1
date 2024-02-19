package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import org.apache.commons.beanutils.BeanUtils;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
//import com.servicex.facturador.config._api.exceptions.NotFoundException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaDocumentoDTO;
import com.servicex.facturador.dtos.FacCajaDocumentoNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaDocumentoModel;
import com.servicex.facturador.services._m_facturador.FacCajaDocumentoService;
import com.servicex.facturador.validators.FacCajaDocumentoValidator;
@RestController
@RequestMapping("/cajadocumento")
public class FacCajaDocumentoController implements ApiResponse{
    @Autowired
    private FacCajaDocumentoService facCajaDocumentoservice;
    @Autowired
    private FacCajaDocumentoValidator facCajaDocumentoValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacCajaDocumentoModel usuarios = facCajaDocumentoservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    @GetMapping("/documento")
    public ResponseEntity<ArrayList<FacCajaDocumentoDTO>> getIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaDocumentoDTO> facdocumentos = facCajaDocumentoservice.getIdCaja(idcaja);
        return ResponseEntity.ok(facdocumentos);
    }
    @GetMapping("/notdocumento")
    public ResponseEntity<ArrayList<FacCajaDocumentoNotDTO>> getnotIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaDocumentoNotDTO> facdocumentos = facCajaDocumentoservice.getnotIdCaja(idcaja);
        return ResponseEntity.ok(facdocumentos);
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacCajaDocumentoModel facCajaDocumentoModel,
            BindingResult result)
            throws NotValidException, SaveException {
        facCajaDocumentoValidator.validate(facCajaDocumentoModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
        FacCajaDocumentoModel newFacCajaDocumento = facCajaDocumentoservice.save(facCajaDocumentoModel);
        return savedSuccessfully(newFacCajaDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
                facCajaDocumentoservice.delete(id);
        return deleteSuccessfully();
    }
}
