package com.deploy.tugasktp.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "KTP")
public class Ktp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Int, Auto Increment

    @Column(unique = true, nullable = false)
    private String nomorKtp; // VARCHAR, Unique

    @Column(name = "nama_lengkap")
    private String namaLengkap; // VARCHAR

    @Column(name = "alamat")
    private String alamat; // VARCHAR

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir; // DATE

    @Column(name = "jenis_kelamin")
    private String jenisKelamin; // VARCHAR
}