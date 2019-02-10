package org.softuni.metube.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.NameTokenizer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public final class RequestBodyParser {
    private static ModelMapper modelMapper = initModelMapper();

    private static ModelMapper initModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    public static <T> T parseRequestBody(Map<String, String[]> params, Class<T> clazz) {

        Map<String, String> parsedParams = params
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        (x) -> x.getKey(),
                        (x) -> x.getValue()[0],
                        (x, y) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));

        return modelMapper.map(parsedParams, clazz);
    }
}
