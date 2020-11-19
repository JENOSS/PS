import java.util.ArrayList;
import java.util.Stack;

public class BoxingRanking {

    public static void main(String[] args) {
        int[][] results = {{4,3},{4,2},{3,2},{1,2},{2,5}};
        System.out.println(solution(5,results));
    }

    public static int solution(int n, int[][] results) {
        int answer = 0;

        ArrayList<Integer>[] winnerList= new ArrayList[n+1]; //list[승리자] = {패배자1,패바자2..}
        ArrayList<Integer>[] loserList = new ArrayList[n+1]; //list[패배자] = {승리자1,승리자2..}


        for(int i = 0 ; i <= n ; i++){
            winnerList[i] = new ArrayList<>();
            loserList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < results.length; i++){
            winnerList[results[i][0]].add(results[i][1]);
            loserList[results[i][1]].add(results[i][0]);
        }


        for(int i = 1 ; i <= n; i++){
            if(dfsWinnerAndLoser(winnerList,loserList,n,i) == n) {
                answer++;
            }
        }

        return answer;
    }

    public static int dfsWinnerAndLoser(ArrayList<Integer>[] winnerList, ArrayList<Integer>[] loserList, int n, int startNode){
        boolean[] checked;
        Stack<Integer> stack;
        int cnt = 0;


        //winner 리스트 탐색
        checked = new boolean[n+1];
        stack = new Stack<>();
        stack.push(startNode);

        while(!stack.isEmpty()){
            int currentNode = stack.pop();
            cnt++;

            for(int nextNode : winnerList[currentNode]){
                if(!checked[nextNode]){
                    checked[nextNode] = true;
                    stack.push(nextNode);
                }
            }
        }

        //loser 리스트 탐색
        checked = new boolean[n+1];
        stack = new Stack<>();
        stack.push(startNode);

        while(!stack.isEmpty()){
            int currentNode = stack.pop();
            cnt++;

            for(int nextNode : loserList[currentNode]){
                if(!checked[nextNode]){
                    checked[nextNode] = true;
                    stack.push(nextNode);
                }
            }
        }

        return cnt-1; //loser 리스트 돌 때 startNode 값을 하나 더 더했음
    }


}
