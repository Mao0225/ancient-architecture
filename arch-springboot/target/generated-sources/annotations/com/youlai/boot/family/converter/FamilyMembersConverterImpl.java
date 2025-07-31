package com.youlai.boot.family.converter;

import com.youlai.boot.family.model.entity.FamilyMembers;
import com.youlai.boot.family.model.form.FamilyMembersForm;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-31T11:24:39+0800",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class FamilyMembersConverterImpl implements FamilyMembersConverter {

    @Override
    public FamilyMembersForm toForm(FamilyMembers entity) {
        if ( entity == null ) {
            return null;
        }

        FamilyMembersForm familyMembersForm = new FamilyMembersForm();

        if ( entity.getId() != null ) {
            familyMembersForm.setId( entity.getId().intValue() );
        }
        familyMembersForm.setName( entity.getName() );
        familyMembersForm.setGender( entity.getGender() );
        familyMembersForm.setBirthDate( entity.getBirthDate() );
        familyMembersForm.setRelationship( entity.getRelationship() );
        familyMembersForm.setPhone( entity.getPhone() );
        familyMembersForm.setEmail( entity.getEmail() );
        familyMembersForm.setUserId( entity.getUserId() );
        familyMembersForm.setUsername( entity.getUsername() );
        familyMembersForm.setPassword( entity.getPassword() );
        familyMembersForm.setStatus( entity.getStatus() );

        return familyMembersForm;
    }

    @Override
    public FamilyMembers toEntity(FamilyMembersForm formData) {
        if ( formData == null ) {
            return null;
        }

        FamilyMembers familyMembers = new FamilyMembers();

        if ( formData.getId() != null ) {
            familyMembers.setId( formData.getId().longValue() );
        }
        familyMembers.setName( formData.getName() );
        familyMembers.setGender( formData.getGender() );
        familyMembers.setBirthDate( formData.getBirthDate() );
        familyMembers.setRelationship( formData.getRelationship() );
        familyMembers.setPhone( formData.getPhone() );
        familyMembers.setEmail( formData.getEmail() );
        familyMembers.setUserId( formData.getUserId() );
        familyMembers.setUsername( formData.getUsername() );
        familyMembers.setPassword( formData.getPassword() );
        familyMembers.setStatus( formData.getStatus() );

        return familyMembers;
    }
}
