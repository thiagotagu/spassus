package com.avaliacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.avaliacao.service.RelatorioService;

@Controller
@RequestMapping("relatorio")
public class RelatorioController {
	
	
	@Autowired
	RelatorioService relatorioService;
		
		 @GetMapping(value = "/relatorio", produces = MediaType.APPLICATION_PDF_VALUE)
		    public ResponseEntity<byte[]> gerarRelatorio() {
		        byte[] relatorio = relatorioService.gerarRelatorioLivros();
		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_PDF);
		        headers.setContentDispositionFormData("inline", "relatorio.pdf");
		        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		        return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);
		    }
		    
		    

}
