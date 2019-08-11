//package com.example.shopstore.Control;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.shopstore.view.LoginPane;
//import com.example.shopstore.view.Register;
//
//import cn.smssdk.EventHandler;
//import cn.smssdk.SMSSDK;
//
//public class SMSRegister {
//
//    public static EventHandler eventHandler;
//
//    public SMSRegister(){
//        SMSSDK.setAskPermisionOnReadContact(true);
//        eventHandler=new EventHandler(){
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                Message msg=new Message();
//                msg.arg1=event;
//                msg.arg2=result;
//                msg.obj=data;
//                new Handler(Looper.getMainLooper(), new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message msg) {
//                        int event=msg.arg1;
//                        int result=msg.arg2;
//                        Object data=msg.obj;
//                        if (event==SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                            if (result==SMSSDK.RESULT_COMPLETE){
//                                //TODO 处理成功得到验证码的结果
//                                //此处完成了发送验证码的请求，验证码短信未必送到
//                                Log.i("SMSRegister","验证码已发送");
//                            }else{
//                                //TODO 处理错误的结果
//                                ((Throwable)data).printStackTrace();
//                            }
//                        }else if (event==SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
//                            if (result==SMSSDK.RESULT_COMPLETE){
//                                //TODO 处理验证码验证通过的结果
//
//                            }else{
//                                //TODO 处理错误的结果
//                                ((Throwable)data).printStackTrace();
//                            }
//                        }
//                        //TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
//                        return false;
//                    }
//                }).sendMessage(msg);
//            }
//        };
//    }
//
//}
