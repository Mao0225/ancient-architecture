package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.Config;
import com.youlai.boot.system.model.form.ConfigForm;
import com.youlai.boot.system.model.vo.ConfigVO;
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
public class ConfigConverterImpl implements ConfigConverter {

    @Override
    public Page<ConfigVO> toPageVo(Page<Config> page) {
        if ( page == null ) {
            return null;
        }

        Page<ConfigVO> page1 = new Page<ConfigVO>();

        page1.setPages( page.getPages() );
        page1.setRecords( configListToConfigVOList( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );

        return page1;
    }

    @Override
    public Config toEntity(ConfigForm configForm) {
        if ( configForm == null ) {
            return null;
        }

        Config config = new Config();

        config.setId( configForm.getId() );
        config.setConfigName( configForm.getConfigName() );
        config.setConfigKey( configForm.getConfigKey() );
        config.setConfigValue( configForm.getConfigValue() );
        config.setRemark( configForm.getRemark() );

        return config;
    }

    @Override
    public ConfigForm toForm(Config entity) {
        if ( entity == null ) {
            return null;
        }

        ConfigForm configForm = new ConfigForm();

        configForm.setId( entity.getId() );
        configForm.setConfigName( entity.getConfigName() );
        configForm.setConfigKey( entity.getConfigKey() );
        configForm.setConfigValue( entity.getConfigValue() );
        configForm.setRemark( entity.getRemark() );

        return configForm;
    }

    protected ConfigVO configToConfigVO(Config config) {
        if ( config == null ) {
            return null;
        }

        ConfigVO.ConfigVOBuilder configVO = ConfigVO.builder();

        configVO.id( config.getId() );
        configVO.configName( config.getConfigName() );
        configVO.configKey( config.getConfigKey() );
        configVO.configValue( config.getConfigValue() );
        configVO.remark( config.getRemark() );

        return configVO.build();
    }

    protected List<ConfigVO> configListToConfigVOList(List<Config> list) {
        if ( list == null ) {
            return null;
        }

        List<ConfigVO> list1 = new ArrayList<ConfigVO>( list.size() );
        for ( Config config : list ) {
            list1.add( configToConfigVO( config ) );
        }

        return list1;
    }
}
