import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
  static Map<String,Integer> teams = new LinkedHashMap<>();

    public void Teams(Map teams,String s1){
        String team1 = null;
        String team2 = null;
        int s4;
        String[] key = new String[teams.size()];
        Integer[] values = new Integer[teams.size()];
        char[] s2 = new char[10];
        char[] s3 = new char[10];
        String[] Teams = s1.split("-");

        // Выигрыш
        for(int i=0;i<teams.size();i++) {
            key[i] = (String) teams.keySet().toArray()[i];
            values[i] = (Integer) teams.values().toArray()[i];
            if (key[i].equals(Teams[0])) {
                if (values[i] != 0) {
                    for (int j = 0; j < teams.size(); j++) {
                        values[j] = (Integer) teams.values().toArray()[j];
                        if (values[j] < values[i] + 3) {
                            if (values[j] == values[i] + 3) {
                                for (int c = 0; c < key[j].length(); c++) {
                                    s2[c] = key[j].toCharArray()[c];
                                    s3[c] = key[i].toCharArray()[c];
                                    if (s2[c] > s3[c]) System.out.print(i);
                                    if (s3[c] > s2[c]) System.out.print(i);
                                }
                            } else System.out.print(i);
                        }
                    }
                }
                if(values[i]==0)System.out.print(i);
            }
        }
            // Ничья
            for(int i=0;i<teams.size();i++) {
                key[i] = (String) teams.keySet().toArray()[i];
                values[i] = (Integer) teams.values().toArray()[i];
                if (key[i].equals(Teams[0])) {
                    for (int j = 0; j < teams.size(); j++) {
                        values[j] = (Integer) teams.values().toArray()[j];
                        if (values[j] < values[i] + 1) {
                            if (values[j] == values[i] + 1) {
                                for (int c = 0; c < key[j].length(); c++) {
                                    s2[c] = key[j].toCharArray()[c];
                                    s3[c] = key[i].toCharArray()[c];
                                    if (s2[c] > s3[c]) System.out.print(" " + i);
                                    if (s3[c] > s2[c]) System.out.print(" " + i);
                                }
                            }
                            else System.out.print(" "+ i);
                        }
                    }
                }
            }
            for(int i=0;i<teams.size();i++){
                key[i] = (String) teams.keySet().toArray()[i];
                values[i] = (Integer) teams.values().toArray()[i];

                if (key[i].equals(Teams[1])) {
                    for (int j = 0; j < teams.size(); j++) {
                        if (values[j] < values[i] + 3) s4 = j;
                        if (values[j] == values[i] + 3) {
                            for (int c = 0; c < key[j].length(); c++) {
                                s2[c] = key[j].toCharArray()[c];
                                s3[c] = key[i].toCharArray()[c];
                                if (s2[c] > s3[c]) s4 = j;
                                if (s3[c] > s2[c]) s4 = j;
                            }
                        }
                    }
                }
                if (key[i].equals(Teams[1])){

                }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        Main main = new Main();
        for(int i=0;i<x;i++){
            teams.put(sc.next(),sc.nextInt());
        }
        String s1 = sc.next();
        main.Teams(teams,s1);
    }
}