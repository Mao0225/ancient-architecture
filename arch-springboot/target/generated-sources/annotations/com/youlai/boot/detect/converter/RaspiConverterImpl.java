package com.youlai.boot.detect.converter;

import com.youlai.boot.common.model.Option;
import com.youlai.boot.detect.model.entity.Raspi;
import com.youlai.boot.detect.model.form.RaspiForm;
import com.youlai.boot.detect.model.vo.RaspiOptionVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:40+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class RaspiConverterImpl implements RaspiConverter {

    @Override
    public RaspiForm toForm(Raspi entity) {
        if ( entity == null ) {
            return null;
        }

        RaspiForm raspiForm = new RaspiForm();

        if ( entity.getId() != null ) {
            raspiForm.setId( entity.getId().intValue() );
        }
        raspiForm.setSerialNumber( entity.getSerialNumber() );
        raspiForm.setTerminalLink( entity.getTerminalLink() );
        raspiForm.setDesktopLink( entity.getDesktopLink() );
        raspiForm.setUsername( entity.getUsername() );
        raspiForm.setPassword( entity.getPassword() );
        raspiForm.setDeptId( entity.getDeptId() );
        raspiForm.setLocation( entity.getLocation() );
        raspiForm.setUploadInterval( entity.getUploadInterval() );
        raspiForm.setAlertKeywords( entity.getAlertKeywords() );

        return raspiForm;
    }

    @Override
    public Raspi toEntity(RaspiForm formData) {
        if ( formData == null ) {
            return null;
        }

        Raspi raspi = new Raspi();

        if ( formData.getId() != null ) {
            raspi.setId( formData.getId().longValue() );
        }
        raspi.setSerialNumber( formData.getSerialNumber() );
        raspi.setDeptId( formData.getDeptId() );
        raspi.setTerminalLink( formData.getTerminalLink() );
        raspi.setDesktopLink( formData.getDesktopLink() );
        raspi.setUsername( formData.getUsername() );
        raspi.setPassword( formData.getPassword() );
        raspi.setLocation( formData.getLocation() );
        raspi.setUploadInterval( formData.getUploadInterval() );
        raspi.setAlertKeywords( formData.getAlertKeywords() );

        return raspi;
    }

    @Override
    public Option<Long> toOption(RaspiOptionVO raspis) {
        if ( raspis == null ) {
            return null;
        }

        Option<Long> option = new Option<Long>();

        if ( raspis.getId() != null ) {
            option.setValue( raspis.getId().longValue() );
        }
        option.setLabel( raspis.getSerialNumber() );

        return option;
    }

    @Override
    public List<Option<Long>> toOptions(List<RaspiOptionVO> raspis) {
        if ( raspis == null ) {
            return null;
        }

        List<Option<Long>> list = new ArrayList<Option<Long>>( raspis.size() );
        for ( RaspiOptionVO raspiOptionVO : raspis ) {
            list.add( toOption( raspiOptionVO ) );
        }

        return list;
    }
}
