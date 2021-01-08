package br.com.projeto.LDS.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tcc")
@RequiredArgsConstructor
public class TCCController {

    @GetMapping("/{ProfessorId}")
    ResponseEntity<Object> getByIdProfessor(){
        return ResponseEntity.ok("TEste");
    }

}
