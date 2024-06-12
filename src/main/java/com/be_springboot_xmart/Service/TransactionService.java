package com.be_springboot_xmart.Service;

import com.be_springboot_xmart.Dto.TransactionRequestDto;
import com.be_springboot_xmart.Dto.TransactionResponseDto;
import com.be_springboot_xmart.Model.Transaction;
import com.be_springboot_xmart.Repository.TransactionRepository;
import com.be_springboot_xmart.Exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionResponseDto findById(Integer id) {
        var res = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found."));

        return TransactionResponseDto.builder()
                .qrCode(res.getQrCode()).rfid(res.getRfid())
                .unitPrice(res.getUnitPrice()).amount(res.getAmount()).totalPrice(res.getTotalPrice())
                .transactionDate(res.getTransactionDate()).build();
    }

    public List<TransactionResponseDto> findAll() {
        var tranList =  transactionRepository.findAll();
        var res = new ArrayList<TransactionResponseDto>();

        tranList.forEach(transaction -> {
            TransactionResponseDto tran = new TransactionResponseDto();
            tran.setQrCode(transaction.getQrCode());
            tran.setRfid(transaction.getRfid());
            tran.setUnitPrice(transaction.getUnitPrice());
            tran.setAmount(transaction.getAmount());
            tran.setTotalPrice(transaction.getTotalPrice());
            tran.setTransactionDate(transaction.getTransactionDate());
            res.add(tran);
        });

        return res;
    }

    public TransactionResponseDto create(TransactionRequestDto transaction) {
        Transaction tran = new Transaction();
        tran.setQrCode(transaction.getQrCode());
        tran.setRfid(transaction.getRfid());
        tran.setUnitPrice(transaction.getUnitPrice());
        tran.setAmount(transaction.getAmount());
        tran.setTotalPrice(transaction.getTotalPrice());
        tran.setTransactionDate(transaction.getTransactionDate());
        var res = transactionRepository.save(tran);

        return TransactionResponseDto.builder()
                .qrCode(res.getQrCode()).rfid(res.getRfid())
                .unitPrice(res.getUnitPrice()).amount(res.getAmount()).totalPrice(res.getTotalPrice())
                .transactionDate(res.getTransactionDate()).build();
    }

    public TransactionResponseDto edit(TransactionRequestDto transaction) {
        var tran = transactionRepository.findById(transaction.getTransactionId())
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found."));

        tran.setQrCode(transaction.getQrCode());
        tran.setRfid(transaction.getRfid());
        tran.setUnitPrice(transaction.getUnitPrice());
        tran.setAmount(transaction.getAmount());
        tran.setTotalPrice(transaction.getTotalPrice());
        tran.setTransactionDate(transaction.getTransactionDate());

        var res = transactionRepository.save(tran);

        return TransactionResponseDto.builder()
                .qrCode(res.getQrCode()).rfid(res.getRfid())
                .unitPrice(res.getUnitPrice()).amount(res.getAmount()).totalPrice(res.getTotalPrice())
                .transactionDate(res.getTransactionDate()).build();
    }

    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
