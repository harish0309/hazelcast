package home.hg.task;

/**
 * Created by HARISH on 14- 07- 2019
 */

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;

import java.io.Serializable;

/**
 * Created by HARISH on 14- 07- 2019
 */
public class EchoTask implements Runnable, Serializable, HazelcastInstanceAware {

    private static final long serialVersionUID = 8170266612201112666L;
    private transient HazelcastInstance hazelcastInstance;

    public void setHazelcastInstance(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Executing Distributed Task");
        System.out.println(this.hazelcastInstance.getCluster().getLocalMember().getUuid());
    }
}

