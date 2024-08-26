package com.banking.controller;

import com.banking.DTO.RequestDTO;
import com.banking.DTO.ResponseDTO;

import com.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("accounts/")
public class AccountController  {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>>  getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);

    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO>  getSingleAccount(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getSingleAccount(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO > createAccount(@RequestBody RequestDTO requestDTO) {
        return new ResponseEntity<>(accountService.createAccount(requestDTO),HttpStatus.CREATED);

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO>  deleteAccount(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }

//    @PutMapping("deposite/{id}")
//    public ResponseEntity<ResponseDTO>  deposite(@PathVariable Long id, @RequestBody Map<String,Double> request){
//        return new ResponseEntity<>(accountService.deposite(id,request.get("amount")), HttpStatus.ACCEPTED);
//    }

    @PutMapping("deposite/{id}")
    public ResponseEntity<ResponseDTO>  deposite(@PathVariable Long id, @RequestParam double amount){
        return new ResponseEntity<>(accountService.deposite(id,amount), HttpStatus.ACCEPTED);
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<ResponseDTO> withdraw (@PathVariable Long id, @RequestParam double amount){
        return new ResponseEntity<>(accountService.withDraw(id, amount), HttpStatus.OK);
    }

}
