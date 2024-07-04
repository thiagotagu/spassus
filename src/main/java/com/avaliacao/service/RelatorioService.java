package com.avaliacao.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public byte[] gerarRelatorioLivros() {
		  try {
		        InputStream jrxmlInput = new ClassPathResource("relatorio/relatorio.xml").getInputStream();
		        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInput);
		        
		    	Resource resource = new ClassPathResource("query/livrosQuery.sql");
				byte[] queryBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
				String sql = new String(queryBytes);
		        JRDataSource dataSource = new JRBeanCollectionDataSource(jdbcTemplate.queryForList(sql));
		        Map<String, Object> params = new HashMap<>();
		        params.put("ReportTitle", "Lista de Livros");

		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		        return JasperExportManager.exportReportToPdf(jasperPrint);

		    } catch (Exception e) {
		        e.printStackTrace();
		        return null;
		    }
    }

}
