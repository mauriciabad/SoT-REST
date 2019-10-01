import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private Scanner scanner = new Scanner(System.in);

    public Console() {}

    public void write() {
        System.out.println();
    }

    public void write(String message) {
        write(message, true);
    }

    public void write(String message, boolean endWithNewLine) {
        if (endWithNewLine){
            System.out.println(message);
        }else{
            System.out.print(message);
        }
    }

    public int readLimited(ArrayList<Integer> acceptedValues){
        Integer input = (Integer) read(Integer.class);
        if (!acceptedValues.contains(input)){
            System.out.println("Input must be one of this values: "+acceptedValues.toString());
            return readLimited(acceptedValues);
        }
        return input;
    }

    public boolean ask(String message){
        switch (read(message + " (y/n) ").toLowerCase()){
            case "y": case "1": case "true": case "yes": case "sure": case "ok": return true;
            case "n": case "0": case "false": case "no": case "nope": case "cancel": case "quit": case "close": case "exit": return false;
            default: write("Invalid option, try again\n"); return ask(message);
        }
    }

    public String read() {
        return (String) read(String.class, true);
    }

    public String read(String message) {
        write(message,false);
        return read();
    }

    public <T> Object read(String message, Class<T> clazz) {
        write(message,false);
        return read(clazz, true);
    }

    public <T> Object read(Class<T> clazz) {
        return read(clazz, true);
    }
    public <T> Object read(Class<T> clazz, boolean ignoreUntilNewLine) {
        switch (clazz.getSimpleName()) {
            case "Byte":
                if (scanner.hasNextByte()) {
                    Byte input = scanner.nextByte();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "Double":
                if (scanner.hasNextDouble()) {
                    Double input = scanner.nextDouble();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "Float":
                if (scanner.hasNextFloat()) {
                    Float input = scanner.nextFloat();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "Integer":
                if (scanner.hasNextInt()) {
                    Integer input = scanner.nextInt();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "Long":
                if (scanner.hasNextLong()) {
                    Long input = scanner.nextLong();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "Short":
                if (scanner.hasNextShort()) {
                    Short input = scanner.nextShort();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
            case "String":
            default:
                if (scanner.hasNext()) {
                    String input = scanner.next();
                    if (ignoreUntilNewLine) scanner.nextLine();
                    return input;
                }
                break;
        }
        String next = scanner.nextLine();
        if (!next.equals("")) System.out.println("\""+next+"\" is not a valid "+clazz.getSimpleName()+". Enter a valid " + clazz.getSimpleName());
        return read(clazz, ignoreUntilNewLine);
    }

}
