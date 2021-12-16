package sec1.Exercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Social network connectivity. Given a social network containing n members and a log file containing m timestamps
// at which times pairs of members formed friendships, design an algorithm to determine the earliest time at
// which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend).
// Assume that the log file is sorted by timestamp and that friendship is an equivalence relation.
// The running time of your algorithm should be m \log n or better and use extra space proportional to nn.

// Note: these interview questions are ungraded and purely for your own enrichment. To get a hint, submit a solution.

//  This problem is based on comparison. There is a data stored in log file, which is time p q, meaning
//  that at some point P and Q are connected. When the question is asked,
//  everyone has a connection, that is, Unicom's component is 1.

public class SocialNetworkConnectivity {
    private int[] memberId;
    private int[] numberOfFriends;
    private int numberOfSingles;
    FileInputStream fileInputStream;

    public SocialNetworkConnectivity(int numberOfMembers, FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
        memberId = new int[numberOfMembers];
        numberOfFriends = new int[numberOfMembers];
        numberOfSingles = numberOfMembers;
        for (int initialValue = 0; initialValue < numberOfMembers; initialValue++) {
            memberId[initialValue] = initialValue;
        }
        for (int initialSize = 0; initialSize < numberOfMembers; initialSize++) {
            numberOfFriends[initialSize] = 1;
        }


    }

    public String timeWhenAllMembersInNetwork() {
        Scanner scanner = new Scanner(fileInputStream, "utf-8");
        String time = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line != null && !line.trim().equals("")) {
                String[] lineArray = line.split(" ");
                Integer member1 = Integer.parseInt(lineArray[1]);
                Integer member2 = Integer.parseInt(lineArray[2]);
                makeFriends(member1, member2);
                if (numberOfSingles == 1) {
                    return lineArray[0];
                }
            }
        }
        return "";
    }

    public int findRootMember(int member) {
        while (memberId[member] != member) {
            member = memberId[member];
        }
        return member;
    }

    public void makeFriends(int member1, int member2) {
        int rootIdMember1 = findRootMember(member1);
        int rootIdMember2 = findRootMember(member2);
        if (rootIdMember1 == rootIdMember2) {
            return;
        }
        if (numberOfFriends[member1] < numberOfFriends[member2]) {
            numberOfFriends[member2] += numberOfFriends[member1];
            memberId[member1] = member2;
        } else {
            numberOfFriends[member1] += numberOfFriends[member2];
            memberId[member2] = member1;
        }
        numberOfSingles--;
    }

    public static void main(String[] args) {
        FileInputStream fileInputStream;
        try {
            String path = "/media/linux_data/projects/java/algorithms_coursera/part1/week1/src/sec1/Exercise/socialNetworkLog.txt";
            fileInputStream = new FileInputStream(path);
            SocialNetworkConnectivity socialNetworkConnectivity = new SocialNetworkConnectivity(10, fileInputStream);
            System.out.println(socialNetworkConnectivity.timeWhenAllMembersInNetwork());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
