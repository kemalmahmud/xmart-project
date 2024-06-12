package com.be_springboot_xmart.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDto {
    private Integer transactionId;

    private String qrCode;

    private String rfid;

    private BigDecimal unitPrice;

    private Integer amount;

    private BigDecimal totalPrice;

    private Date transactionDate;
}
