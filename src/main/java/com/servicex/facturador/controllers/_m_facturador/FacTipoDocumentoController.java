package com.servicex.facturador.controllers._m_facturador;
import jakarta.validation.Valid;
import java.util.*;
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
import com.servicex.facturador.models._m_facturador.FacTipoDocumentoModel;
import com.servicex.facturador.services._m_facturador.FacTipoDocumentoService;
import com.servicex.facturador.validators.FacTipoDocumentoValidator;
@RestController
@RequestMapping("/tipodocumento")
public class FacTipoDocumentoController implements ApiResponse{
    @Autowired
    private FacTipoDocumentoService facTipoDocumentoservice;
    @Autowired
    private FacTipoDocumentoValidator facTipoDocumentoValidator;
    @Autowired
    Configurations configurations;

    @GetMapping("/listar")
    public ResponseEntity<Object> getDefault() {
        Map<String, Object> response = new HashMap<>();
        response.put("facTipoDocumento", facTipoDocumentoservice.findAll());
        response.put("role", configurations.getRole());
        return showOne(response);
    }

    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        ArrayList<FacTipoDocumentoModel> usuarios = facTipoDocumentoservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    private void validateFacTipoDocumento(FacTipoDocumentoModel facTipoDocumentoModel, BindingResult result) throws NotValidException {
        facTipoDocumentoValidator.validate(facTipoDocumentoModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacTipoDocumentoModel facTipoDocumentoModel, BindingResult result)
            throws NotValidException, SaveException {
                validateFacTipoDocumento(facTipoDocumentoModel, result);
                FacTipoDocumentoModel newFacCaja = facTipoDocumentoservice.save(facTipoDocumentoModel);
        return savedSuccessfully(newFacCaja);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCaja(@PathVariable Long id, @RequestBody Map<String, Object> updates)
            throws NotValidException, SaveException {
                FacTipoDocumentoModel existingFacTipoDocumento = facTipoDocumentoservice.findOne(id);
        BindingResult result = new BeanPropertyBindingResult(existingFacTipoDocumento, "existingFacTipoDocumento");
        try {
            BeanUtils.populate(existingFacTipoDocumento, updates);
        } catch (Exception e) {
            throw new SaveException(e);
        }
        validateFacTipoDocumento(existingFacTipoDocumento, result); 
        existingFacTipoDocumento = facTipoDocumentoservice.update(id, existingFacTipoDocumento);
        return updateSuccessfully(existingFacTipoDocumento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facTipoDocumentoservice.delete(id);
        return deleteSuccessfully();
    }
}
