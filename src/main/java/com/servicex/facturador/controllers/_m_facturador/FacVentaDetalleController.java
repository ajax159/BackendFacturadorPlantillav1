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
import com.servicex.facturador.models._m_facturador.FacVentaDetalleModel;
import com.servicex.facturador.services._m_facturador.FacVentaDetalleService;
// import com.servicex.facturador.validators.FacVentaDetalleValidator;
@RestController
@RequestMapping("/ventadetalle")
public class FacVentaDetalleController implements ApiResponse{
    @Autowired
    private FacVentaDetalleService facVentaDetalleservice;
    // @Autowired
    // private FacVentaDetalleValidator facVentaDetalleValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facVentaDetalle", facVentaDetalleservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacVentaDetalleModel usuarios = facVentaDetalleservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    // private void validateFacVentaDetalle(FacVentaDetalleModel facVentaDetalleModel, BindingResult result) throws NotValidException {
    //     facVentaDetalleValidator.validate(facVentaDetalleModel, result);
    //     if (result.hasErrors()) {
    //         throw new NotValidException(result.getAllErrors());
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacVentaDetalleModel facVentaDetalleModel, BindingResult result)
            throws NotValidException, SaveException {
                // validateFacVentaDetalle(facVentaDetalleModel, result);
                FacVentaDetalleModel newFacCaja = facVentaDetalleservice.save(facVentaDetalleModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacVentaDetalleModel existingFacVentaDetalle = facVentaDetalleservice.findOne(id);
        //BindingResult result = new BeanPropertyBindingResult(existingFacVentaDetalle, "existingFacVentaDetalle");
        try {
            BeanUtils.populate(existingFacVentaDetalle, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        // validateFacVentaDetalle(existingFacVentaDetalle, result); 
        existingFacVentaDetalle = facVentaDetalleservice.update(id, existingFacVentaDetalle);
        return updateSuccessfully(existingFacVentaDetalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facVentaDetalleservice.delete(id);
        return deleteSuccessfully();
    }
}
