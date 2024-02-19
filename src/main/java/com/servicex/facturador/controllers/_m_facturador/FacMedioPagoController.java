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
import com.servicex.facturador.models._m_facturador.FacMedioPagoModel;
import com.servicex.facturador.services._m_facturador.FacMedioPagoService;
import com.servicex.facturador.validators.FacMedioPagoValidator;
@RestController
@RequestMapping("/mediopago")
public class FacMedioPagoController implements ApiResponse{
    @Autowired
    private FacMedioPagoService facMedioPagoservice;
    @Autowired
    private FacMedioPagoValidator facMedioPagoValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facMedioPago", facMedioPagoservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacMedioPagoModel usuarios = facMedioPagoservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacMedioPago(FacMedioPagoModel facMedioPagoModel, BindingResult result) throws NotValidException {
        facMedioPagoValidator.validate(facMedioPagoModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacMedioPagoModel facMedioPagoModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacMedioPago(facMedioPagoModel, result);
                FacMedioPagoModel newFacCaja = facMedioPagoservice.save(facMedioPagoModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacMedioPagoModel existingFacMedioPago = facMedioPagoservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacMedioPago, "existingFacMedioPago");
        try {
            BeanUtils.populate(existingFacMedioPago, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacMedioPago(existingFacMedioPago, result); 
        existingFacMedioPago = facMedioPagoservice.update(id, existingFacMedioPago);
        return updateSuccessfully(existingFacMedioPago);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facMedioPagoservice.delete(id);
        return deleteSuccessfully();
    }
}
