package com.yyy.server;

import com.yyy.rpc.TransportServer;
import com.yyy.rpc.impl.TransportServerImpl;
import lombok.Data;
import utils.codec.Decoder;
import utils.codec.Encoder;
import utils.codec.impl.DecoderImpl;
import utils.codec.impl.EncoderImpl;

@Data
public class ServiceConfig {
    public Class<? extends TransportServer> transportClass=TransportServerImpl.class;

    public Class<? extends Encoder> encoder= EncoderImpl.class;

    public Class<? extends Decoder> Decoder= DecoderImpl.class;

    public int port=6000;
}
