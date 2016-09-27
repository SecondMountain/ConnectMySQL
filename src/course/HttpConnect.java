package course;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyf on 2016/4/17.
 */
public class HttpConnect {
    private static  String URLs= "http://127.0.0.1:8081";
    private static URL url = null;
    private static URLConnection urlConnection = null;
    private static InputStream in;
    private static OutputStream out;
    private static BufferedReader bufferedReader;
    public static BufferedWriter bufferedWriter;
    private static HttpURLConnection httpURLConnection=null;
    public static boolean login(){
        try {
            URLs+="/test?userid="+ URLEncoder.encode("200900101","UTF-8")+"&password="+URLEncoder.encode("200900101","UTF-8");
            url = new URL(URLs);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            String string="";
            String str= "";
            while ((string = bufferedReader.readLine())!= null){
                str +=string;
            }

            //JSONObject jsonObject = new JSONObject(str);//解析json字符串

            //System.out.println(jsonObject.get("token"));
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    bufferedReader = null;

                }
                if (bufferedWriter != null){
                    bufferedWriter.close();
                    bufferedWriter =null;
                }
                if (httpURLConnection !=null){
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }
    public static void getCourses(String token){
        try {
            //cbb54ef8-0490-11e6-98a7-024200af334a
            URLs+="/static_lessons?token="+ URLEncoder.encode(token,"UTF-8");
            url = new URL(URLs);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(in));
            String string="";
            StringBuilder str=new StringBuilder();
            while ((string = bufferedReader.readLine())!= null){
                str= new StringBuilder(str.toString()+string);
            }



            System.out.println(str.toString());
            JSONObject jsonObject = new JSONObject(str);
            JSONArray jsonArray = new JSONArray(jsonObject.get("courses").toString());

            for (int i = 0; i< jsonArray.length();i++){

            }
        }
        catch (Exception e){

        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    bufferedReader = null;

                }
                if (httpURLConnection !=null){
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void SearchRoom(String room){
        try {
            room =URLEncoder.encode(room,"UTF-8");
            URLs+="/"+room+"/crowdedness";
            url = new URL(URLs);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.connect();
            in = httpURLConnection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(in));
            String string="";
            String str= "";
            while ((string = bufferedReader.readLine())!= null){
                str +=string;
            }
            JSONObject jsonObject = new JSONObject( str);
            Double ddd = jsonObject .getDouble("crowdedness");
            System.out.println(ddd);
        }
        catch (Exception e){

        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    bufferedReader = null;

                }
                if (httpURLConnection !=null){
                    httpURLConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String decodeUnicode(String str) {
                Charset set = Charset.forName("UTF-16");
                Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
                Matcher m = p.matcher( str );
                int start = 0 ;
                int start2 = 0 ;
                StringBuffer sb = new StringBuffer();
                while( m.find( start ) ) {
                        start2 = m.start() ;
                        if( start2 > start ){
                                String seg = str.substring(start, start2) ;
                                sb.append( seg );
                            }
                        String code = m.group( 1 );
                        int i = Integer.valueOf( code , 16 );
                        byte[] bb = new byte[ 4 ] ;
                        bb[ 0 ] = (byte) ((i >> 8) & 0xFF );
                        bb[ 1 ] = (byte) ( i & 0xFF ) ;
                       ByteBuffer b = ByteBuffer.wrap(bb);
                        sb.append( String.valueOf( set.decode(b) ).trim() );
                        start = m.end() ;
                    }
                start2 = str.length() ;
                if( start2 > start ){
                        String seg = str.substring(start, start2) ;
                        sb.append( seg );
                    }
                return sb.toString() ;
            }

    public static void main(String[] args){
//        String newstring = "{  \"courses\": [    {      \"course_id\": \"1400632009001\",\"name\":\"\\u6bdb\\u6cfd\\u4e1c\\u601d\\u60f3\\u548c\\u4e2d\\u56fd\\u7279\\u8272\\u793e\\u4f1a\\u4e3b\\u4e49\\u7406\\u8bba\\u4f53\\u7cfb\\u6982\\u8bba\",\"schedule\": [        {          \"building\": \"5\",          \"campus\": \"\\u9f99\\u5b50\\u6e56\\u6821\\u533a\",          \"class_index\": \"2\",          \"floor\": \"4\",          \"number\": null,          \"position\": \"[\\u9f99]\\u4e94\\u53f7\\u697c5403\",          \"term\": \"1\",          \"week\": \"1\",          \"weekday\": \"2\",          \"year\": \"20102011\"        },        {          \"building\": \"5\",          \"campus\": \"\\u9f99\\u5b50\\u6e56\\u6821\\u533a\",          \"class_index\": \"2\",          \"floor\": \"4\",          \"number\": null,          \"position\": \"[\\u9f99]\\u4e94\\u53f7\\u697c5403\",          \"term\": \"1\",          \"week\": \"2\",          \"weekday\": \"2\",          \"year\": \"20102011\"        }]}]}";
//        JSONObject jsonObject = new JSONObject(newstring);
//        JSONArray jsonArray = new JSONArray(jsonObject.get("courses").toString());
//        JSONArray jsonArray1;
//        JSONObject jsonObject1;
//        for (int i = 0;i<jsonArray.length();i++){
//                JSONObject item = jsonArray.getJSONObject(i);
//                System.out.println(item.getString("course_id"));
//                System.out.println(decodeUnicode(item.getString("name")));
//
//                jsonArray1 = new JSONArray(item.get("schedule").toString());
//            for (int j = 0;j<jsonArray1.length();j++){
//                JSONObject AnotherItem = jsonArray1.getJSONObject(j);
//                System.out.println(AnotherItem.getInt("building"));
//                System.out.println(decodeUnicode(AnotherItem.getString("campus")));
//                System.out.println(AnotherItem.getInt("class_index"));
//                System.out.println(AnotherItem.getInt("floor"));
//                System.out.println(AnotherItem.get("number"));
//                System.out.println(decodeUnicode(AnotherItem.get("position").toString()));
//                System.out.println(AnotherItem.getInt("term"));
//                System.out.println(AnotherItem.getInt("weekday"));
//                System.out.println(AnotherItem.getString("year"));
//            }
//        }

        //System.out.print(decodeUnicode("\\u6bdb\\u6cfd\\u4e1c\\u601d\\u60f3\\u548c\\u4e2d\\u56fd\\u7279\\u8272\\u793e\\u4f1a\\u4e3b\\u4e49\\u7406\\u8bba\\u4f53\\u7cfb\\u6982\\u8bba"));
        login();
    }
}
