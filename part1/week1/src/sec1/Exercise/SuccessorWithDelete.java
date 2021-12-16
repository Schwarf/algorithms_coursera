package sec1.Exercise;

// Successor with delete. Given a set of nn integers S = \{ 0, 1, ... , n-1 \}S={0,1,...,n−1} and a
// sequence of requests of the following form:

// Remove x from S
// Find the successor of x: the smallest y in SS such that y ≥ x. (y ge x)
// design a data type so that all operations (except construction) take logarithmic time or better in the worst case
// This title means that there is a continuous sequence starting from 0. Each time, a
// number x is extracted from the sequence, and the number larger than x in the sequence is calculated,
// but the smallest one.

public class SuccessorWithDelete {
    private int N;
    private int[] num;
    private boolean[] isRemove;

    public SuccessorWithDelete(int N) {
        num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = i;
            isRemove[i] = false;
        }
    }

    public int find(int i) {
        while (i != num[i]) {
            i = num[i];
        }
        return i;
    }

    public void remove(int index) {
        if (index - 1 >= 0 && isRemove[index - 1]) {
            union(index, index - 1);
        } else if (index + 1 < N && isRemove[index + 1]) {
            union(index, index + 1);
        }
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) {
            return;
        }
        if (i < j) {
            num[i] = j;
        } else {
            num[j] = i;
        }
    }

    public int getSuccessor(int x) {
        int num = find(x) + 1;
        if (num < N) {
            return num;
        }
        return -1;
    }
}
