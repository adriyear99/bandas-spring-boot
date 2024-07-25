package com.springboot.adriano.dto.suporte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordRequest {
    private String senhaAtual;
    private String novaSenha;
    private String confirmacaoSenha;
}

