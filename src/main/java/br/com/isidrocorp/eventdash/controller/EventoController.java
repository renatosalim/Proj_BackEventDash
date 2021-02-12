package br.com.isidrocorp.eventdash.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.eventdash.dao.EventoDAO;
import br.com.isidrocorp.eventdash.model.Evento;

@RestController
@CrossOrigin("*")
public class EventoController {
	
    @Autowired
	private EventoDAO dao;
	
	@GetMapping("/eventos")
	public ArrayList<Evento> listarTudo(){
		ArrayList<Evento> lista;
		lista = (ArrayList<Evento>) dao.findAll();
		return lista;
	}
	
    // http://localhost:8088/buscarpordata?inicio=2020-01-01&fim=2020-01-01
	@GetMapping("/buscarpordata")
	public ArrayList<Evento> buscarPorData(@RequestParam(name="inicio") String dataIni,
		                               @RequestParam(name="fim") String dataFim){
	// preciso converter meus parâmetros (que são strings) para datas
	LocalDate dtIni = LocalDate.parse(dataIni); 
	LocalDate dtFim = LocalDate.parse(dataFim);
	ArrayList<Evento> lista = dao.findAllByDataEventoBetween(dtIni, dtFim);
	return lista;

}
}

