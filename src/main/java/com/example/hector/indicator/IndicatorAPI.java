package com.example.hector.indicator;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/hector/indicators")
@Slf4j
@RequiredArgsConstructor
public class IndicatorAPI{
	
	private final IndicatorService indicatorService;
	
	    @GetMapping
	    public ResponseEntity<List<IndicatorType>>getAll() {
	        return ResponseEntity.ok(indicatorService.findAll());
	    }

	    @PostMapping
	    public ResponseEntity<IndicatorType> create(@Valid @RequestBody IndicatorType indicator){
	        return ResponseEntity.ok(indicatorService.save(indicator));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<IndicatorType> findById(@PathVariable Long id) {
	      
	    	Optional<IndicatorType> indicator = indicatorService.findById(id);
	        if (!indicator.isPresent()) {
	            log.error("Id " + id + " is not existed");
	            ResponseEntity.badRequest().build();
	        }

	        return ResponseEntity.ok(indicator.get());
	        
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<IndicatorType> update(@PathVariable Long id, @Valid @RequestBody IndicatorType indicator) {
	        if (!indicatorService.findById(id).isPresent()) {
	            log.error("Id " + id + " is not existed");
	            ResponseEntity.badRequest().build();
	        }

	        return ResponseEntity.ok(indicatorService.save(indicator));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> delete (@PathVariable Long id) {
	        if (!indicatorService.findById(id).isPresent()) {
	            log.error("Id " + id + " is not existed");
	            ResponseEntity.badRequest().build();
	        }

	        indicatorService.deleteById(id);
	        return ResponseEntity.ok().build();
	    }

}
