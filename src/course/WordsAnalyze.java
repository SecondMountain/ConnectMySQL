package course;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static course.MyTextFile.*;
import static Print.Print.println;

/**
 * Created by zyf on 2016/3/1.
 */
public class WordsAnalyze {
    /**
     * 种别编码
     * int 1
     * char 2
     * float 3
     * void 4
     * const 5
     * if 6
     * else 7
     * do 8
     * while 9
     * scanf 10
     * printf 11
     * return 12
     * main 13
     * read 14
     *  +   15
     *  -   16
     *  *   17
     *  /   18
     *  %   19
     *  =   20
     *  ==  21
     *  >   22
     *  <   23
     *  !=  24
     *  >=  25
     *  <=  26
     *  &&  27
     *  ||  28
     *  !   29
     *  <>  30
     *  (   31
     *  )   32
     *  {   33
     *  }   34
     *  ;   35
     *  ,   36
     *  "   37
     *  '   38
     *  ++  39
     *  --  40
     *  &   41
     */
    //key words
    public static String[] words = {"int","char","float","void","const","if","else","do","while",
        "scanf","printf","return","main","read"};

    public static void main(String[] args)throws IOException {
        //TODO write your code here
//        InputStreamReader in = new InputStreamReader(new FileInputStream("src/course/WordsAnalyze.java"));
        InputStreamReader in = new InputStreamReader(new ByteArrayInputStream(remove(read("src/words.txt")).getBytes()));
        int number;
        char ch='#';
        boolean readNext = true;
        while (true){
//            readNext = true;
            if (readNext){
                if ((number = in.read())== -1)
                    break;
                ch = (char)number;
            }
            if (!readNext)
                readNext = true;
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
                    String str = "";
                    while ((number = in.read())!= -1){
                        str+=ch;
                        ch = (char)number;
                        if (ch == ' ' || ch == ';'||ch == ','||ch == '('||ch == ')' ||ch == '{' ||ch == '}'||ch == '<' ||ch == '>' ||ch == '"'){
                            readNext =false;
                            break ;
                        }

                    }
                    int lengths = words.length;
                    boolean out = false;
                    for (int i = 0;i<lengths;i++){
                        if (str.compareTo(words[i])==0){
                            switch (i){
                                case 0:println("<int,"+(i+1)+">");out=true;break;
                                case 1:println("<char,"+(i+1)+">");out=true;break;
                                case 2:println("<float,"+(i+1)+">");out=true;break;
                                case 3:println("<void,"+(i+1)+">");out=true;break;
                                case 4:println("<const,"+(i+1)+">");out=true;break;
                                case 5:println("<if,"+(i+1)+">");out=true;break;
                                case 6:println("<else,"+(i+1)+">");out=true;break;
                                case 7:println("<do,"+(i+1)+">");out=true;break;
                                case 8:println("<while,"+(i+1)+">");out=true;break;
                                case 9:println("<scanf,"+(i+1)+">");out=true;break;
                                case 10:println("<printf,"+(i+1)+">");out=true;break;
                                case 11:println("<return,"+(i+1)+">");out=true;break;
                                case 12:println("<main,"+(i+1)+">");out=true;break;
                                case 13:println("<read"+(i+1)+">");out=true;break;
                                default:break;
                            }
                        }
                    }
//                    println("<"+str+",_>");
                    if (!out){
                        println("<"+str+",_>");
                        out=false;
                    }
                    else out=false;

                }
                if (ch >= '0' && ch <= '9') {
                    String str =""+ch;
                    int t = 1;
                    while ((number = in.read())!= -1){
                        ch=(char)number;
                        if (ch >= '0' && ch <= '9')
                        {
                            str+=ch;
                        }
                        else if (ch=='.'){
                            str+=ch;
                            t=2;
                        }
                        else if (ch=='l'||ch=='L'){
                            str+=ch;
                            println("<"+str+",_>");
                            break;
                        }
                        else {
                            println("<"+str+",_>");
                            readNext = false;
                            break;
                        }
                    }
                }
                switch (ch) {
                    case '>':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '=')
                            println("<>=,25>");
                        else {
                            println("<>,22>");
                            readNext = false;
                        }
                    }break;
                    case '<':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '=')
                            println("<<=,26>");
                        else {
                            println("<<,23>");
                            readNext = false;
                        }
                    }break;
                    case '!':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '=')
                            println("<!=,24>");
                        else {
                            readNext = false;
                        }
                    }break;
                    case '=':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '=')
                            println("<==,21>");
                        else {
                            println("<=,20>");
                            readNext = false;
                        }
                    }break;
                    case '+':
                        if ((number = in.read()) != -1) {
                            ch = (char) number;
                            if (ch == '+')
                                println("<++,39>");
                            else {
                                println("<+,15>");
                                readNext = false;
                            }
                        }break;
                    case '-':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '-')
                            println("<--,40>");
                        else {
                            println("<-,16>");
                            readNext = false;
                        }
                    }break;
                    case '&':if ((number = in.read()) != -1) {
                        ch = (char) number;
                        if (ch == '&')
                            println("<&&,27>");
                        else {
                            println("<&,41>");
                            readNext = false;
                        }
                    }break;

                    case '%':
                        println("<%,19>");readNext= true;break;
                    case '*':
                        println("<*,17>");readNext= true;break;

                    case '/':
                        println("</,18>");readNext= true;break;

                    case '(':
                        println("<(,31>");readNext= true;break;

                    case ')':
                        println("<),32>");readNext= true;break;

                    case '{':
                        println("<{,33>");readNext= true;break;

                    case '}':
                        println("<},34>");readNext= true;break;

                    case ',':
                        println("<,,36>");readNext= true;break;

                    case ';':
                        println("<;,35>");readNext= true;break;

                    case '"':println("<\",37>");readNext= true;break;

                    case '\'':println("<',38>");readNext= true;break;
                    case  ' ':readNext= true;break;
                    case  '#':println("<#,_>");readNext= true;break;
                    default:readNext= true;break;
                }

        }
        println("---END---");
    }
}
