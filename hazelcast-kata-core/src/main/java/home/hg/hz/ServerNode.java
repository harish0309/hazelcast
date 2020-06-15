package home.hg.hz;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by HARISH on 07- 07- 2019
 */
public class ServerNode {


    public static void main(String[] args) {
        String nodeId = args[0];
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        System.out.println("Launching instance :: " + nodeId);
        //hazelcastInstance.getCluster().addMembershipListener(new HazelcastMembershipListener(nodeId, hazelcastInstance));
        //  hazelcastInstance.getCPSubsystem().getCPSubsystemManagementService().promoteToCPMember();

//
//        IExecutorService executorService = hazelcastInstance.getExecutorService("exec");
//
//        IntStream.range(0, 2).forEach(value -> executorService.submit(new TestTask(nodeId + "-" + value)));


//        executorService.submitToAllMembers(new EchoTask(), new MultiExecutionCallback() {
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
