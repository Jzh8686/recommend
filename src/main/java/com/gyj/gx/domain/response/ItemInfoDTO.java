package com.gyj.gx.domain.response;

import com.gyj.gx.domain.ItemInfoEntity;
import lombok.Data;

import java.util.List;

@Data
public class ItemInfoDTO {
    List<MayLikeDTO> list;
    Long totalPage;

    public ItemInfoDTO(List<MayLikeDTO> list, Long totalPage) {
        this.list = list;
        this.totalPage = totalPage;
    }
}
