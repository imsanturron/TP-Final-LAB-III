package facundo.gt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Persistencia {

    public static <s, t> void serializeHashMap(HashMap<s, t> hashMap, String path) { ///o string path
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <t> void serializeArrayList(ArrayList<t> arrayList, String path) { ///o string path
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(file, arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <s, t> HashMap<s, t> DEserializeHashMap(String path) { ///ver si anda esto
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<s, t> ret = null;
        try {
            ret = mapper.readValue(file, new TypeReference<HashMap<s, t>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static <t> ArrayList<t> DEserializeArrayList(String path) {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<t> ret = new ArrayList<>();
        try {
            ret = mapper.readValue(file, new TypeReference<ArrayList<t>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
