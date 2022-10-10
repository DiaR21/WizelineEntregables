package com.wizeline.maven.learningjavamaven.controller;

import com.sun.tools.javac.util.List;
import com.wizeline.maven.learningjavamaven.model.BankAccountDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;

public class BankingAccountController {





    @DeleteMapping("/deleteAccounts")
    public ResponseEntity<String> deleteAccounts() {
        bankAccountService.deleteAccounts();
        return new ResponseEntity<>("All accounts deleted", HttpStatus.OK);
    }
    @GetMapping("/getAccountByUser")
    public ResponseEntity<List<BankAccountDTO>> getAccountByUser(@RequestParam String user) {
        LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
        Instant inicioDeEjecucion = Instant.now();
        LOGGER.info("LearningJava - Procesando peticion HTTP de tipo GET");
        List<BankAccountDTO> accounts = bankAccountService.getAccountByUser(user);

        Instant finalDeEjecucion = Instant.now();

        LOGGER.info("LearningJava - Cerrando recursos ...");
        String total = new String(String.valueOf(Duration.between(inicioDeEjecucion, finalDeEjecucion).toMillis()).concat(" segundos."));
        LOGGER.info("Tiempo de respuesta: ".concat(total));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(accounts, responseHeaders, HttpStatus.OK);
    }



}
