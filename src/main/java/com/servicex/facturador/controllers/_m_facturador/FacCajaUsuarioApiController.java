package com.servicex.facturador.controllers._m_facturador;

import jakarta.validation.Valid;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.servicex.facturador.config._api.ApiResponse;
import com.servicex.facturador.config._api.Configurations;
import com.servicex.facturador.config._api.exceptions.DeleteException;
import com.servicex.facturador.config._api.exceptions.NotValidException;
import com.servicex.facturador.config._api.exceptions.SaveException;
import com.servicex.facturador.dtos.FacCajaUsuarioDTO;
import com.servicex.facturador.dtos.FacCajaUsuarioNotDTO;
import com.servicex.facturador.models._m_facturador.FacCajaUsuarioModel;
import com.servicex.facturador.services._m_facturador.FacCajaUsuarioService;
import com.servicex.facturador.validators.FacCajaUsuarioValidator;

//import com.servicex.facturador.validators.FacCajaValidatorApi;
@RestController
@RequestMapping("/cajausuario")
public class FacCajaUsuarioApiController implements ApiResponse {
    @Autowired
    private FacCajaUsuarioService facCajaUsuarioservice;
    @Autowired
    private FacCajaUsuarioValidator facCajaUsuarioValidator;
    @Autowired
    Configurations configurations;

    // @GetMapping("/predata")
    // public ResponseEntity<Object> getDefault() {
    //     Map<String, Object> response = new HashMap<>();
    //     response.put("facTipoDocumento", facCajaService.findAll());
    //     response.put("role", configurations.getRole());
    //     return showOne(response);
    // }

    @PreAuthorize("hasPermission('1','read')")
    @GetMapping("/listar/{gecId}/{empId}")
    public ResponseEntity<Object> listarPorGecIdEmpIdYEstado(@PathVariable Long gecId, @PathVariable Long empId) {
        FacCajaUsuarioModel usuarios = facCajaUsuarioservice.listarPorGecIdEmpIdYEstado(gecId, empId, 0L);
        return showOne(usuarios);
    }

    @PreAuthorize("hasPermission('1','read')")
    @GetMapping("/usuario")
    public ResponseEntity<ArrayList<FacCajaUsuarioDTO>> getIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaUsuarioDTO> facusuarios = facCajaUsuarioservice.getIdCaja(idcaja);
        return ResponseEntity.ok(facusuarios);
    }

    @PreAuthorize("hasPermission('1','read')")
    @GetMapping("/usuarionot")
    public ResponseEntity<ArrayList<FacCajaUsuarioNotDTO>> getNotIdCaja(@RequestParam("idcaja") Long idcaja){
        ArrayList<FacCajaUsuarioNotDTO> facusuarios = facCajaUsuarioservice.getNotIdCaja(idcaja);
        return ResponseEntity.ok(facusuarios);
    }

    @PreAuthorize("hasPermission('1','read')")
    @GetMapping("/nombre")
    public ResponseEntity<ArrayList<FacCajaUsuarioDTO>> getUsuarioporNombre(@RequestParam("nombre") String nombre){
        ArrayList<FacCajaUsuarioDTO> facusuarios = facCajaUsuarioservice.getUsuarioporNombre(nombre);
        return ResponseEntity.ok(facusuarios);
    }

    @PreAuthorize("hasPermission('1','insert')")
    @PostMapping("/crear")
    public ResponseEntity<Object> createCaja(@Valid @RequestBody FacCajaUsuarioModel facCajaUsuarioModel,
            BindingResult result)
            throws NotValidException, SaveException {
        facCajaUsuarioValidator.validate(facCajaUsuarioModel, result);
        if (result.hasErrors()) {
            throw new NotValidException(result.getAllErrors());
        }
        FacCajaUsuarioModel newFacCajaUsuario = facCajaUsuarioservice.save(facCajaUsuarioModel);
        return savedSuccessfully(newFacCajaUsuario);
    }

    @PreAuthorize("hasPermission('1','delete')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCaja(@PathVariable Long id)
            throws DeleteException {
        facCajaUsuarioservice.delete(id);
        return deleteSuccessfully();
    }

    // @GetMapping("/listar/{id}")
    // public ResponseEntity<Object> buscarPorIdUsuario(@PathVariable Long id) throws NotFoundException {
    //     facCajaUsuarioservice.buscarPorIdUsuario(id);
    //     return showOne(id);
    // }
}
