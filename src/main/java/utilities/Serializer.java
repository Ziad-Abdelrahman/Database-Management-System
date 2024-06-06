package utilities;

import java.io.*;

public class Serializer {
    public static Object deserialize(String fileName) {
        Object deserializedObject = null;
        try (FileInputStream fileIn = new FileInputStream(fileName + ".ser");
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            deserializedObject = (Object) objectIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return deserializedObject;
    }

    public static void serialize(String folder, Serializable t) {
        try (FileOutputStream fileOut = new FileOutputStream(folder + ".ser");
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
