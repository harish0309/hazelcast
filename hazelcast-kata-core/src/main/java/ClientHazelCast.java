import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Cluster;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import home.hg.task.TestTask;

import java.util.stream.IntStream;

/**
 * Created by HARISH on 07- 07- 2019
 */
public class ClientHazelCast {


    public static void main(String[] args) {

        ClientConfig config = new ClientConfig();

        HazelcastInstance hzClient
                = HazelcastClient.newHazelcastClient(config);
        Cluster cluster = hzClient.getCluster();


        IExecutorService executorService = hzClient.getExecutorService("exec");

//        ISemaphore distributedSemaphore = hzClient.getCPSubsystem().getSemaphore("distributedSemaphore");
//
//        try {
//            System.out.println("Available Permits :: " + distributedSemaphore.availablePermits());
//            distributedSemaphore.acquire();
//            System.out.println("Lock acquired !!");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//
//            distributedSemaphore.release();
//            System.out.println("Lock released!!");
//        }


        IntStream.range(0, 100_000).forEach(value -> executorService.submit(new TestTask(String.valueOf(value))));

//        executorService.submitToAllMembers(new TestTask(), new MultiExecutionCallback() {
//            public void onResponse(Member member, Object o) {
//                System.out.println("onResponse");
//            }
//
//            public void onComplete(Map<Member, Object> map) {
//                System.out.println("onComplete");
//            }
//        });
    }

}
