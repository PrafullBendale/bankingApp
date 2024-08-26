package com.banking.service;


import com.banking.DTO.RequestDTO;
import com.banking.DTO.ResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    List<ResponseDTO> getAllAccounts();
    ResponseDTO getSingleAccount(Long id);
    ResponseDTO createAccount(RequestDTO requestDTO);
    ResponseDTO deleteAccount(Long id);
    ResponseDTO deposite(Long id, double amount);
    ResponseDTO withDraw(Long id, double amount);
}
