import java.io.IOException;

import org.slf4j.Logger;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.client.api.IClientInterceptor;
import ca.uhn.fhir.rest.client.api.IHttpRequest;
import ca.uhn.fhir.rest.client.api.IHttpResponse;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

public class RequestInterceptor implements IClientInterceptor{
	private static final org.slf4j.Logger ourLog = org.slf4j.LoggerFactory.getLogger(RequestInterceptor.class);
	private Logger myLog = ourLog;
	public RequestInterceptor() {
		super();
	}
	
	@Override
	@Hook(Pointcut.CLIENT_REQUEST)
	public void interceptRequest(IHttpRequest theRequest) {
	}
	
	@Override
	@Hook(Pointcut.CLIENT_RESPONSE)
	public void interceptResponse(IHttpResponse theResponse) throws IOException {
		// TODO Auto-generated method stub
		String responseTime = theResponse.getRequestStopWatch().toString();
		myLog.info("Averate response time for each request: {}", responseTime);
		
		
	}

}
