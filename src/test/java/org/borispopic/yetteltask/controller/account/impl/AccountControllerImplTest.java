package org.borispopic.yetteltask.controller.account.impl;

import org.borispopic.yetteltask.dto.AccountDTO;
import org.borispopic.yetteltask.mapper.AccountAndAccountDTOMapper;
import org.borispopic.yetteltask.model.domain.Account;
import org.borispopic.yetteltask.service.account.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountControllerImpl.class)
class AccountControllerImplTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private AccountAndAccountDTOMapper accountAndAccountDTOMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    void createAccount() throws Exception {
        AccountDTO testAccountDTO = AccountDTO.builder()
                .id(23)
                .ownerId(15)
                .balance(BigDecimal.valueOf(567.32))
                .build();

        Account testAccount = Account.builder()
                .id(23)
                .ownerId(15)
                .balance(BigDecimal.valueOf(567.32))
                .build();

        when(accountAndAccountDTOMapper.toAccount(any(AccountDTO.class))).thenReturn(testAccount);
        when(accountAndAccountDTOMapper.toAccountDTO(any(Account.class))).thenReturn(testAccountDTO);
        when(accountService.createAccount(any(Account.class))).thenReturn(Optional.of(testAccount));

        mvc.perform(
                        post("/api/v1/account/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":23,\"ownerId\":15,\"balance\":567.32}")
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAccount() throws Exception {

        AccountDTO testAccountDTO = AccountDTO.builder()
                .id(23)
                .ownerId(15)
                .balance(BigDecimal.valueOf(567.32))
                .build();

        Account testAccount = Account.builder()
                .id(23)
                .ownerId(15)
                .balance(BigDecimal.valueOf(567.32))
                .build();

        when(accountService.getAccount(anyLong())).thenReturn(Optional.of(testAccount));
        when(accountAndAccountDTOMapper.toAccountDTO(any(Account.class))).thenReturn(testAccountDTO);

        mvc.perform(get("/api/v1/account/get/23")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}