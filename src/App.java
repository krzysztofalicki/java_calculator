import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.Math.pow;

class Node {

    String operator;
    double number;
    int prior;

    Node() {
        operator = "0";
        number = 0;
        prior = -1;
    }

    Node(double n) {
        operator = "0";
        number = n;
        prior = -1;
    }

    @Override
    public String toString() {
        return "Node: operator = " + operator + " number = " + number + " prior = " + prior + ".";
    }
}

public class App {

    public static void main(String[] args) {

        /////////////// tests ////////////////////////////////////////

        /*
        Node node1 = new Node();
        System.out.println(node1.toString());
        */

        /*
        StringBuilder test1 = new StringBuilder();
        test1.append("2.2");
        double test2 = Double.parseDouble(test1.toString());
        System.out.println(test2);
        */





        //////////////////////// program //////////////////////////////////

        LinkedList<Node> list = new LinkedList<>();

        Scanner ss = new Scanner(System.in);
        System.out.print("Enter the question : ");
        String question = ss.nextLine();

        System.out.printf("Your answer is : " + question);

        conversion(list, question);

        counting(list);

    }


    public static void conversion(LinkedList<Node> list, String question) {

        int howlong = 0;
        howlong = question.length();
        int n = 0;
        int from = 0;
        String tmp = "";

        while (n < howlong)
        {
            from = n;

            while ( (n != howlong -1) &&
                    ( question.substring(n,n+1).equals("0")||
                            question.substring(n,n+1).equals("1")||
                            question.substring(n,n+1).equals("2")||
                            question.substring(n,n+1).equals("3")||
                            question.substring(n,n+1).equals("4")||
                            question.substring(n,n+1).equals("5")||
                            question.substring(n,n+1).equals("6")||
                            question.substring(n,n+1).equals("7")||
                            question.substring(n,n+1).equals("8")||
                            question.substring(n,n+1).equals("9")||
                            question.substring(n,n+1).equals(".") ) )
            {
                n++;
            }

            if (n == howlong -1) {
                n++;
            }

            tmp = question.substring(from,n);
            double value = Double.parseDouble(tmp);
            list.add(new Node(value));

            if (n < howlong)
            {
                list.getLast().operator = question.substring(n,n+1);

                if (question.substring(n,n+1).equals("%"))
                {
                    list.getLast().prior = 0;
                }
                else if (question.substring(n,n+1).equals("+") || question.substring(n,n+1).equals("-"))
                {
                    list.getLast().prior = 1;
                }
                else if (question.substring(n,n+1).equals("*") || question.substring(n,n+1).equals("/"))
                {
                    list.getLast().prior = 2;
                }
                else if (question.substring(n,n+1).equals("^"))
                {
                    list.getLast().prior = 3;
                }

                n++;
            }
        }

    }





    public static void counting(LinkedList<Node> list)
    {
        double result = 0;

        int howmany = list.size();
        howmany--;

        while (howmany > 0)
        {
            int i = list.size();
            i--;

            while (i != 0 && list.get(i-1).prior >= list.get(i).prior && !list.get(i).operator.equals("^"))
            {
                i--;
            }

            if (list.get(i).operator.equals("%"))
                result = list.get(i).number % list.get(i+1).number;
            else if (list.get(i).operator.equals("+"))
                result = list.get(i).number + list.get(i+1).number;
            else if (list.get(i).operator.equals("-"))
                result = list.get(i).number - list.get(i+1).number;
            else if (list.get(i).operator.equals("*"))
                result = list.get(i).number * list.get(i+1).number;
            else if (list.get(i).operator.equals("/"))
                result = list.get(i).number / list.get(i+1).number;
            else if (list.get(i).operator.equals("^"))
                result = pow(list.get(i).number, list.get(i+1).number);

            howmany--;

            list.get(i+1).number = result;

            list.remove(i);
        }

        System.out.printf(" = " + list.get(0).number);;
    }

}

