/**
 * 
 */
package org.adaikiss.xun.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hlw
 *
 */
public abstract class BaseController {
	@ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMethodArgumentNotValidException(
            MethodArgumentNotValidException error) {
        return "Bad request: " + error.getMessage();
    }
}
