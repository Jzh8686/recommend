package com.gyj.gx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gyj.gx.dao.ItemInfoMapper;
import com.gyj.gx.dao.UserDataMapper;
import com.gyj.gx.domain.ItemInfoEntity;
import com.gyj.gx.domain.Recommend;
import com.gyj.gx.domain.UserDataEntity;
import com.gyj.gx.service.ItemInfoService;
import com.gyj.gx.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserDataServiceImpl extends ServiceImpl<UserDataMapper,UserDataEntity> implements UserDataService {
    @Autowired
    UserDataMapper userDataMapper;
    @Autowired
    DataSource dataSource;
    @Autowired
    ItemInfoMapper itemInfoMapper;
    public List<ItemInfoEntity> generateRecommend(Long id)  {
        List<ItemInfoEntity> list = new ArrayList<>();
        try {
            DataModel dataModel = new MySQLJDBCDataModel(dataSource,"userdata","user_id","item_id","preference","timestamp_value");
            UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(10,similarity,dataModel);
            Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood,similarity);
            System.out.println(LocalDateTime.now().toString());
            List<RecommendedItem> recommendedItemList = recommender.recommend(id, 5);
            List<Long> itemIdList = recommendedItemList.stream().map(x -> x.getItemID()).collect(Collectors.toList());
           list = itemInfoMapper.getItemByItemId(itemIdList);
        }catch (Exception e){
            e.getStackTrace();
        }
        return list;
    }
    public List<UserDataEntity> getRatings(Integer id, Integer curPage, Integer size){
        QueryWrapper<UserDataEntity> wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);

        Page<UserDataEntity> page = new Page<UserDataEntity>(curPage,size);
        Page<UserDataEntity> userDataEntityPage = userDataMapper.selectPage(page, wrapper);
        List<UserDataEntity> userData = userDataEntityPage.getRecords();
        return userData;
    }

    public void rateFile(UserDataEntity userDataEntity){
        userDataMapper.insertUserData(userDataEntity);
    }
    public List<Long> getUserIdList(){
        return userDataMapper.getUserIdList();
    }
    public void ratingItem(Long userId,Long itemId,Float preference){
        QueryWrapper<UserDataEntity> wrapper = new QueryWrapper();
        wrapper.eq("user_id", userId);
        wrapper.eq("item_id", itemId);
        UserDataEntity userDataEntity = userDataMapper.selectOne(wrapper);
        if (userDataEntity==null){
            UserDataEntity temp = new UserDataEntity(userId,itemId,preference,Calendar.getInstance().getTimeInMillis());
            userDataMapper.insert(temp);
        }else{
            userDataEntity.setPreference(preference);
            userDataMapper.update(userDataEntity,wrapper);
        }
    }
}
