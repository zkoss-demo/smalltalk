package org.zkoss.support.smalltalk.recaptcha;

import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;

public class RecaptchaComposer extends SelectorComposer<Component> {

	@Wire
	Button submit;
	
	final String SECRET = "6Lcj5AYTAAAAANcaQYWvFkHVSkqR6FsBaCXXw54r"; //from reCaptcha
	
	@Listen("onUserRespond = #recaptcha")
	public void verify(Event event) throws Exception{
		JSONObject result = RecaptchaVerifier.verifyResponse(SECRET, ((JSONObject)event.getData()).get("response").toString());
		if (Boolean.parseBoolean(result.get("success").toString())){
			submit.setDisabled(false);
		}else{
			String errorCode = result.get("error-codes").toString();
			//log or show error
			Clients.showNotification(errorCode);
		}
	}
	
	@Listen("onClick = #submit")
	public void submit(){
		Clients.showNotification("submitted");
	}
}
