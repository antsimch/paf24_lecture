package sg.edu.nus.iss.paf24_lecture.exception;

import java.util.Date;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BankAccountNotFoundException.class)
    public ModelAndView handleBankAccountNotFoundException(
            BankAccountNotFoundException ex, 
            HttpServletRequest request
    ) {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(404);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage("Bank Account not found");
        errMsg.setDescription(request.getRequestURI());
        
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);

        return mav;
    }

    @ExceptionHandler(AmountNotSufficientException.class)
    public ModelAndView handleAmountNotSufficientException(
            AmountNotSufficientException ex, 
            HttpServletRequest request
    ) {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(500);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage("Amount is insufficient");
        errMsg.setDescription(request.getRequestURI());
        
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);

        return mav;
    }

    @ExceptionHandler(AccountBlockedAndDisabledException.class)
    public ModelAndView handleAccountBlockedAndDisabledException(
            AccountBlockedAndDisabledException ex, 
            HttpServletRequest request
    ) {
        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(500);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage("Account is blocked and disabled");
        errMsg.setDescription(request.getRequestURI());
        
        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);

        return mav;
    }

    @ExceptionHandler(AccountNotActiveException.class)
    public ModelAndView handleAccountNotActiveException(
        AccountNotActiveException ex,
        HttpServletRequest request
    ) {

        ErrorMessage errMsg = new ErrorMessage();
        errMsg.setStatusCode(500);
        errMsg.setTimeStamp(new Date());
        errMsg.setMessage("Account is not active");
        errMsg.setDescription(request.getRequestURI());

        ModelAndView mav = new ModelAndView("error.html");
        mav.addObject("errorMessage", errMsg);

        return mav;
    }
}
