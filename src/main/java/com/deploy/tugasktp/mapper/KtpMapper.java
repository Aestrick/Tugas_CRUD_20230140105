package com.deploy.tugasktp.mapper;

import com.deploy.tugasktp.model.dto.KtpDto;
import com.deploy.tugasktp.model.entity.Ktp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KtpMapper {
    KtpMapper MAPPER = Mappers.getMapper(KtpMapper.class);

    KtpDto toKtpDtoData(Ktp ktp);
}