package home.hg.task;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import home.hg.FEASynchronizerFacade;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by HARISH on 12- 05- 2020
 */
public class TestTask implements Runnable, Serializable, HazelcastInstanceAware {
    private static final long serialVersionUID = -884127281433316074L;

    private String nodeName;
    private transient HazelcastInstance hazelcastInstance;

    public TestTask(String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public void run() {
        try {
            FEASynchronizerFacade.getInstance(hazelcastInstance).acquirePermit();
            System.out.println(Calendar.getInstance().getTime().toString() + "  " + nodeName + "  " + hazelcastInstance.getName());
            Thread.sleep(100);
            System.out.println(Calendar.getInstance().getTime().toString() + "  " + "Waking Up ::" + nodeName + "  " + hazelcastInstance.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FEASynchronizerFacade.getInstance(hazelcastInstance).releasePermit();
        }
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }
}
