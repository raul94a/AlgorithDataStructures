package utils;

public class Runneable implements Runner {

    public void run(Callable callable){

        long millis = System.currentTimeMillis();
        callable.call();
        System.out.println("Ejecutado en: "  + (System.currentTimeMillis() - millis) + " ms.");

    }
}
