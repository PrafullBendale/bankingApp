package com.banking.DTO;

import com.banking.entities.Account;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DTOMapper {

    public static Account toAccount(RequestDTO requestDTO){
        Account account = new Account();
        account.setBalance(requestDTO.getBalance());
        account.setAccName(requestDTO.getAccName());

        return account;
    }

    public static List<ResponseDTO> toDTO(List<Account> account){
        List<ResponseDTO> responseDTOS = new ArrayList<>();
        for( Account acct : account){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setId(acct.getId());
            responseDTO.setAccName(acct.getAccName());
            responseDTO.setBalance(acct.getBalance());
            responseDTOS.add(responseDTO);
        }
        return responseDTOS;
    }
    public static ResponseDTO toDTO(Account account){
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setId(account.getId());
            responseDTO.setAccName(account.getAccName());
            responseDTO.setBalance(account.getBalance());
        return responseDTO;
    }
}
