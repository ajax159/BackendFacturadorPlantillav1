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
import com.servicex.facturador.models._m_facturador.FacClienteModel;
import com.servicex.facturador.services._m_facturador.FacClienteService;
import com.servicex.facturador.validators.FacClienteValidator;
@RestController
@RequestMapping("/cliente")
public class FacClienteApiController implements ApiResponse{
    @Autowired
    private FacClienteService facClienteservice;
    @Autowired
    private FacClienteValidator facClienteValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facCliente", facClienteservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacClienteModel usuarios = facClienteservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacCliente(FacClienteModel facClienteModel, BindingResult result) throws NotValidException {
        facClienteValidator.validate(facClienteModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacClienteModel facClienteModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacCliente(facClienteModel, result);
                FacClienteModel newFacCaja = facClienteservice.save(facClienteModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacClienteModel existingFacCliente = facClienteservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacCliente, "existingFacCliente");
        try {
            BeanUtils.populate(existingFacCliente, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacCliente(existingFacCliente, result); 
        existingFacCliente = facClienteservice.update(id, existingFacCliente);
        return updateSuccessfully(existingFacCliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facClienteservice.delete(id);
        return deleteSuccessfully();
    }
}
