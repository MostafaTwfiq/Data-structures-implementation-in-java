
public class Main {

    public static void main(String[] args){
       LinkedList<Integer> list = new LinkedList<>();
       list.addLast(10);
       list.addLast(20);
       list.addLast(30);
       list.addLast(40);
       list.addLast(50);
       list.reverse();

       for (Integer v : list.toArray(new Integer[0]))
           System.out.println(v);

    }

}
