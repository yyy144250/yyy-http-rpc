import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import utils.codec.Decoder;
import utils.codec.Encoder;
import utils.codec.impl.DecoderImpl;
import utils.codec.impl.EncoderImpl;

@Slf4j
public class TestMain {

    @Test
    public void encode(){
        TestClass testClass=new TestClass();
        Encoder encoder=new EncoderImpl();
        Decoder decoder=new DecoderImpl();
        testClass.setAge(11);
        byte[] bytes=encoder.encode(testClass);
        log.info(String.valueOf(bytes));

        TestClass testClass1=decoder.decode(bytes,TestClass.class);
        System.out.println(testClass1.getAge());
    }

}
