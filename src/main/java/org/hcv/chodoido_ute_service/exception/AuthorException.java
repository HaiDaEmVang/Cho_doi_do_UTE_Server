package org.hcv.chodoido_ute_service.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthorException extends AuthenticationException {
    public AuthorException(String message) {
        super(message);
    }
}
