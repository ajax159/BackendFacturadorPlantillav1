package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacMovimientoCajaDTO;
import com.servicex.facturador.models._m_facturador.FacMovimientoCajaModel;
import com.servicex.facturador.services._m_facturador.FacMovimientoCajaService;
import com.servicex.facturador.validators.FacCajaMovimientoValidator;
@RestController
@RequestMapping("/movimientocaja")
public class FacMovimientoCajaController implements ApiResponse{
    @Autowired
    private FacMovimientoCajaService facMovimientoCajaservice;
    @Autowired
    private FacCajaMovimientoValidator facMovimientoCajaValidator;
    @Autowired
    Configurations configurations;

    @PreAuthorize("hasPermission('2','read')")
    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facMovimientoCaja", facMovimientoCajaservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @PreAuthorize("hasPermission('2','read')")
    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacMovimientoCajaModel usuarios = facMovimientoCajaservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    @PreAuthorize("hasPermission('2','read')")
    @GetMapping("/listarmovcaja/{gecId}/{empId}")
    public ResponseEntity<ArrayList<FacMovimientoCajaDTO>> listarMovimientocaja(@PathVariable Long gecId, @PathVariable Long empId, @RequestParam LocalDate fechainicio, @RequestParam LocalDate fechafin) {
        ArrayList<FacMovimientoCajaDTO> facmovimientocaja = facMovimientoCajaservice.listarMovimientocaja(gecId, empId, fechainicio, fechafin);
        return ResponseEntity.ok(facmovimientocaja);
    }
    

    private void validateFacMovimientoCaja(FacMovimientoCajaModel facMovimientoCajaModel, BindingResult result) throws NotValidException {
        facMovimientoCajaValidator.validate(facMovimientoCajaModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PreAuthorize("hasPermission('2','insert')")
    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacMovimientoCajaModel facMovimientoCajaModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacMovimientoCaja(facMovimientoCajaModel, result);
                FacMovimientoCajaModel newFacCaja = facMovimientoCajaservice.save(facMovimientoCajaModel);
        return savedSuccessfully(newFacCaja);
    }

    @PreAuthorize("hasPermission('2','update')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacMovimientoCajaModel existingFacMovimientoCaja = facMovimientoCajaservice.findOne(id);
        //BindingResult result = new BeanPropertyBindingResult(existingFacMovimientoCaja, "existingFacMovimientoCaja");
        try {
            BeanUtils.populate(existingFacMovimientoCaja, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        //validateFacMovimientoCaja(existingFacMovimientoCaja, result); 
        existingFacMovimientoCaja = facMovimientoCajaservice.update(id, existingFacMovimientoCaja);
        return updateSuccessfully(existingFacMovimientoCaja);
    }

    @PreAuthorize("hasPermission('2','delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facMovimientoCajaservice.delete(id);
        return deleteSuccessfully();
    }
}
