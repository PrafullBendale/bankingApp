package com.banking.service.implementaion;

import com.banking.DTO.DTOMapper;
import com.banking.DTO.RequestDTO;
import com.banking.DTO.ResponseDTO;
import com.banking.entities.Account;
import com.banking.repositories.AccountRepo;
import com.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepo accountRepo;
    private final DTOMapper dtoMapper;

    public AccountServiceImpl(AccountRepo accountRepo, DTOMapper dtoMapper) {
        this.accountRepo = accountRepo;
        this.dtoMapper = dtoMapper;
    }

    @Override
    public List<ResponseDTO> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        List<ResponseDTO> responseDTOS = dtoMapper.toDTO(accounts);
        return responseDTOS;
    }

    @Override
    public ResponseDTO getSingleAccount(Long id){
        Account account = accountRepo.findById(id).orElseThrow(()->new RuntimeException("Account not found.."));
        ResponseDTO responseDTO = dtoMapper.toDTO(account);
        return responseDTO;
    }

    @Override
    public ResponseDTO createAccount(RequestDTO requestDTO) {
        Account account = accountRepo.save(dtoMapper.toAccount(requestDTO));
        ResponseDTO responseDTO = dtoMapper.toDTO(account);
        return responseDTO;
    }

    @Override
    public ResponseDTO deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        ResponseDTO responseDTO = dtoMapper.toDTO(account);
        accountRepo.delete(account);
        return responseDTO;
    }

    @Override
    public ResponseDTO deposite(Long id, double amount) {
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()+amount);
        Account account1 = accountRepo.save(account);
        ResponseDTO responseDTO = dtoMapper.toDTO(account1);

        return responseDTO;
    }

    @Override
    public ResponseDTO withDraw(Long id, double amount){
        Account account = accountRepo.findById(id).orElseThrow(()-> new RuntimeException("Account not found"));
        double availableBalance = account.getBalance();
        if(amount<=availableBalance){
            account.setBalance(availableBalance-amount);
            Account acct = accountRepo.save(account);
            ResponseDTO responseDTO  = dtoMapper.toDTO(acct);
            return responseDTO;
        }
        else{
            System.out.println("Balance is insufficient");
            throw new RuntimeException("Balance is insufficient.. ");
        }
    }
}
