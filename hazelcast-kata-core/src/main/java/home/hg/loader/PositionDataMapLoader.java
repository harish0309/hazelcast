package home.hg.loader;

import com.hazelcast.core.MapLoader;
import home.hg.data.PositionData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Created by HARISH on 07- 07- 2019
 */
public class PositionDataMapLoader implements MapLoader<Long, PositionData> {
    public PositionData load(Long aLong) {
        return null;
    }

    public Map<Long, PositionData> loadAll(Collection<Long> terms) {
        Map<Long, PositionData> dataMap = new HashMap<>();
        terms.forEach(num -> {
            PositionData data = null;
            if (num % 2 == 0) {
                data = new PositionData(num, "Primary", "PrimSettle", num * 1.2);
            } else {
                data = new PositionData(num, "Secondary", "Adjustment", num * 1.3);
            }
            dataMap.put(num, data);
        });


        return dataMap;
    }

    public Iterable<Long> loadAllKeys() {
        return LongStream.rangeClosed(100, 200)
                .boxed().collect(Collectors.toList());
    }
}
