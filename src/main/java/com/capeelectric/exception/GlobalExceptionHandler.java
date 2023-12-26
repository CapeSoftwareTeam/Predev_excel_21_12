package com.capeelectric.exception;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capeelectric.util.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({UserException.class})
    public ResponseEntity<ErrorMessage> handleUserException(UserException e){
    		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
    		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ChangePasswordException.class})
    public ResponseEntity<ErrorMessage> handleChangePasswordException(ChangePasswordException e){
    		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
    		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    
   }
    
    @ExceptionHandler({CompanyDetailsException.class})
    public ResponseEntity<ErrorMessage> handleCompanyDetailsException(CompanyDetailsException e) {
        ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "400");
        return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ForgotPasswordException.class})
    public ResponseEntity<ErrorMessage> handleForgotPasswordException(ForgotPasswordException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler({UpdatePasswordException.class})
    public ResponseEntity<ErrorMessage> handleUpdatePasswordException(UpdatePasswordException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "400");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler({InstalReportException.class})
    public ResponseEntity<ErrorMessage> handleInstalReportException(InstalReportException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({SupplyCharacteristicsException.class})
    public ResponseEntity<ErrorMessage> handleSupplyCharacteristicsException(SupplyCharacteristicsException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({InspectionException.class})
    public ResponseEntity<ErrorMessage> handleInspectionException(InspectionException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({DecimalConversionException.class})
    public ResponseEntity<ErrorMessage> handleDecimalConversionException(DecimalConversionException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "400");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({PeriodicTestingException.class})
    public ResponseEntity<ErrorMessage> handlePeriodicTestingException(PeriodicTestingException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({SummaryException.class})
    public ResponseEntity<ErrorMessage> handleSummaryException(SummaryException e){
            ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
            return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler({RegistrationException.class})
    public ResponseEntity<ErrorMessage> handleRegistrationException(RegistrationException e){
    		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
    		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e){
    		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
    		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({ObservationException.class})
    public ResponseEntity<ErrorMessage> handleObservationException(ObservationException e){
    		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
    		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler({PdfException.class})
    public ResponseEntity<ErrorMessage> handlePDFException(ObservationException e){
    	ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
    	return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler({ ClientDetailsException.class })
	public ResponseEntity<ErrorMessage> handleClientDetailsException(ClientDetailsException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler({ FacilityDataException.class })
	public ResponseEntity<ErrorMessage> handleFacilityDataException(FacilityDataException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ PowerEarthingDataException.class })
	public ResponseEntity<ErrorMessage> handlePowerEarthingDataException(PowerEarthingDataException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ElectromagneticCompatabilityException.class })
	public ResponseEntity<ErrorMessage> handleElectromagneticCompatabilityException(
			ElectromagneticCompatabilityException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "404");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ DiagramComponentException.class })
	public ResponseEntity<ErrorMessage> handleDiagramComponentException(DiagramComponentException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ MCBException.class })
	public ResponseEntity<ErrorMessage> handleMCBException(MCBException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ MCCBException.class })
	public ResponseEntity<ErrorMessage> handleMCCBException(MCCBException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ RCBOException.class })
	public ResponseEntity<ErrorMessage> handleRCBOException(RCBOException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ LightException.class })
	public ResponseEntity<ErrorMessage> handleLightException(LightException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ PortableApplianceException.class })
	public ResponseEntity<ErrorMessage> handlePortableApplianceException(PortableApplianceException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ TransformerException.class })
	public ResponseEntity<ErrorMessage> handleTransformerException(TransformerException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler({ ProtectiveEarthConductorsException.class })
	public ResponseEntity<ErrorMessage> handleProtectiveEarthConductorsException(ProtectiveEarthConductorsException e) {
		ErrorMessage exceptionMessage = new ErrorMessage(e.getMessage(), e.getLocalizedMessage(), "406");
		return new ResponseEntity<ErrorMessage>(exceptionMessage, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
	}
}