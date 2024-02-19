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
import com.servicex.facturador.models._m_facturador.FacVentaModel;
import com.servicex.facturador.services._m_facturador.FacVentaService;
// import com.servicex.facturador.validators.FacVentaValidator;
@RestController
@RequestMapping("/venta")
public class FacVentaApiController implements ApiResponse{
    @Autowired
    private FacVentaService facVentaservice;
    // @Autowired
    // private FacVentaValidator facVentaValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facVenta", facVentaservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacVentaModel usuarios = facVentaservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    // private void validateFacVenta(FacVentaModel facVentaModel, BindingResult result) throws NotValidException {
    //     facVentaValidator.validate(facVentaModel, result);
    //     if (result.hasErrors()) {
    //         throw new NotValidException(result.getAllErrors());
    //     }
    // }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacVentaModel facVentaModel, BindingResult result)
            throws NotValidException, SaveException {
                // validateFacVenta(facVentaModel, result);
                FacVentaModel newFacCaja = facVentaservice.save(facVentaModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacVentaModel existingFacVenta = facVentaservice.findOne(id);
        //BindingResult result = new BeanPropertyBindingResult(existingFacVenta, "existingFacVenta");
        try {
            BeanUtils.populate(existingFacVenta, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        // validateFacVenta(existingFacVenta, result); 
        existingFacVenta = facVentaservice.update(id, existingFacVenta);
        return updateSuccessfully(existingFacVenta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facVentaservice.delete(id);
        return deleteSuccessfully();
    }
}
