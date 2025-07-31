package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.system.model.entity.Dict;
import com.youlai.boot.system.model.form.DictForm;
import com.youlai.boot.system.model.vo.DictPageVO;
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
public class DictConverterImpl implements DictConverter {

    @Override
    public Page<DictPageVO> toPageVo(Page<Dict> page) {
        if ( page == null ) {
            return null;
        }

        Page<DictPageVO> page1 = new Page<DictPageVO>();

        page1.setPages( page.getPages() );
        page1.setRecords( dictListToDictPageVOList( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );

        return page1;
    }

    @Override
    public DictForm toForm(Dict entity) {
        if ( entity == null ) {
            return null;
        }

        DictForm dictForm = new DictForm();

        dictForm.setId( entity.getId() );
        dictForm.setName( entity.getName() );
        dictForm.setDictCode( entity.getDictCode() );
        dictForm.setRemark( entity.getRemark() );
        dictForm.setStatus( entity.getStatus() );

        return dictForm;
    }

    @Override
    public Dict toEntity(DictForm entity) {
        if ( entity == null ) {
            return null;
        }

        Dict dict = new Dict();

        dict.setId( entity.getId() );
        dict.setDictCode( entity.getDictCode() );
        dict.setName( entity.getName() );
        dict.setStatus( entity.getStatus() );
        dict.setRemark( entity.getRemark() );

        return dict;
    }

    protected DictPageVO dictToDictPageVO(Dict dict) {
        if ( dict == null ) {
            return null;
        }

        DictPageVO dictPageVO = new DictPageVO();

        dictPageVO.setId( dict.getId() );
        dictPageVO.setName( dict.getName() );
        dictPageVO.setDictCode( dict.getDictCode() );
        dictPageVO.setStatus( dict.getStatus() );

        return dictPageVO;
    }

    protected List<DictPageVO> dictListToDictPageVOList(List<Dict> list) {
        if ( list == null ) {
            return null;
        }

        List<DictPageVO> list1 = new ArrayList<DictPageVO>( list.size() );
        for ( Dict dict : list ) {
            list1.add( dictToDictPageVO( dict ) );
        }

        return list1;
    }
}
