package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.entity.Role;
import com.youlai.boot.system.model.form.RoleForm;
import com.youlai.boot.system.model.vo.RolePageVO;
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
public class RoleConverterImpl implements RoleConverter {

    @Override
    public Page<RolePageVO> toPageVo(Page<Role> page) {
        if ( page == null ) {
            return null;
        }

        Page<RolePageVO> page1 = new Page<RolePageVO>();

        page1.setPages( page.getPages() );
        page1.setRecords( roleListToRolePageVOList( page.getRecords() ) );
        page1.setTotal( page.getTotal() );
        page1.setSize( page.getSize() );
        page1.setCurrent( page.getCurrent() );

        return page1;
    }

    @Override
    public Option<Long> toOption(Role role) {
        if ( role == null ) {
            return null;
        }

        Option<Long> option = new Option<Long>();

        option.setValue( role.getId() );
        option.setLabel( role.getName() );

        return option;
    }

    @Override
    public List<Option<Long>> toOptions(List<Role> roles) {
        if ( roles == null ) {
            return null;
        }

        List<Option<Long>> list = new ArrayList<Option<Long>>( roles.size() );
        for ( Role role : roles ) {
            list.add( toOption( role ) );
        }

        return list;
    }

    @Override
    public Role toEntity(RoleForm roleForm) {
        if ( roleForm == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleForm.getId() );
        role.setName( roleForm.getName() );
        role.setCode( roleForm.getCode() );
        role.setSort( roleForm.getSort() );
        role.setStatus( roleForm.getStatus() );
        role.setDataScope( roleForm.getDataScope() );

        return role;
    }

    @Override
    public RoleForm toForm(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleForm roleForm = new RoleForm();

        roleForm.setId( entity.getId() );
        roleForm.setName( entity.getName() );
        roleForm.setCode( entity.getCode() );
        roleForm.setSort( entity.getSort() );
        roleForm.setStatus( entity.getStatus() );
        roleForm.setDataScope( entity.getDataScope() );

        return roleForm;
    }

    protected RolePageVO roleToRolePageVO(Role role) {
        if ( role == null ) {
            return null;
        }

        RolePageVO rolePageVO = new RolePageVO();

        rolePageVO.setId( role.getId() );
        rolePageVO.setName( role.getName() );
        rolePageVO.setCode( role.getCode() );
        rolePageVO.setStatus( role.getStatus() );
        rolePageVO.setSort( role.getSort() );
        rolePageVO.setCreateTime( role.getCreateTime() );
        rolePageVO.setUpdateTime( role.getUpdateTime() );

        return rolePageVO;
    }

    protected List<RolePageVO> roleListToRolePageVOList(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        List<RolePageVO> list1 = new ArrayList<RolePageVO>( list.size() );
        for ( Role role : list ) {
            list1.add( roleToRolePageVO( role ) );
        }

        return list1;
    }
}
