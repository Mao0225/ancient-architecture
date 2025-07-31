package com.youlai.boot.building.converter;

import com.youlai.boot.building.model.entity.BuildingList;
import com.youlai.boot.building.model.form.BuildingListForm;
import com.youlai.boot.building.model.vo.BuildingListOptionVO;
import com.youlai.boot.common.model.Option;
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
public class BuildingListConverterImpl implements BuildingListConverter {

    @Override
    public BuildingListForm toForm(BuildingList entity) {
        if ( entity == null ) {
            return null;
        }

        BuildingListForm buildingListForm = new BuildingListForm();

        buildingListForm.setId( entity.getId() );
        buildingListForm.setBuildingName( entity.getBuildingName() );
        buildingListForm.setBuildingDescription( entity.getBuildingDescription() );
        buildingListForm.setStatus( entity.getStatus() );

        return buildingListForm;
    }

    @Override
    public BuildingList toEntity(BuildingListForm formData) {
        if ( formData == null ) {
            return null;
        }

        BuildingList buildingList = new BuildingList();

        buildingList.setId( formData.getId() );
        buildingList.setBuildingName( formData.getBuildingName() );
        buildingList.setBuildingDescription( formData.getBuildingDescription() );
        buildingList.setStatus( formData.getStatus() );

        return buildingList;
    }

    @Override
    public Option<Long> toOption(BuildingListOptionVO building) {
        if ( building == null ) {
            return null;
        }

        Option<Long> option = new Option<Long>();

        option.setValue( building.getId() );
        option.setLabel( building.getBuildingName() );

        return option;
    }

    @Override
    public List<Option<Long>> toOptions(List<BuildingListOptionVO> building) {
        if ( building == null ) {
            return null;
        }

        List<Option<Long>> list = new ArrayList<Option<Long>>( building.size() );
        for ( BuildingListOptionVO buildingListOptionVO : building ) {
            list.add( toOption( buildingListOptionVO ) );
        }

        return list;
    }
}
