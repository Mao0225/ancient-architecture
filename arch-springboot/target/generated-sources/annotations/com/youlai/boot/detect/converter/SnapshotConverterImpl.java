package com.youlai.boot.detect.converter;

import com.youlai.boot.detect.model.entity.Snapshot;
import com.youlai.boot.detect.model.form.SnapshotForm;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class SnapshotConverterImpl implements SnapshotConverter {

    @Override
    public SnapshotForm toForm(Snapshot entity) {
        if ( entity == null ) {
            return null;
        }

        SnapshotForm snapshotForm = new SnapshotForm();

        snapshotForm.setId( entity.getId() );
        snapshotForm.setCameraUuid( entity.getCameraUuid() );
        snapshotForm.setWet( entity.getWet() );
        snapshotForm.setTem( entity.getTem() );
        snapshotForm.setInfo( entity.getInfo() );
        snapshotForm.setSmoke( entity.getSmoke() );
        snapshotForm.setImagePath( entity.getImagePath() );
        snapshotForm.setGrabTime( entity.getGrabTime() );
        snapshotForm.setDetectInfo( entity.getDetectInfo() );

        return snapshotForm;
    }

    @Override
    public Snapshot toEntity(SnapshotForm formData) {
        if ( formData == null ) {
            return null;
        }

        Snapshot snapshot = new Snapshot();

        snapshot.setId( formData.getId() );
        snapshot.setCameraUuid( formData.getCameraUuid() );
        snapshot.setWet( formData.getWet() );
        snapshot.setTem( formData.getTem() );
        snapshot.setInfo( formData.getInfo() );
        snapshot.setSmoke( formData.getSmoke() );
        snapshot.setImagePath( formData.getImagePath() );
        snapshot.setGrabTime( formData.getGrabTime() );
        snapshot.setDetectInfo( formData.getDetectInfo() );

        return snapshot;
    }
}
