package LessonStreamApi;

import LessonMap.MyMap;
import LessonSet.MyMultiSet;
import LessonSet2.MyNavigableSet;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.stream.Stream;

public class DifferentTasks {

    public void run() {
        //Написать пример использования компаратора в виде лямбда-выражения.
        List<Pen> listPen = getPens();
        listPen.sort((Pen o1, Pen o2)->(int) o1.getLength()- (int) o2.getLength());
        listPen.forEach((pen)->System.out.println(pen));

        //Вывести все элементы листа, которые больше самого большого
        // элемента второго листа (с использованием Stream api)
        List<Integer> integerList1 = getIntegers1();
        List<Integer> integerList2 = getIntegers2();
        Stream stream2 = integerList2.stream();
        Stream stream1 = integerList1.stream();
        int maxi = (int) stream2.max((Object int1, Object int2) -> (int) int1 - (int) int2).get();
        stream1.filter(x -> (int) x > maxi).forEach(System.out::println);


        //Посчитать количество строк в set, в которых количество гласных больше трёх.
        MyNavigableSet<String> myNavigableSet = getStrings();
        Stream setStream = myNavigableSet.stream();
        long ans = 0;
        String vowels = "euioaEUIOA";
       ans = setStream.filter(x -> {
           String tmp = (String) x;
           int count = 0;
           for (int i = 0; i < tmp.length(); i++) {
               for (int j = 0; j < vowels.length(); j++) {
                   if (tmp.charAt(i) == vowels.charAt(j)) {
                       count++;
                       if (count > 3) {
                           return true;
                       }
                       break;
                   }
               }
           }
           return false;
       }).count();
        System.out.println("Num strings " + ans);


        //Сконкотенировать все ключи map.
        Map<String, Integer> myMap = getMaps();
        Stream keys = myMap.keySet().stream();;
        StringBuilder old = new StringBuilder();
        keys.forEach(x -> {
            old.append ((String) x);
        });
        System.out.println(old);

        //Получить сумму длин строк коллекции, которые длиннее 5-ти символов.
        MyNavigableSet<String> myNavigableSet2 = getStrings();
        Stream stringStream = myNavigableSet2.stream();
        int sum = stringStream.filter(x -> ((String) x).length() > 5).mapToInt(x -> ((String) x).length()).sum();
        System.out.println("Sum lengths" + sum);

    }

    private List<Pen> getPens() {
        List<Pen> pens = new ArrayList<Pen>();
        pens.add(new Pen(78, "Erich"));
        pens.add(new Pen(11, "Crause"));
        pens.add(new Pen(32, "Xiaomi"));
        pens.add(new Pen(26, "Mijia"));
        return pens;
    }
    private List<Integer> getIntegers1() {
        List<Integer> pens = new ArrayList<>();
        pens.add(new Integer(50));
        pens.add(new Integer(29));
        pens.add(new Integer(23));
        pens.add(new Integer(81));
        return pens;
    }
    private List<Integer> getIntegers2() {
        List<Integer> pens = new ArrayList<>();
        pens.add(new Integer(40));
        pens.add(new Integer(39));
        pens.add(new Integer(13));
        pens.add(new Integer(11));
        return pens;
    }
    private MyNavigableSet<String> getStrings() {
        MyNavigableSet<String> pens = new MyNavigableSet<>();
        pens.add("Erich");
        pens.add("Crause");
        pens.add("Xiaomi");
        pens.add("Mijia");
        return pens;
    }
    private Map<String, Integer> getMaps() {
        Map<String, Integer> pens = new WeakHashMap<>();
        pens.put("Erich", 78);
        pens.put("Crause", 11);
        pens.put("Xiaomi", 32);
        pens.put("Mijia", 26);
        return pens;
    }
}
