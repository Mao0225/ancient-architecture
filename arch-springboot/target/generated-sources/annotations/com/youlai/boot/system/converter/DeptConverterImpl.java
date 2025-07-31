package com.youlai.boot.system.converter;

import com.youlai.boot.system.model.entity.Dept;
import com.youlai.boot.system.model.form.DeptForm;
import com.youlai.boot.system.model.vo.DeptVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DeptConverterImpl implements DeptConverter {

    @Override
    public DeptForm toForm(Dept entity) {
        if ( entity == null ) {
            return null;
        }

        DeptForm deptForm = new DeptForm();

        deptForm.setId( entity.getId() );
        deptForm.setName( entity.getName() );
        deptForm.setCode( entity.getCode() );
        deptForm.setParentId( entity.getParentId() );
        deptForm.setStatus( entity.getStatus() );
        deptForm.setSort( entity.getSort() );

        return deptForm;
    }

    @Override
    public DeptVO toVo(Dept entity) {
        if ( entity == null ) {
            return null;
        }

        DeptVO deptVO = new DeptVO();

        deptVO.setId( entity.getId() );
        deptVO.setParentId( entity.getParentId() );
        deptVO.setName( entity.getName() );
        deptVO.setCode( entity.getCode() );
        deptVO.setSort( entity.getSort() );
        deptVO.setStatus( entity.getStatus() );
        deptVO.setCreateTime( entity.getCreateTime() );
        deptVO.setUpdateTime( entity.getUpdateTime() );

        return deptVO;
    }

    @Override
    public Dept toEntity(DeptForm deptForm) {
        if ( deptForm == null ) {
            return null;
        }

        Dept dept = new Dept();

        dept.setId( deptForm.getId() );
        dept.setName( deptForm.getName() );
        dept.setCode( deptForm.getCode() );
        dept.setParentId( deptForm.getParentId() );
        dept.setSort( deptForm.getSort() );
        dept.setStatus( deptForm.getStatus() );

        return dept;
    }
}
