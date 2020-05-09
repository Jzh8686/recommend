package com.gyj.gx.controller;
import com.gyj.gx.base.returns.RespCode;
import com.gyj.gx.base.returns.RespEntity;
import com.gyj.gx.service.ItemInfoService;
import com.gyj.gx.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("item")
public class ItemInfoController {
    @Autowired
    ItemInfoService itemInfoService;
    @Autowired
    UserDataService userDataService;
    @GetMapping("latest")
    public RespEntity getLatestItem(){
        return new RespEntity(RespCode.SUCCESS,itemInfoService.getLatestItem());
    }
    @GetMapping("mayLike")
    public RespEntity mayLisk(@RequestParam("userId") Long userId){
        return new RespEntity(RespCode.SUCCESS,userDataService.generateItemRecommend(userId));
    }
    @PostMapping("preference")
    public RespEntity ratingItem(@RequestParam("userId")Long userId,@RequestParam("itemId")Long itemId,@RequestParam("preference")Float preference){
       userDataService.ratingItem(userId,itemId,preference);
        return new RespEntity(RespCode.SUCCESS);
    }
    @GetMapping("movice")
    public RespEntity searchByName(@RequestParam("moviceName")String moviceName,@RequestParam("pageIndex")Integer pageIndex,@RequestParam("size")Integer size){
        return new RespEntity(RespCode.SUCCESS,itemInfoService.getMoviceByName(moviceName,pageIndex,size));
    }
    @GetMapping("moviceYear")
    public RespEntity searchByYear(@RequestParam("startYear")Integer startYear,@RequestParam("endYear")Integer endYear,@RequestParam("pageIndex")Integer pageIndex,@RequestParam("size")Integer size){
        return new RespEntity(RespCode.SUCCESS,itemInfoService.getMoviceByYear(startYear,endYear,pageIndex,size));
    }
    @GetMapping("evaluate")
    public RespEntity  evaluate(){
        return new RespEntity(RespCode.SUCCESS,userDataService.evaluator());
    }
}
