package com.deploy.tugasktp.controller;

import com.deploy.tugasktp.model.dto.KtpAddRequest;
import com.deploy.tugasktp.model.dto.KtpDto;
import com.deploy.tugasktp.service.KtpService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ktp")
@AllArgsConstructor
public class KtpController {

    private final KtpService ktpService;

    // POST /ktp : Tambah KTP baru
    @PostMapping
    public ResponseEntity<Map<String, Object>> addKtp(@RequestBody KtpAddRequest request) {
        try {
            KtpDto result = ktpService.addKtp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "success",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // GET /ktp : Ambil semua data
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllKtp() {
        List<KtpDto> result = ktpService.getAllKtp();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "status", "success",
                "data", result
        ));
    }

    // GET /ktp/{id} : Ambil data berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getKtpById(@PathVariable("id") Integer id) {
        try {
            KtpDto result = ktpService.getKtpById(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "success",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // PUT /ktp/{id} : Update data
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateKtp(
            @PathVariable("id") Integer id,
            @RequestBody KtpAddRequest request
    ) {
        try {
            KtpDto result = ktpService.updateKtp(id, request);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "success",
                    "data", result
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }

    // DELETE /ktp/{id} : Hapus data
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteKtp(@PathVariable("id") Integer id) {
        try {
            ktpService.deleteKtp(id);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", "success",
                    "message", "Data KTP dengan ID " + id + " berhasil dihapus"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", "error",
                    "message", e.getMessage()
            ));
        }
    }
}