package com.example.online_courses.util;

import com.example.online_courses.dto.ContentDto;
import com.example.online_courses.dto.CreateContentRequest;
import com.example.online_courses.models.Content;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class ContentMappingUtil {

    public static ContentDto mapToContentDto(Content content) {
        return ContentDto.builder()
                .name(content.getName())
                .info(content.getInfo())
                .type(content.getType())
                .block(content.isBlock())
                .build();
    }

    public static Content mapToContentFromRequest(CreateContentRequest request) {
        return Content.builder()
                .name(request.getName())
                .info(request.getInfo())
                .type(request.getType())
                .block(request.isBlock())
                .build();
    }
}
