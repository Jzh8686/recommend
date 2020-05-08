package com.gyj.gx.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("iteminfo")
public class ItemInfoEntity {
    Long itemId;
    String moviceName;
    Date releaseDate;
    String url;
    String cover;
    public ItemInfoEntity(Long itemId, String moviceName, Date releaseDate, String url) {
        this.itemId = itemId;
        this.moviceName = moviceName;
        this.releaseDate = releaseDate;
        this.url = url;
    }
}
