import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Persistencia {

    public static <s, t> void serializeHashMap(HashMap<s, t> hashMap, String path) { ///o string path
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("com.baeldung.jackson.inheritance")
                .allowIfSubType("java.util.HashMap").build();

        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        try {
            mapper.writeValue(file, hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <t> void serializeArrayList(ArrayList<t> arrayList, String path) { ///o string path
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("com.baeldung.jackson.inheritance")
                .allowIfSubType("java.util.ArrayList").build();

        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        try {
            mapper.writeValue(file, arrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <s, t> HashMap<s, t> DEserializeHashMap(String path, Class<s> elementKey, Class<t> elementValue) { ///ver si anda esto
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HashMap<s, t> ret = new HashMap<>();
        MapType hashtype = mapper.getTypeFactory().constructMapType(HashMap.class, elementKey, elementValue);
        try {
            ret = mapper.readValue(file, hashtype);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static <t> ArrayList<t> DEserializeArrayList(String path, Class<t> element) {
        File file = new File(path);
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ArrayList<t> ret = new ArrayList<>();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, element);
        try {
            ret = mapper.readValue(file, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
