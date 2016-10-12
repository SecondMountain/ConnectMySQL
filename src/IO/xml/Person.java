package IO.xml;

import nu.xom.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static Print.Print.print;

/**
 * Created by zyf on 2016/3/3.
 */
public class Person {
    private String first,last;
    public Person(String first,String last){
        this.first = first;
        this.last = last;
    }
    public Element getXML(){
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }
    public Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }
    public String toString(){
        return first + " " + last;
    }
    public static void format(OutputStream os, Document doc)throws Exception{
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }
    public static void main(String[] args)throws Exception {
        Person person = new Person("zhao","yafeng");
        Element root = new Element("people");
        root.appendChild(person.getXML());
        Document doc = new Document(root);
        format( new BufferedOutputStream(
                new FileOutputStream("E:\\people.xml"))
                ,doc);
        format (System.out,doc);

        //读出
        File file = new File("E:\\people.xml");
//        String filename = "E:\\people.xml"; 使用字符穿表达文件不可以使用，可以传递一个文件类
        Document docs = new Builder().build(file);
        Elements roots = docs.getRootElement().getChildElements();
        print(new Person(roots.get(0)).toString());
    }

}
