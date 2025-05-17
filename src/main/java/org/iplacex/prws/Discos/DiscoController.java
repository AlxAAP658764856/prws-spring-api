package org.iplacex.prws.Discos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin
@RequestMapping("/api")

public class DiscoController {

    @Autowired
    private IDiscoRepository discorepo;

    @PostMapping(
    value = "/disco",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
    )

    public ResponseEntity<Disco> HandleInsertDiscoRequest(@RequestBody Disco disco){
        Disco temp = discorepo.insert(disco);
        return new ResponseEntity<>(temp, null, HttpStatus.CREATED);
    }

    @GetMapping(
    value = "/disco/{id}",   
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Disco>_HandleGetDiscoRequest(@PathVariable("id") String id){
    Optional<Disco> temp = discorepo.findById(id);

    if(!temp.isPresent()){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(temp.get(), null, HttpStatus.OK);
}

@PutMapping(
    value = "/disco/{id}",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
)
public ResponseEntity<Disco> HandleUpdateDiscoRequest(
    @PathVariable("id") String id,
    @RequestBody Disco disco
){
    if(!discorepo.existsById(id)){
        return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
    }

    disco._id = id;
    Disco temp = discorepo.save(disco);

    return new ResponseEntity<>(temp, null, HttpStatus.OK);
}
                                   
@DeleteMapping(value = "/disco/{id}")
public ResponseEntity<Disco> HandleDeleteDiscoRequest(@PathVariable("id") String id){
    if(!discorepo.existsById(id)){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    Disco temp = discorepo.findById(id).get();
    discorepo.deleteById(id);

    return new ResponseEntity<>(temp, null, HttpStatus.OK);
}

}
