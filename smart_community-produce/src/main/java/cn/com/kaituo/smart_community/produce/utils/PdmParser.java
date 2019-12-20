package cn.com.kaituo.smart_community.produce.utils;

import lombok.extern.log4j.Log4j2;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Licensed to Homeii LTD. under the terms of the Homeii 
 * Software License version 1.0.

 * See the NOTICE file distributed with this work for additional 
 * information regarding copyright ownership.  
 * ----------------------------------------------------------------------------
 * Date             Author               Version        Comments
 * 2019年9月17日        Congcong cc.         1.0            Initial Version

 */

@Log4j2
//TODO: 应该继承自XmlParser(装饰模式)
public class PdmParser {
    protected Element element;

    public PdmParser(String path){
        readPdm(path);
    }

    public PdmParser(InputStream in){
        readPdm(in);
        log.debug("PDM加载完成！");
    }

    public PdmParser(Element element){
        this.element = element;
    }

    /**
     * 读取pdm  初始化document对象
     * @param path pdm地址
     */
    private void readPdm(String path){
        if(path == null || path.isEmpty())
            throw new RuntimeException("path is invalid:"+path);
        File f = new File(path);
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(f);
            element = doc.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取pdm  初始化document对象
     * @param fis pdm文件流
     */
    private void readPdm(InputStream fis){
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(fis);
            element = doc.getRootElement();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性值
     * @param attrName 属性名称
     * @return
     */
    public String attr(String attrName){
        return this.element.attributeValue(attrName);
    }
    /**
     * 获取内容
     * @return
     */
    public String getText(){
        return this.element.getText();
    }
    /**
     * 获取去除前后空格的内容
     * @return
     */
    public String getTextTrim(){
        return this.element.getTextTrim();
    }

    /**
     * 全文查找
     * @param elementName
     * @return
     */
    public List<PdmParser> find(String elementName){
        List<PdmParser> rtn = new ArrayList<PdmParser>();
        @SuppressWarnings("rawtypes")
        Iterator elements =element.selectNodes(elementName).iterator();

        while (elements.hasNext()) {
            rtn.add(new PdmParser((Element)elements.next()));
        }

        if(rtn.isEmpty())
            return null;
        return rtn;
    }

    /**
     * 获取子节点中第一个与节点名匹配的节点
     * @param elementName
     * @return
     */
    public PdmParser child(String elementName){
        if(element != null && element.element(elementName) != null)
            return new PdmParser(element.element(elementName));
        return null;
    };

    /**
     * 获取上级节点
     * @return
     */
    public PdmParser getParent(){
        return new PdmParser(element.getParent());
    }

    /**
     * 根据名称获取上级节点
     * @param nodeName
     * @return
     */
    public PdmParser getParentByNodeName(String nodeName){
        for(Element e = element.getParent();e!=null;e=e.getParent()){
            if(e.getQName().getName().equals(nodeName.trim())){
                return new PdmParser(e);
            }
        }
        return null;
    }

    public Element toElement(){
        return element;
    }
    //TODO end

    /**
     * 获取子节点的内容
     * @param nodeName 字节点名称
     * @return
     */
    public String getChildText(String nodeName){
        if(element == null)
            return null;
        return element.elementTextTrim(nodeName);
    }

    public PdmParser getDBMS(){
        List<PdmParser> dbmes = find("//c:DBMS");
        for (PdmParser dbms : dbmes) {
            if(dbms!=null)
                return dbms;
        }
        return null;
    }

    public String getDbmsName(){
        List<PdmParser> dbmeNames = find("//c:DBMS//a:Name");
        for (PdmParser dbmsName : dbmeNames) {
            if(dbmsName!=null)
                return dbmsName.getTextTrim();
        }
        return "";
    }

    public List<PdmParser> getRoot(){
        return   find("//c:Packages");
    }

    public List<PdmParser> getAllPackages(){
        return find("//c:Packages//o:Package");
    }

    public List<PdmParser> getPackages(){
        return find("c:Packages//o:Package");
    }

    public List<PdmParser> getAllTables(){
        return find("//c:Tables//o:Table");
    }

    public List<PdmParser> getTables(){
        return find("c:Tables//o:Table");
    }

    public List<PdmParser> getAllColumns(){
        return find("//c:Columns//o:Column");
    }

    public List<PdmParser> getColumns(){
        return find("c:Columns//o:Column");
    }

    public List<PdmParser> getAllKeys(){
        return find("//c:Keys//o:Key");
    }

    public List<PdmParser> getKeys(){
        return find("c:Keys//o:Key");
    }

    public List<PdmParser> getAllShortcut(){
        return find("//c:Tables//o:Shortcut");
    }

    public List<PdmParser> getShortcut(){
        return find("c:Tables//o:Shortcut");
    }

    public List<PdmParser> getAllRefs(){
        return find("//c:References//o:Reference");
    }

    public List<PdmParser> getRefs(){
        return find("c:References//o:Reference");
    }

    public String getId(){
        String id = getChildText("Id");
        if(id == null)
            id = attr("Id");
        return id;
    }

    public String getName(){
        return getChildText("Name");
    }

    public String getCode(){
        return getChildText("Code");
    }

    public String getComment(){
        return getChildText("Comment");
    }

    public String getModificationDate(){
        return getChildText("ModificationDate");
    }

    //PDM中预定义的实体类型为 ENTITY
    public String getPredefineType(){
        return getChildText("BaseTable.Type");
    }

    public String getObjectId(){
        return getChildText("ObjectID");
    }

    public String getDataType(){
        return getChildText("DataType");
    }

    public String getLength() { return getChildText("Length"); }

    public boolean isNotNull(){
        String mandatory = getChildText("Mandatory");
        return (mandatory !=null && mandatory.trim().equals("1"))?true:false;
    }

    public String getTargetId(){
        return getChildText("TargetID");
    }

    public String getListValue(){
        return getChildText("ListOfValues");
    }
}