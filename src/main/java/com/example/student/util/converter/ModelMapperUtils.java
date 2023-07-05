package com.example.student.util.converter;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {
    private static final ModelMapper modelMapper;
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
    }

    private ModelMapperUtils(){

    }

    public static <D, T> D map(final T entity, Class<D> outClass) {
        return modelMapper.map(entity, outClass);
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> outClass){
        return entityList.stream().map(entity->map(entity,outClass)).collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination){
        modelMapper.map(source, destination);
        return destination;
    }
}
