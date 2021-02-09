import sun.misc.Queue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String string = bufferedReader.readLine();

        Parser parser = new Parser();
        Queue<Token> queue = parser.parse(string);
        if (queue == null) {
            System.out.println("ERROR");
        }
        else {
            queue = SortingStation.sorting(queue);

            while (!queue.isEmpty()) {
                System.out.println(Calculator.calculate(queue));
//              System.out.println(queue.dequeue().toString());
            }
        }
    }
}
