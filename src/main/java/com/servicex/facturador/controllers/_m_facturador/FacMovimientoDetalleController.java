package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.models._m_facturador.FacMovimientoDetalleModel;
import com.servicex.facturador.services._m_facturador.FacMovimientoDetalleService;
//import com.servicex.facturador.validators.FacMovimientoDetalleValidator;
@RestController
@RequestMapping("/movimientodetalle")
public class FacMovimientoDetalleController implements ApiResponse{
    @Autowired
    private FacMovimientoDetalleService facMovimientoDetalleservice;
    // @Autowired
    // private FacMovimientoDetalleValidator facMovimientoDetalleValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facMovimientoDetalle", facMovimientoDetalleservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacMovimientoDetalleModel usuarios = facMovimientoDetalleservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    // private void validateFacMovimientoDetalle(FacMovimientoDetalleModel facMovimientoDetalleModel, BindingResult result) throws NotValidException {
    //     facMovimientoDetalleValidator.validate(facMovimientoDetalleModel, result);
    //     if (result.hasErrors()) {
    //         throw new NotValidException(result.getAllErrors());
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacMovimientoDetalleModel facMovimientoDetalleModel, BindingResult result)
            throws NotValidException, SaveException {
                //validateFacMovimientoDetalle(facMovimientoDetalleModel, result);
                FacMovimientoDetalleModel newFacCaja = facMovimientoDetalleservice.save(facMovimientoDetalleModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacMovimientoDetalleModel existingFacMovimientoDetalle = facMovimientoDetalleservice.findOne(id);
        //BindingResult result = new BeanPropertyBindingResult(existingFacMovimientoDetalle, "existingFacMovimientoDetalle");
        try {
            BeanUtils.populate(existingFacMovimientoDetalle, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        //validateFacMovimientoDetalle(existingFacMovimientoDetalle, result); 
        existingFacMovimientoDetalle = facMovimientoDetalleservice.update(id, existingFacMovimientoDetalle);
        return updateSuccessfully(existingFacMovimientoDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facMovimientoDetalleservice.delete(id);
        return deleteSuccessfully();
    }
}
