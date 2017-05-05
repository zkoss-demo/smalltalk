package org.zkoss.support.smalltalk.recaptcha;

import org.zkoss.bind.annotation.*;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.util.Clients;

@ToServerCommand("verify")
public class InvisibleRecaptchaVM extends SelectorComposer<Component> {

	final String SECRET = "6Let8B8UAAAAAEV4SlUGv9AmqeB4-X_O2oAvNdov"; //from reCaptcha
	
	@Command 
	public void verify(@BindingParam("response")String response) throws Exception{
		JSONObject result = RecaptchaVerifier.verifyResponse(SECRET, response);
		if (Boolean.parseBoolean(result.get("success").toString())){
			Clients.showNotification("submitted");
		}else{
			String errorCode = result.get("error-codes").toString();
			//log or show error
			Clients.showNotification("verification error: "+errorCode);
		}
	}

}
