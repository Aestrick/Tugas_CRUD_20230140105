package com.deploy.tugasktp.service.impl;

import com.deploy.tugasktp.mapper.KtpMapper;
import com.deploy.tugasktp.model.dto.KtpAddRequest;
import com.deploy.tugasktp.model.dto.KtpDto;
import com.deploy.tugasktp.model.entity.Ktp;
import com.deploy.tugasktp.repository.KtpRepository;
import com.deploy.tugasktp.service.KtpService;
import com.deploy.tugasktp.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KtpServiceImpl implements KtpService {

    private final KtpRepository ktpRepository;
    private final ValidationUtil validationUtil;

    @Override
    public KtpDto addKtp(KtpAddRequest request) {
        validationUtil.validate(request);

        // Validasi: Error kalau nomor KTP sudah terdaftar
        if (ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Error: Nomor KTP sudah terdaftar!");
        }

        Ktp ktp = Ktp.builder()
                .nomorKtp(request.getNomorKtp())
                .namaLengkap(request.getNamaLengkap())
                .alamat(request.getAlamat())
                .tanggalLahir(request.getTanggalLahir())
                .jenisKelamin(request.getJenisKelamin())
                .build();

        ktpRepository.save(ktp);
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public List<KtpDto> getAllKtp() {
        List<Ktp> ktpList = ktpRepository.findAll();
        // Mengubah list Entity menjadi list DTO
        return ktpList.stream()
                .map(KtpMapper.MAPPER::toKtpDtoData)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        // Validasi: Error kalau ID tidak ditemukan
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Data KTP tidak ditemukan!"));
        return KtpMapper.MAPPER.toKtpDtoData(ktp);
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpAddRequest request) {
        validationUtil.validate(request);

        Ktp existingKtp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Data KTP tidak ditemukan!"));

        // Cek jika nomor KTP diubah dan apakah sudah dipakai orang lain
        if (!existingKtp.getNomorKtp().equals(request.getNomorKtp()) &&
                ktpRepository.existsByNomorKtp(request.getNomorKtp())) {
            throw new RuntimeException("Error: Nomor KTP sudah terdaftar oleh pengguna lain!");
        }

        existingKtp.setNomorKtp(request.getNomorKtp());
        existingKtp.setNamaLengkap(request.getNamaLengkap());
        existingKtp.setAlamat(request.getAlamat());
        existingKtp.setTanggalLahir(request.getTanggalLahir());
        existingKtp.setJenisKelamin(request.getJenisKelamin());

        ktpRepository.save(existingKtp);
        return KtpMapper.MAPPER.toKtpDtoData(existingKtp);
    }

    @Override
    public void deleteKtp(Integer id) {
        Ktp ktp = ktpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: Data KTP tidak ditemukan!"));
        ktpRepository.delete(ktp);
    }
}