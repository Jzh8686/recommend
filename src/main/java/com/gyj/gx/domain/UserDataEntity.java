package com.gyj.gx.domain;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("userdata")
public class UserDataEntity {
    private Long userId;
    private Long itemId;
    private Float preference;
    private Long timestampValue;

    public UserDataEntity(Long userId, Long itemId, Float preference) {
        this.userId = userId;
        this.itemId = itemId;
        this.preference = preference;
    }

    public UserDataEntity(Long userId, Long itemId, Float preference, Long timestampValue) {
        this.userId = userId;
        this.itemId = itemId;
        this.preference = preference;
        this.timestampValue = timestampValue;
    }
}
