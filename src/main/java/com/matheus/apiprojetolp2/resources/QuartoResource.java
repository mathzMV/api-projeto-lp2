package com.matheus.apiprojetolp2.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.matheus.apiprojetolp2.domain.Quarto;
import com.matheus.apiprojetolp2.services.QuartoService;

@RestController
@RequestMapping(value = "/quartos")
public class QuartoResource {

	@Autowired
	private QuartoService quartoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Quarto>> findAll() {
		List<Quarto> quartos = quartoService.findAll();
		return ResponseEntity.ok().body(quartos);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Quarto> findById(@PathVariable String id) {
		Quarto quarto = quartoService.findById(id);
		return ResponseEntity.ok().body(quarto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Quarto obj) {
		Quarto quarto = new Quarto(obj.getId(), obj.getCusto(), obj.getStatus(), obj.getTipoCategoria());
		quarto = quartoService.insert(quarto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(quarto.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Quarto obj, @PathVariable String id) {
		Quarto newObj = new Quarto(id, obj.getCusto(), obj.getStatus(), obj.getTipoCategoria());
		newObj = quartoService.update(newObj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {
		quartoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}