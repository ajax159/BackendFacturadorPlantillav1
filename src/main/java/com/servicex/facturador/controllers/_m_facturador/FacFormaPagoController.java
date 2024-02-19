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
import com.servicex.facturador.models._m_facturador.FacFormaPagoModel;
import com.servicex.facturador.services._m_facturador.FacFormaPagoService;
import com.servicex.facturador.validators.FacFormaPagoValidator;
@RestController
@RequestMapping("/formapago")
public class FacFormaPagoController implements ApiResponse{
    @Autowired
    private FacFormaPagoService facFormaPagoservice;
    @Autowired
    private FacFormaPagoValidator facFormaPagoValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facFormaPago", facFormaPagoservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacFormaPagoModel usuarios = facFormaPagoservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacFormaPago(FacFormaPagoModel facFormaPagoModel, BindingResult result) throws NotValidException {
        facFormaPagoValidator.validate(facFormaPagoModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacFormaPagoModel facFormaPagoModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacFormaPago(facFormaPagoModel, result);
                FacFormaPagoModel newFacCaja = facFormaPagoservice.save(facFormaPagoModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacFormaPagoModel existingFacFormaPago = facFormaPagoservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacFormaPago, "existingFacFormaPago");
        try {
            BeanUtils.populate(existingFacFormaPago, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacFormaPago(existingFacFormaPago, result); 
        existingFacFormaPago = facFormaPagoservice.update(id, existingFacFormaPago);
        return updateSuccessfully(existingFacFormaPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facFormaPagoservice.delete(id);
        return deleteSuccessfully();
    }
}
