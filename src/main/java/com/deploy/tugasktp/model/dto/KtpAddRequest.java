package com.deploy.tugasktp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KtpAddRequest {
    @NotBlank
    private String nomorKtp;

    @NotBlank
    private String namaLengkap;

    @NotBlank
    private String alamat;

    @NotNull
    private LocalDate tanggalLahir;

    @NotBlank
    private String jenisKelamin;
}