import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Student student = new Student();
        Field signatureField = Student.class.getDeclaredField("indexNumber");
        signatureField.setAccessible(true);

        String indexNumber = (String) signatureField.get(student);
        System.out.println(indexNumber);
    }
}
