package com.boboeye.luandun.module.tuling;

/**
 * Created by libo_591 on 15/9/30.
 */
public class TulingDecoderFactory {
    public static TulingDecoder createDecoder(int code){
        if(code==200000){
            return new LinkDecoder();
        }else if(code==302000){
            return new NewsDecoder();
        }else if(code==305000){
            return new TicketDecoder();
        }else if(code==308000){
            return new CookbookDecoder();
        }
        return new TextDecoder();
    }
}
