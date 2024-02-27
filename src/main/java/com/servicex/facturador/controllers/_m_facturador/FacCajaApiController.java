package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.beanutils.BeanUtils;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacCajaModel;
import com.servicex.facturador.services._m_facturador.FacCajaService;
import com.servicex.facturador.validators.FacCajaValidatorApi;
@RestController
@RequestMapping("/caja")
public class FacCajaApiController implements ApiResponse {
    @Autowired
    private FacCajaService facCajaservice;
    @Autowired
    private FacCajaValidatorApi facCajaValidatorApi;
    @Autowired
    Configurations configurations;

    
    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facCaja", facCajaservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @PreAuthorize("hasPermission('1','read')")
    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        ArrayList<FacCajaModel> usuarios = facCajaservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacCaja(FacCajaModel facCajaModel, BindingResult result) throws NotValidException {
        facCajaValidatorApi.validate(facCajaModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PreAuthorize("hasPermission('1','insert')")
    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacCajaModel facCajaModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacCaja(facCajaModel, result);
        FacCajaModel newFacCaja = facCajaservice.save(facCajaModel);
        return savedSuccessfully(newFacCaja);
    }

    @PreAuthorize("hasPermission('1','update')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
        FacCajaModel existingFacCaja = facCajaservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacCaja, "existingFacCaja");
        try {
            BeanUtils.populate(existingFacCaja, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacCaja(existingFacCaja, result); 
        existingFacCaja = facCajaservice.update(id, existingFacCaja);
        return updateSuccessfully(existingFacCaja);
    }

    @PreAuthorize("hasPermission('1','delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facCajaservice.delete(id);
        return deleteSuccessfully();
    }
}
