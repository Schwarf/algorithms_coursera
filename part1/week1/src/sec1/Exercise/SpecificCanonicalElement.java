package sec1.Exercise;
// Union-find with specific canonical element. Add a method 𝚏𝚒𝚗𝚍() to the union-find data type so that 𝚏𝚒𝚗𝚍(𝚒)
// returns the largest element in the connected component containing ii. The operations, 𝚞𝚗𝚒𝚘𝚗(), 𝚌𝚘𝚗𝚗𝚎𝚌𝚝𝚎𝚍(),
// and 𝚏𝚒𝚗𝚍() should all take logarithmic time or better.

// For example, if one of the connected components is \{1, 2, 6, 9\}{1,2,6,9},
// then the 𝚏𝚒𝚗𝚍() method should return 99 for each of the four elements in the connected components.

// The meaning of this topic is to find the maximum value of each component.
// If we use quick-find method, it is very simple. The root node of each component is the maximum value.
// If weighted-quick-union is used, a max array needs to be maintained, and the root node of each link
// component keeps the maximum value.

public class SpecificCanonicalElement {
    private int[] id;   //Component id
    private int[] size;   //The magnitude of the component corresponding to each root node
    private int count;  //Component quantity
    private int[] max;

    public SpecificCanonicalElement(int N) {
        count = N;
        id = new int[N];
        size = new int[N];
        max = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
            max[i] = i;
        }

    }

    public int count() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int i) {
        while (id[i] != i) {
            i = id[i];
        }
        return i;
    }

    public void union(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if (pId == qId) {
            return;
        }
        if (size[pId] < size[qId]) {
            id[pId] = qId;
            size[qId] += size[pId];
            max[qId] = Math.min(max[qId], max[pId]);
        } else {
            id[qId] = pId;
            size[pId] += size[qId];
            max[pId] = Math.max(max[pId], max[qId]);
        }
        count--;
    }

    public int getMax(int i) {
        return max[find(i)];
    }
}
