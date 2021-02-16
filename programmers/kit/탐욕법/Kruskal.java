import java.util.Arrays;
import java.util.Comparator;

public class Kruskal {

    public static void main(String[] args){
        int n = 4;
        int[][] costs = {{0,1,5},{1,2,3},{2,3,3},{3,1,2},{3,0,4}};
        System.out.println(solution(n,costs));
    }

    public static int solution(int n, int[][] costs) {
        int answer = 0;
        int count = n -1; // MST의 간선 개수는 n - 1

        int[] root = new int[n];
        for(int i = 0 ; i < n ; i++){
            root[i] = i;
        }

        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[2],o2[2]);
            }
        });


        for(int i  = 0 ; i < costs.length; i++){
            if(count == 0 ) break;

            if(find(root,costs[i][0]) != find(root,costs[i][1])){
                answer+=costs[i][2];
                count--;
                union(root, costs[i][0], costs[i][1]);
            }

        }

        return answer;
    }

    public static int find(int[] root, int n1){
        if(root[n1] != n1) {
            return find(root, root[n1]);
        }else{
            return n1;
        }
    }

    public static void union(int[] root, int n1, int n2){
        n1 = find(root,n1);
        n2 = find(root,n2);

        root[n2] = n1; // 부모 통합
    }

}
