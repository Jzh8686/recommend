package com.gyj.gx.domain.response;

import lombok.Data;

import java.util.Date;
@Data
public class MayLikeDTO {
    Long itemId;
    String moviceName;
    Date releaseDate;
    String url;
    String cover;
    Float preference;
}
