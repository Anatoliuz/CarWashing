package com.interview.carwash.config;

import com.interview.carwash.dto.WashingDto;
import com.interview.carwash.model.Operation;
import com.interview.carwash.model.OperationPrice;
import com.interview.carwash.model.Washing;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.stream.Collectors;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

//@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper mapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);
        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.addConverter(opConverter());
        modelMapper.addConverter(washingConverter());
        return modelMapper;
    }

    public Converter<Operation, String> opConverter() {
        return new AbstractConverter<Operation, String>() {
            @Override
            protected String convert(Operation source) {
                if (source == null) {
                    return null;
                }

                return source.getName();
            }
        };
    }

    public Converter<Washing, WashingDto> washingConverter() {
        return new AbstractConverter<Washing, WashingDto>() {
            @Override
            protected WashingDto convert(Washing washing) {
                if (washing == null) {
                    return null;
                }
                WashingDto dto = new WashingDto();
                int price = 0;
                for (OperationPrice op : washing.getOperationsPrices()) {
                    price += op.getPrice();
                }
                dto.setPrice(price);
                dto.setId(washing.getId());
                dto.setOperations(
                        washing.getOperationsPrices()
                                .stream()
                                .map(OperationPrice::getOperation)
                                .map(Operation::getName)
                                .collect(Collectors.toList())
                );

                return dto;
            }
        };
    }


}
