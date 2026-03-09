package com.deploy.tugasktp.service;

import com.deploy.tugasktp.model.dto.KtpAddRequest;
import com.deploy.tugasktp.model.dto.KtpDto;
import java.util.List;

public interface KtpService {
    KtpDto addKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto updateKtp(Integer id, KtpAddRequest request);
    void deleteKtp(Integer id);
}