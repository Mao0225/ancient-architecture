package com.youlai.boot.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.boot.common.model.Option;
import com.youlai.boot.system.model.bo.UserBO;
import com.youlai.boot.system.model.dto.CurrentUserDTO;
import com.youlai.boot.system.model.dto.UserImportDTO;
import com.youlai.boot.system.model.entity.User;
import com.youlai.boot.system.model.form.UserForm;
import com.youlai.boot.system.model.form.UserProfileForm;
import com.youlai.boot.system.model.vo.UserPageVO;
import com.youlai.boot.system.model.vo.UserProfileVO;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:40+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPageVO toPageVo(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        UserPageVO userPageVO = new UserPageVO();

        userPageVO.setId( bo.getId() );
        userPageVO.setUsername( bo.getUsername() );
        userPageVO.setNickname( bo.getNickname() );
        userPageVO.setMobile( bo.getMobile() );
        userPageVO.setGender( bo.getGender() );
        userPageVO.setAvatar( bo.getAvatar() );
        userPageVO.setEmail( bo.getEmail() );
        userPageVO.setStatus( bo.getStatus() );
        userPageVO.setDeptName( bo.getDeptName() );
        userPageVO.setRoleNames( bo.getRoleNames() );
        userPageVO.setCreateTime( bo.getCreateTime() );

        return userPageVO;
    }

    @Override
    public Page<UserPageVO> toPageVo(Page<UserBO> bo) {
        if ( bo == null ) {
            return null;
        }

        Page<UserPageVO> page = new Page<UserPageVO>();

        page.setPages( bo.getPages() );
        page.setRecords( userBOListToUserPageVOList( bo.getRecords() ) );
        page.setTotal( bo.getTotal() );
        page.setSize( bo.getSize() );
        page.setCurrent( bo.getCurrent() );

        return page;
    }

    @Override
    public UserForm toForm(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserForm userForm = new UserForm();

        userForm.setId( entity.getId() );
        userForm.setUsername( entity.getUsername() );
        userForm.setNickname( entity.getNickname() );
        userForm.setMobile( entity.getMobile() );
        userForm.setGender( entity.getGender() );
        userForm.setAvatar( entity.getAvatar() );
        userForm.setEmail( entity.getEmail() );
        userForm.setStatus( entity.getStatus() );
        userForm.setDeptId( entity.getDeptId() );

        return userForm;
    }

    @Override
    public User toEntity(UserForm entity) {
        if ( entity == null ) {
            return null;
        }

        User user = new User();

        user.setId( entity.getId() );
        user.setUsername( entity.getUsername() );
        user.setNickname( entity.getNickname() );
        user.setGender( entity.getGender() );
        user.setDeptId( entity.getDeptId() );
        user.setAvatar( entity.getAvatar() );
        user.setMobile( entity.getMobile() );
        user.setStatus( entity.getStatus() );
        user.setEmail( entity.getEmail() );

        return user;
    }

    @Override
    public CurrentUserDTO toCurrentUserDto(User entity) {
        if ( entity == null ) {
            return null;
        }

        CurrentUserDTO currentUserDTO = new CurrentUserDTO();

        currentUserDTO.setUserId( entity.getId() );
        currentUserDTO.setUsername( entity.getUsername() );
        currentUserDTO.setNickname( entity.getNickname() );
        currentUserDTO.setAvatar( entity.getAvatar() );

        return currentUserDTO;
    }

    @Override
    public User toEntity(UserImportDTO vo) {
        if ( vo == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( vo.getUsername() );
        user.setNickname( vo.getNickname() );
        user.setMobile( vo.getMobile() );
        user.setEmail( vo.getEmail() );

        return user;
    }

    @Override
    public UserProfileVO toProfileVo(UserBO bo) {
        if ( bo == null ) {
            return null;
        }

        UserProfileVO userProfileVO = new UserProfileVO();

        userProfileVO.setId( bo.getId() );
        userProfileVO.setUsername( bo.getUsername() );
        userProfileVO.setNickname( bo.getNickname() );
        userProfileVO.setAvatar( bo.getAvatar() );
        userProfileVO.setGender( bo.getGender() );
        userProfileVO.setMobile( bo.getMobile() );
        userProfileVO.setEmail( bo.getEmail() );
        userProfileVO.setDeptName( bo.getDeptName() );
        userProfileVO.setRoleNames( bo.getRoleNames() );
        if ( bo.getCreateTime() != null ) {
            userProfileVO.setCreateTime( Date.from( bo.getCreateTime().toInstant( ZoneOffset.UTC ) ) );
        }

        return userProfileVO;
    }

    @Override
    public User toEntity(UserProfileForm formData) {
        if ( formData == null ) {
            return null;
        }

        User user = new User();

        user.setId( formData.getId() );
        user.setUsername( formData.getUsername() );
        user.setNickname( formData.getNickname() );
        user.setGender( formData.getGender() );
        user.setAvatar( formData.getAvatar() );
        user.setMobile( formData.getMobile() );
        user.setEmail( formData.getEmail() );

        return user;
    }

    @Override
    public Option<String> toOption(User entity) {
        if ( entity == null ) {
            return null;
        }

        Option<String> option = new Option<String>();

        option.setLabel( entity.getNickname() );
        if ( entity.getId() != null ) {
            option.setValue( String.valueOf( entity.getId() ) );
        }

        return option;
    }

    @Override
    public List<Option<String>> toOptions(List<User> list) {
        if ( list == null ) {
            return null;
        }

        List<Option<String>> list1 = new ArrayList<Option<String>>( list.size() );
        for ( User user : list ) {
            list1.add( toOption( user ) );
        }

        return list1;
    }

    protected List<UserPageVO> userBOListToUserPageVOList(List<UserBO> list) {
        if ( list == null ) {
            return null;
        }

        List<UserPageVO> list1 = new ArrayList<UserPageVO>( list.size() );
        for ( UserBO userBO : list ) {
            list1.add( toPageVo( userBO ) );
        }

        return list1;
    }
}
