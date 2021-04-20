package pl.wsb.apps;
import java.io.IOException;
import java.text.NumberFormat;


public class ProcessorsApp {
    public static void main(String[] args) throws IOException {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(NumberFormat.getInstance().format(Runtime.getRuntime().maxMemory()));
        System.out.println(NumberFormat.getInstance().format(Runtime.getRuntime().freeMemory()));
        System.out.println(NumberFormat.getInstance().format(Runtime.getRuntime().totalMemory()));
    }
}
