package com.qiyu;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bobol.tipster.BassApplication;
import com.bobol.tipster.R;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFUserInfo;

public class QiYuPlugin extends CordovaPlugin{

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

    if("openQiYu".equals(action)){

      JSONObject data = args.getJSONObject(0);

      YSFUserInfo userInfo = new YSFUserInfo();
      userInfo.userId = data.getString("userId");
      userInfo.data = data.getString("Data");
      Unicorn.setUserInfo(userInfo);
      
      BassApplication myApplication = (BassApplication)this.cordova.getActivity().getApplication();
      myApplication.myYSFOptions.uiCustomization.rightAvatar = data.getString("Avatar");

      // 启动聊天界面
      ConsultSource source = new ConsultSource(data.getString("Url"), data.getString("UrlTitle"), data.getString("UrlDesc"));
      source.productDetail = null;
      Unicorn.openServiceActivity(this.cordova.getActivity().getApplicationContext(), data.getString("Title"), source);

      callbackContext.success("success");
      return true;
    }
    
    return false;
  }

}
