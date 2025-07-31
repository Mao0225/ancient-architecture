package com.youlai.boot.system.converter;

import com.youlai.boot.system.model.entity.Menu;
import com.youlai.boot.system.model.form.MenuForm;
import com.youlai.boot.system.model.vo.MenuVO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class MenuConverterImpl implements MenuConverter {

    @Override
    public MenuVO toVo(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuVO menuVO = new MenuVO();

        menuVO.setId( entity.getId() );
        menuVO.setParentId( entity.getParentId() );
        menuVO.setName( entity.getName() );
        menuVO.setType( entity.getType() );
        menuVO.setRouteName( entity.getRouteName() );
        menuVO.setRoutePath( entity.getRoutePath() );
        menuVO.setComponent( entity.getComponent() );
        menuVO.setSort( entity.getSort() );
        menuVO.setVisible( entity.getVisible() );
        menuVO.setIcon( entity.getIcon() );
        menuVO.setRedirect( entity.getRedirect() );
        menuVO.setPerm( entity.getPerm() );

        return menuVO;
    }

    @Override
    public MenuForm toForm(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuForm menuForm = new MenuForm();

        menuForm.setId( entity.getId() );
        menuForm.setParentId( entity.getParentId() );
        menuForm.setName( entity.getName() );
        menuForm.setType( entity.getType() );
        menuForm.setRouteName( entity.getRouteName() );
        menuForm.setRoutePath( entity.getRoutePath() );
        menuForm.setComponent( entity.getComponent() );
        menuForm.setPerm( entity.getPerm() );
        menuForm.setVisible( entity.getVisible() );
        menuForm.setSort( entity.getSort() );
        menuForm.setIcon( entity.getIcon() );
        menuForm.setRedirect( entity.getRedirect() );
        menuForm.setKeepAlive( entity.getKeepAlive() );
        menuForm.setAlwaysShow( entity.getAlwaysShow() );

        return menuForm;
    }

    @Override
    public Menu toEntity(MenuForm menuForm) {
        if ( menuForm == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( menuForm.getId() );
        menu.setParentId( menuForm.getParentId() );
        menu.setName( menuForm.getName() );
        menu.setType( menuForm.getType() );
        menu.setRouteName( menuForm.getRouteName() );
        menu.setRoutePath( menuForm.getRoutePath() );
        menu.setComponent( menuForm.getComponent() );
        menu.setPerm( menuForm.getPerm() );
        menu.setVisible( menuForm.getVisible() );
        menu.setSort( menuForm.getSort() );
        menu.setIcon( menuForm.getIcon() );
        menu.setRedirect( menuForm.getRedirect() );
        menu.setKeepAlive( menuForm.getKeepAlive() );
        menu.setAlwaysShow( menuForm.getAlwaysShow() );

        return menu;
    }
}
