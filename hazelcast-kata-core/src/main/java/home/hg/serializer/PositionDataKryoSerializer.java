package home.hg.serializer;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import home.hg.data.PositionData;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by HARISH on 07- 07- 2019
 */
public class PositionDataKryoSerializer implements StreamSerializer<PositionData> {

    private static final ThreadLocal<Kryo> kryoThreadLocal
            = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(PositionData.class);
        return kryo;
    });

    @Override
    public void write(ObjectDataOutput objectDataOutput, PositionData positionData) throws IOException {
        Kryo kryo = kryoThreadLocal.get();
        System.out.println("Inside Write");

        Output output = new Output((OutputStream) objectDataOutput);
        kryo.writeObject(output, positionData);
        output.flush();

    }

    @Override
    public PositionData read(ObjectDataInput objectDataInput) throws IOException {
        System.out.println("Inside Read");
        InputStream in = (InputStream) objectDataInput;
        Input input = new Input(in);
        Kryo kryo = kryoThreadLocal.get();
        return kryo.readObject(input, PositionData.class);

    }

    @Override
    public int getTypeId() {
        return 1;
    }

    @Override
    public void destroy() {
        // No impl required
    }
}
