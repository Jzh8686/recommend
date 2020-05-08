package com.gyj.gx;

import com.gyj.gx.dao.ItemInfoMapper;
import com.gyj.gx.domain.ItemInfoEntity;
import com.gyj.gx.domain.UserDataEntity;
import com.gyj.gx.domain.UserEntity;
import com.gyj.gx.service.ItemInfoService;
import com.gyj.gx.service.UserDataService;
import com.gyj.gx.service.UserService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GxApplicationTests {
    @Autowired
    UserDataService userDataService;
    @Autowired
    UserService userService;
    @Autowired
    ItemInfoMapper itemInfoMapper;
    @Test
    void contextLoads() {
    }
    @Test
    public void insertTUserData(){
        for (long i = 1; i <=1682; i++) {
            int v = (int)(Math.random()*25+1);
            itemInfoMapper.updateCover("pic"+v,i);
        }
    }
    @Test
    public void testMahout() throws IOException, TasteException {
        Map<String,Integer> dateMap = new HashMap<>();
        dateMap.put("Jan",1);
        dateMap.put("Feb",2);
        dateMap.put("Mar",3);
        dateMap.put("Apr",4);
        dateMap.put("May",5);
        dateMap.put("Jun",6);
        dateMap.put("Jul",7);
        dateMap.put("Aug",8);
        dateMap.put("Sep",9);
        dateMap.put("Oct",10);
        dateMap.put("Nov",11);
        dateMap.put("Dec",12);
        try {
            BufferedReader in = new BufferedReader(new FileReader("F:\\毕设\\文件\\ml-100k\\ml-100k\\u.item"));
            String str=null;//定义一个字符串类型变量str
            while ((str = in.readLine())!= null) {//readLine()方法, 用于读取一行,只要读取内容不为空就一直执行
                String[] split = str.split("\\|\\|");
                String[] baseInfo = split[0].split("\\|");
                String url = split[1];
                Long itemId = Long.parseLong(baseInfo[0]);
                int index = baseInfo[1].lastIndexOf("(");
                String moviceName ="";
                Date date = null;
                if (index==-1){
                    moviceName = baseInfo[1];
                }else {
                    moviceName = baseInfo[1].substring(0,index-1);
                    String[] dateString = baseInfo[2].split("-");
                    LocalDate localDate = LocalDate.of(Integer.parseInt(dateString[2]),dateMap.get(dateString[1]),Integer.parseInt(dateString[0]));
                    LocalDate localDate1 = localDate.plusDays(1);
                    date = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
                itemInfoMapper.insert(new ItemInfoEntity(itemId,moviceName,date,url));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
