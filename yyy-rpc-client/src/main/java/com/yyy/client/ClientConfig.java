package com.yyy.client;

import com.yyy.rpc.TransportClient;
import com.yyy.rpc.impl.TransportClientImpl;
import lombok.Data;
import model.Point;
import utils.codec.Decoder;
import utils.codec.Encoder;
import utils.codec.impl.DecoderImpl;
import utils.codec.impl.EncoderImpl;

import java.util.Arrays;
import java.util.List;

@Data
public class ClientConfig {
    private Class<? extends TransportClient> transportClient= TransportClientImpl.class;
    private Class<? extends Encoder> encoder= EncoderImpl.class;
    private Class<? extends Decoder> decoder= DecoderImpl.class;
    private Class<? extends ClientOpe> clientOpe=ClientOpeImpl.class;
    private Point point=new Point("127.0.0.1",6000);
}
