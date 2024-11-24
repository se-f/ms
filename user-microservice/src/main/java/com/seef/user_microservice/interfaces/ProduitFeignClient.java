package com.seef.user_microservice.interfaces;

import com.seef.user_microservice.dto.ProduitDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "produit-microservice", url = "http://localhost:8082") public interface ProduitFeignClient {

    @GetMapping("/produits")
    List<ProduitDTO> getProduitsForUser();
}

