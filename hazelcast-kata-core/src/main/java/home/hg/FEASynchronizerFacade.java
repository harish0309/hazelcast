package home.hg;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ISemaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by HARISH on 13- 05- 2020
 */
public class FEASynchronizerFacade {

    private static volatile FEASynchronizerFacade feaSynchronizer;

    private HazelcastInstance hazelcastInstance;

    private volatile Semaphore localSemaphore = new Semaphore(3);
    private ISemaphore distributedSemaphore;

    private FEASynchronizerFacade(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        if (hazelcastInstance.getConfig().getCPSubsystemConfig().getCPMemberCount() > 0) {
            this.distributedSemaphore = hazelcastInstance.getCPSubsystem().getSemaphore("distributedSemaphore");
            this.distributedSemaphore.init(3);
        }
    }

    public static FEASynchronizerFacade getInstance(HazelcastInstance hazelcastInstance) {
        if (feaSynchronizer == null) {
            synchronized (FEASynchronizerFacade.class) {
                if (feaSynchronizer == null) {
                    feaSynchronizer = new FEASynchronizerFacade(hazelcastInstance);
                }
            }
        }
        return feaSynchronizer;
    }

    public void acquirePermit() throws InterruptedException {
        if (isCPSubsystemEnabled()) {
            localSemaphore.acquire();
        } else {
            distributedSemaphore.acquire();
        }
    }

    private boolean isCPSubsystemEnabled() {
        return hazelcastInstance.getConfig().getCPSubsystemConfig().getCPMemberCount() == 0;
    }

    public void releasePermit() {
        if (isCPSubsystemEnabled()) {
            localSemaphore.release();
        } else {
            distributedSemaphore.release();
        }
    }
}
