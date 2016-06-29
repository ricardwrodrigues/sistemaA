/*
 * Project: visualiza-exames
 * @(#) ExameImageUtil.java	 29/06/2016
 * @(#)br.com.pixeon.visualiza.controller.util
 *
 * Copyright 2016 . All rights reserved.
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package br.com.pixeon.visualiza.controller.util;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by ricardo on 29/06/16.
 */
public class ExameImageUtil {

    public static String generateUri(String baseUrl, Map<String,String> parameters){
        if ("".equals(baseUrl)) return baseUrl;
        return UriComponentsBuilder.fromHttpUrl(baseUrl).queryParams(converter(parameters)).build().toUriString();
    }

    private static MultiValueMap<String, String> converter (Map<String,String> parameters) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        parameters.forEach((k,v)-> params.add(k,v));
        return params;
    }
}
