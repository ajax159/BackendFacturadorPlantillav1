package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacClaseDocumentoModel;
import com.servicex.facturador.services._m_facturador.FacClaseDocumentoService;
import com.servicex.facturador.validators.FacClaseDocumentoValidator;
@RestController
@RequestMapping("/clasedocumento")
public class FacClaseDocumentoApiController implements ApiResponse{
    @Autowired
    private FacClaseDocumentoService facClaseDocumentoservice;
    @Autowired
    private FacClaseDocumentoValidator facClaseDocumentoValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facClaseDocumento", facClaseDocumentoservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacClaseDocumentoModel usuarios = facClaseDocumentoservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacClaseDocumento(FacClaseDocumentoModel facClaseDocumentoModel, BindingResult result) throws NotValidException {
        facClaseDocumentoValidator.validate(facClaseDocumentoModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacClaseDocumentoModel facClaseDocumentoModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacClaseDocumento(facClaseDocumentoModel, result);
                FacClaseDocumentoModel newFacCaja = facClaseDocumentoservice.save(facClaseDocumentoModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacClaseDocumentoModel existingFacClaseDocumento = facClaseDocumentoservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacClaseDocumento, "existingFacClaseDocumento");
        try {
            BeanUtils.populate(existingFacClaseDocumento, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacClaseDocumento(existingFacClaseDocumento, result); 
        existingFacClaseDocumento = facClaseDocumentoservice.update(id, existingFacClaseDocumento);
        return updateSuccessfully(existingFacClaseDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facClaseDocumentoservice.delete(id);
        return deleteSuccessfully();
    }
}
