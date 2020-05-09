package com.gyj.gx.base.component;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class MahoutConfig {
    @Bean
    public DataModel dataModel(){
        File file = new File("G:\\u.data");
        DataModel dataModel = null;
        try {
            dataModel = new FileDataModel(file);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return dataModel;
    }
    @Bean
    public ItemSimilarity itemSimilarity() throws TasteException {
        return new EuclideanDistanceSimilarity(dataModel());
    }
    @Bean
    public Recommender recommender() throws TasteException {
        return new GenericItemBasedRecommender(dataModel(),itemSimilarity());
    }
    @Bean
    public RecommenderEvaluator recommenderEvaluator(){
        return new AverageAbsoluteDifferenceRecommenderEvaluator();
    }
    @Bean
    public RecommenderBuilder recommenderBuilder(){
        return x -> recommender();
    }
    @Bean
    public RecommenderIRStatsEvaluator recommenderIRStatsEvaluator(){
        return new GenericRecommenderIRStatsEvaluator();
    }
}
