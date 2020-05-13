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
    String searchDate;
    public ItemInfoEntity(Long itemId, String moviceName, Date releaseDate, String url) {
        this.itemId = itemId;
        this.moviceName = moviceName;
        this.releaseDate = releaseDate;
        this.url = url;
    }

    public ItemInfoEntity(Long itemId, String moviceName, Date releaseDate, String url, String searchDate) {
        this.itemId = itemId;
        this.moviceName = moviceName;
        this.releaseDate = releaseDate;
        this.url = url;
        this.searchDate = searchDate;
    }
}
