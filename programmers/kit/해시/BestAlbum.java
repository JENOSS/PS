import java.util.*;
import java.util.Map.Entry;

public class BestAlbum {

    public static void main(String[] args) {
        String[] genre = {"classic","pop","classic","classic","pop"};
        int[] plays = {500,600,150,800,2500};
        for(int i : solution(genre,plays))
            System.out.println(i);
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Integer> answerList = new ArrayList<>();

        HashMap<String,Integer> countGenreHM = new HashMap<>();
        HashMap<String,HashMap<Integer,Integer>> genresHM = new HashMap<>();

        for(int i = 0 ; i < plays.length; i++){
            if(countGenreHM.get(genres[i]) != null){
                countGenreHM.put(genres[i],countGenreHM.get(genres[i])+plays[i]);
            }else{
                countGenreHM.put(genres[i],plays[i]);
            }

            if(genresHM.get(genres[i]) != null){
                genresHM.get(genres[i]).put(i,plays[i]);
            }else{
                HashMap<Integer,Integer> play = new HashMap<>();
                play.put(i,plays[i]);
                genresHM.put(genres[i],play);
            }
        }

        List<String> countGenreHMKeySet = new ArrayList<>(countGenreHM.keySet());
        Collections.sort(countGenreHMKeySet, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return countGenreHM.get(o2).compareTo(countGenreHM.get(o1));
            }
        });

        for(String genre : countGenreHMKeySet){
            List<Integer> genreHMKeySet = new ArrayList<>(genresHM.get(genre).keySet());
            Collections.sort(genreHMKeySet, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if(genresHM.get(genre).get(o1) == genresHM.get(genre).get(o2)){
                        return o2-o1;
                    }else{
                        return genresHM.get(genre).get(o2).compareTo(genresHM.get(genre).get(o1));
                    }
                }
            });

            int count = 0;
            for(Integer key : genreHMKeySet){
                    if(count < 2){
                        answerList.add(key);
                        count++;
                    }else{
                        break;
                    }
            }
        }

        int[] answer = new int[answerList.size()];

        for(int k = 0 ; k < answerList.size(); k++) answer[k] = answerList.get(k);

        return answer;
    }

}
