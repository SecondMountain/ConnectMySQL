package course;

import java.io.IOException;

/**
 * Created by zyf on 2016/4/13.
 */
public class student {
    public static final String strings ="{\"employees\": [{\"firstName\":\"Bill\" , \"lastName\":\"Gates\" },{\"firstName\":\"George\" ,\"lastName\":\"Bush\" },{\"firstName\":\"Thomas\" ,\"lastName\":\"Carter\"} ]}";

    public static void  main(String[] args)throws IOException{
//        JSONObject jsonObject = new JSONObject(strings);
//        JSONArray jsonArray = new JSONArray(jsonObject.get("employees").toString());
//        JSONObject item = jsonArray.getJSONObject(1);
//
//        System.out.print(item.get("firstName").toString());
        new JavaSocket().client();
    }
}
