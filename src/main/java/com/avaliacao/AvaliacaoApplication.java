package com.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.avaliacao.service.CargaMassaTesteFAKE;

@SpringBootApplication
public class AvaliacaoApplication {

	@Autowired
	CargaMassaTesteFAKE carga;
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoApplication.class, args);
	}
 
	  @EventListener(ApplicationReadyEvent.class) public void runAfterStartup() {
	   carga.gerarCarga();
	   
	  }
	 
	

}
