import java.io.BufferedWriter;
import java.io.FileWriter;

public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            stringBuilder.append("i");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ha"));
            writer.write(stringBuilder.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
