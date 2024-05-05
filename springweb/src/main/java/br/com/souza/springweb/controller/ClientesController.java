package br.com.souza.springweb.controller;

import br.com.souza.springweb.model.UserEntity;
import br.com.souza.springweb.model.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private static final Map<Integer, UserEntity> DATABASE = new HashMap<>();

    static {
        DATABASE.put(1, new UserEntity(1, "Vitor", 22));
        DATABASE.put(2, new UserEntity(2, "Vini", 18));
        DATABASE.put(3, new UserEntity(3, "Lucia", 32));
        DATABASE.put(4, new UserEntity(4, "Maria", 27));
    }

    @GetMapping
    public ResponseEntity<Set<UserEntity>> getAllClientes(){
        Set<UserEntity> usuarios = DATABASE.keySet().stream()
                .map(DATABASE::get)
                .collect(Collectors.toSet());

        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserEntity> getClienteById(@PathVariable("userId") Integer userId) throws Exception{
        UserEntity usuario = DATABASE.get(userId);
        if(usuario == null){
            throw new Exception("Esse usuario nao existe");
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserRequest request){
        Integer id = DATABASE.keySet().stream().max(Integer::compareTo).orElse(1) + 1;

        DATABASE.put(id, new UserEntity(id, request.getNome(), request.getIdade()));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") Integer userId,
                                           @RequestBody UserRequest request) throws Exception{

        UserEntity usuario = DATABASE.get(userId);
        if(usuario == null){
            throw new Exception("Esse usuario nao existe");
        }

        usuario.setNome(request.getNome());
        usuario.setIdade(request.getIdade());

        DATABASE.put(userId, usuario);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Integer userId){
        DATABASE.remove(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
