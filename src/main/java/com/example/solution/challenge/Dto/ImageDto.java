package com.example.solution.challenge.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {

    private String originImageName;
    private String imageName;
    private String imagePath;

    public ImageDto toEntity() {
        ImageDto image = ImageDto.builder()
                .originImageName(originImageName)
                .imageName(imageName)
                .imagePath(imagePath)
                .build();
        return image;
    }

    @Builder
    public ImageDto (String originImageName, String imageName,String imagePath) {
        this.originImageName = originImageName;
        this.imageName = imageName;
        this.imagePath = imagePath;
    }

}