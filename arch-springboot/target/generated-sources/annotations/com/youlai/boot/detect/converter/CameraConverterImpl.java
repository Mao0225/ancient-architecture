package com.youlai.boot.detect.converter;

import com.youlai.boot.common.model.Option;
import com.youlai.boot.detect.model.entity.Camera;
import com.youlai.boot.detect.model.form.CameraForm;
import com.youlai.boot.detect.model.vo.CameraOptionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class CameraConverterImpl implements CameraConverter {

    @Override
    public CameraForm toForm(Camera entity) {
        if ( entity == null ) {
            return null;
        }

        CameraForm cameraForm = new CameraForm();

        if ( entity.getId() != null ) {
            cameraForm.setId( entity.getId().intValue() );
        }
        cameraForm.setVideoUrl( entity.getVideoUrl() );
        cameraForm.setDeviceNumber( entity.getDeviceNumber() );
        cameraForm.setRaspberryPiId( entity.getRaspberryPiId() );
        cameraForm.setLocation( entity.getLocation() );

        return cameraForm;
    }

    @Override
    public Camera toEntity(CameraForm formData) {
        if ( formData == null ) {
            return null;
        }

        Camera camera = new Camera();

        if ( formData.getId() != null ) {
            camera.setId( formData.getId().longValue() );
        }
        camera.setVideoUrl( formData.getVideoUrl() );
        camera.setDeviceNumber( formData.getDeviceNumber() );
        camera.setRaspberryPiId( formData.getRaspberryPiId() );
        camera.setLocation( formData.getLocation() );

        return camera;
    }

    @Override
    public Option<String> toOption(CameraOptionVO camera) {
        if ( camera == null ) {
            return null;
        }

        Option<String> option = new Option<String>();

        option.setValue( camera.getCameraUuid() );
        option.setLabel( camera.getDeviceNumber() );

        return option;
    }

    @Override
    public List<Option<String>> toOptions(List<CameraOptionVO> cameras) {
        if ( cameras == null ) {
            return null;
        }

        List<Option<String>> list = new ArrayList<Option<String>>( cameras.size() );
        for ( CameraOptionVO cameraOptionVO : cameras ) {
            list.add( toOption( cameraOptionVO ) );
        }

        return list;
    }
}
