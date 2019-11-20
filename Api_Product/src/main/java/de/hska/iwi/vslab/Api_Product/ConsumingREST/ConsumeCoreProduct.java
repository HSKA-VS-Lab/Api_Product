package de.hska.iwi.vslab.Api_Product.ConsumingREST;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ConsumeCoreProduct {

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreProduct.class);

    private String urlCoreProduct = "http://localhost:8081/product";

    RestTemplate restTemplate = new RestTemplate();

    public Product[] getProducts() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getProductURL());
            Product[] products = restTemplate.getForObject(urlBuilder.getProductURL(), Product[].class);
            return products;
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteProduct(int id) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getSlashURL());
            restTemplate.delete(urlBuilder.getSlashURL() + id);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void updateProduct(Product product) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            restTemplate.put(urlBuilder.getBaseUrl(), product);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public void deleteAllProducts() {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            restTemplate.delete(urlBuilder.getBaseUrl());
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public Product getProduct(int id) {
        try {
            UrlBuilder urlBuilder = new UrlBuilder();
            log.info("URL:" + urlBuilder.getBaseUrl());
            return restTemplate.getForObject(urlBuilder.getUrlWithId(id), Product.class);
        } catch (Exception e) {
            System.out.println(e);
            throw e;
        }
    }

    public Product[] findProduct(Optional<String> searchValue, Optional<String> priceMinValue,
            Optional<String> priceMaxValue) {
        Product[] list = restTemplate.getForObject(urlCoreProduct + "/product/find", Product[].class, searchValue,
                priceMinValue, priceMaxValue);
        return list;
    }

}