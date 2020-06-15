package home.hg.listeners;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MembershipAdapter;
import com.hazelcast.core.MembershipEvent;

/**
 * Created by HARISH on 13- 05- 2020
 */
public class HazelcastMembershipListener extends MembershipAdapter {

    private HazelcastInstance hazelcastInstance;
    private String nodeName;

    public HazelcastMembershipListener(String nodeName, HazelcastInstance hazelcastInstance) {
        this.nodeName = nodeName;
        this.hazelcastInstance = hazelcastInstance;
    }

    public void memberAdded(MembershipEvent membershipEvent) {
        System.out.println("Node ::" + nodeName + " added to the cluster");
    }

    public void memberRemoved(MembershipEvent membershipEvent) {
        System.out.println("Node ::" + nodeName + " removed from the cluster");
        if (hazelcastInstance.getConfig().getCPSubsystemConfig().getCPMemberCount() < 2) {
            System.out.println("Restarting CP subsystem");
            hazelcastInstance.getCPSubsystem().getCPSubsystemManagementService().promoteToCPMember();
        }
    }
}
