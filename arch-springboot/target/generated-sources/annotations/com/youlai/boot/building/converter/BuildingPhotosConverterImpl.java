package com.youlai.boot.building.converter;

import com.youlai.boot.building.model.entity.BuildingPhotos;
import com.youlai.boot.building.model.form.BuildingPhotosForm;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:40+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class BuildingPhotosConverterImpl implements BuildingPhotosConverter {

    @Override
    public BuildingPhotosForm toForm(BuildingPhotos entity) {
        if ( entity == null ) {
            return null;
        }

        BuildingPhotosForm buildingPhotosForm = new BuildingPhotosForm();

        buildingPhotosForm.setId( entity.getId() );
        buildingPhotosForm.setBuildingId( entity.getBuildingId() );
        buildingPhotosForm.setBuildingPart( entity.getBuildingPart() );
        buildingPhotosForm.setPhotoUrl( entity.getPhotoUrl() );
        buildingPhotosForm.setDescription( entity.getDescription() );
        buildingPhotosForm.setStatus( entity.getStatus() );

        return buildingPhotosForm;
    }

    @Override
    public BuildingPhotos toEntity(BuildingPhotosForm formData) {
        if ( formData == null ) {
            return null;
        }

        BuildingPhotos buildingPhotos = new BuildingPhotos();

        buildingPhotos.setId( formData.getId() );
        buildingPhotos.setBuildingId( formData.getBuildingId() );
        buildingPhotos.setBuildingPart( formData.getBuildingPart() );
        buildingPhotos.setPhotoUrl( formData.getPhotoUrl() );
        buildingPhotos.setDescription( formData.getDescription() );
        buildingPhotos.setStatus( formData.getStatus() );

        return buildingPhotos;
    }
}
