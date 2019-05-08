package xihe.ji.pet_springboot.utils;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *  敏感词过滤 工具类   -- 【匹配度高，可以使用】
 *  《高效精准》敏感字&词过滤：http://blog.csdn.net/hubiao_0618/article/details/45076871
 * @author hubiao
 * @version 0.1
 * @CreateDate 2015年4月16日 15:28:32
 */
public class SensitiveWord {
    private StringBuilder replaceAll;//初始化
    private String encoding = "UTF-8";
    private String replceStr = "*";
    private int replceSize = 500;
    private String fileName = "CensorWords.txt";
    private List<String> arrayList;
    public Set<String> sensitiveWordSet;//包含的敏感词列表，过滤掉重复项
    public List<String> sensitiveWordList;//包含的敏感词列表，包括重复项，统计次数

    public static  SensitiveWord sw=null;
    /**
     * 文件要求路径在src或resource下，默认文件名为CensorWords.txt
     * @param fileName 词库文件名(含后缀)
     */
    public SensitiveWord(String fileName)
    {
        this.fileName = fileName;
    }

    /**
     * @param replceStr 敏感词被转换的字符
     * @param replceSize 初始转义容量
     */
    public SensitiveWord(String replceStr, int replceSize)
    {
        this.replceStr = fileName;
        this.replceSize = replceSize;
    }

    public SensitiveWord()
    {
    }
    public   static   String StringFilter(String   str)   throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？》《]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return   m.replaceAll("").trim();
    }
    public   static   String StringFilterEn(String   str)   throws PatternSyntaxException {
        // 只允许字母和数字
        // String   regEx  =  "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx="[[0-9][a-z][A-Z]]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return   m.replaceAll("").trim();
    }
    public static String filterMsg(String str){
        str = sw.filterInfo(str);
        return sw.filterInfoEn(str);
    }
    /**
     * @param str 将要被过滤信息  匹配去除特殊字符
     * @return 过滤后的信息
     */
    public String filterInfo(String str)
    {
        String str1=StringFilter(str);
        str1=StringFilterEn(str1);
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList= new ArrayList<String>();
        StringBuilder buffer = new StringBuilder(str1);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        List<String> mgzList = new ArrayList<>();
        for(int x = 0; x < arrayList.size();x++)
        {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for(int start = -1;(start=buffer.indexOf(temp,findIndexSize)) > -1;)
            {
                findIndexSize = start+temp.length();//从已找到的后面开始找
                Integer mapStart = hash.get(start);//起始位置
                if(mapStart == null || (mapStart != null && findIndexSize > mapStart))//满足1个，即可更新map
                {
                    mgzList.add(buffer.substring(start,findIndexSize));
                }
            }
        }
        hash.clear();
        for(String mgz:mgzList){
            /*
        [`~!@#$%^&*()+=|{}':;',\[\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？》《]*?
        */
            List<String>  ss= new ArrayList(Arrays.asList(mgz.split("")));
            String regEx=StringUtils.join(ss,"[【】\\.\\*\\[ \\]\\+\\-\\\\\\^\\|\\{\\}\\$\\(\\)\\w@#%&！‘；" +
                    "：”“’。，、？<>:;\\'\\'》《——+=|{}]*?");
//            System.out.println(regEx);
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while(m.find()) {
                str=str.replace(m.group(),replaceAll.substring(0,m.group().length()));
            }
        }
        return str;
    }

    /**
     * 匹配英文数字
     * @param str
     * @return
     */
    public String filterInfoEn(String str)
    {
        String str1=StringFilter(str);
        sensitiveWordSet = new HashSet<String>();
        sensitiveWordList= new ArrayList<String>();
        StringBuilder buffer = new StringBuilder(str1);
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>(arrayList.size());
        String temp;
        List<String> mgzList = new ArrayList<>();
        for(int x = 0; x < arrayList.size();x++)
        {
            temp = arrayList.get(x);
            int findIndexSize = 0;
            for(int start = -1;(start=buffer.indexOf(temp,findIndexSize)) > -1;)
            {
                findIndexSize = start+temp.length();//从已找到的后面开始找
                Integer mapStart = hash.get(start);//起始位置
                if(mapStart == null || (mapStart != null && findIndexSize > mapStart))//满足1个，即可更新map
                {
                    mgzList.add(buffer.substring(start,findIndexSize));
                }
            }
        }
        hash.clear();
        for(String mgz:mgzList){
            /*
        [`~!@#$%^&*()+=|{}':;',\[\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？》《]*?
        */
            List<String>  ss= new ArrayList(Arrays.asList(mgz.split("")));
            String regEx=StringUtils.join(ss,"[【】\\.\\*\\[ \\]\\+\\-\\\\\\^\\|\\{\\}\\$\\(\\)\\d@#%&！‘；" +
                    "：”“’。，、？<>:;\\'\\'》《——+=|{}]*?");
//            System.out.println(regEx);
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            while(m.find()) {
                str=str.replace(m.group(),replaceAll.substring(0,m.group().length()));
            }
        }
        return str;
    }
    static {
        sw = new SensitiveWord();
        sw.InitializationWork();
    }
    /**
     *   初始化敏感词库
     */
    public void InitializationWork()
    {
        replaceAll = new StringBuilder(replceSize);
        for(int x=0;x < replceSize;x++)
        {
            replaceAll.append(replceStr);
        }
        //加载词库
        arrayList = new ArrayList<String>();
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        try {
            read = new InputStreamReader(SensitiveWord.class.getClassLoader().getResourceAsStream(fileName),encoding);
            bufferedReader = new BufferedReader(read);
            for(String txt = null;(txt = bufferedReader.readLine()) != null;){
                if(!arrayList.contains(txt)) {
                    arrayList.add(txt);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                if(null != bufferedReader) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(null != read) {
                    read.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public StringBuilder getReplaceAll() {
        return replaceAll;
    }
    public void setReplaceAll(StringBuilder replaceAll) {
        this.replaceAll = replaceAll;
    }
    public String getReplceStr() {
        return replceStr;
    }
    public void setReplceStr(String replceStr) {
        this.replceStr = replceStr;
    }
    public int getReplceSize() {
        return replceSize;
    }
    public void setReplceSize(int replceSize) {
        this.replceSize = replceSize;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public List<String> getArrayList() {
        return arrayList;
    }
    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }
    public String getEncoding() {
        return encoding;
    }
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static void main(String[] args){
        long startNumer = System.currentTimeMillis();
//	    System.out.println("敏感词的数量：" + arrayList.size());
        String str = "太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "关, 人, 流, 电, 发, 情, 太, 限, 法，轮，功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。" +
                "脑残";
//        System.out.println(str);
        str="冰毒,海洛因，艳情小说";
        System.out.println("被检测字符串长度:"+str.length());
        str = SensitiveWord.filterMsg(str);
        long endNumber = System.currentTimeMillis();
//	    System.out.println("语句中包含敏感词的个数为：" + sensitiveWordSet.size() + "。包含：" + sensitiveWordSet);
//	    System.out.println("语句中包含敏感词的个数为：" + sensitiveWordList.size() + "。包含：" + sensitiveWordList);
        System.out.println("总共耗时:"+(endNumber-startNumer)+"ms");
        System.out.println("替换后的字符串为:\n"+str);
    }
}  

