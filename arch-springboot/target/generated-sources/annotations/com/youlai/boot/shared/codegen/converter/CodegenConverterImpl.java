package com.youlai.boot.shared.codegen.converter;

import com.youlai.boot.shared.codegen.model.entity.GenConfig;
import com.youlai.boot.shared.codegen.model.entity.GenFieldConfig;
import com.youlai.boot.shared.codegen.model.form.GenConfigForm;
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
public class CodegenConverterImpl implements CodegenConverter {

    @Override
    public GenConfigForm toGenConfigForm(GenConfig genConfig, List<GenFieldConfig> fieldConfigs) {
        if ( genConfig == null && fieldConfigs == null ) {
            return null;
        }

        GenConfigForm genConfigForm = new GenConfigForm();

        if ( genConfig != null ) {
            genConfigForm.setTableName( genConfig.getTableName() );
            genConfigForm.setBusinessName( genConfig.getBusinessName() );
            genConfigForm.setModuleName( genConfig.getModuleName() );
            genConfigForm.setPackageName( genConfig.getPackageName() );
            genConfigForm.setEntityName( genConfig.getEntityName() );
            genConfigForm.setAuthor( genConfig.getAuthor() );
            genConfigForm.setId( genConfig.getId() );
            genConfigForm.setParentMenuId( genConfig.getParentMenuId() );
        }
        genConfigForm.setFieldConfigs( toGenFieldConfigForm( fieldConfigs ) );

        return genConfigForm;
    }

    @Override
    public List<GenConfigForm.FieldConfig> toGenFieldConfigForm(List<GenFieldConfig> fieldConfigs) {
        if ( fieldConfigs == null ) {
            return null;
        }

        List<GenConfigForm.FieldConfig> list = new ArrayList<GenConfigForm.FieldConfig>( fieldConfigs.size() );
        for ( GenFieldConfig genFieldConfig : fieldConfigs ) {
            list.add( toGenFieldConfigForm( genFieldConfig ) );
        }

        return list;
    }

    @Override
    public GenConfigForm.FieldConfig toGenFieldConfigForm(GenFieldConfig genFieldConfig) {
        if ( genFieldConfig == null ) {
            return null;
        }

        GenConfigForm.FieldConfig fieldConfig = new GenConfigForm.FieldConfig();

        fieldConfig.setId( genFieldConfig.getId() );
        fieldConfig.setColumnName( genFieldConfig.getColumnName() );
        fieldConfig.setColumnType( genFieldConfig.getColumnType() );
        fieldConfig.setFieldName( genFieldConfig.getFieldName() );
        fieldConfig.setFieldSort( genFieldConfig.getFieldSort() );
        fieldConfig.setFieldType( genFieldConfig.getFieldType() );
        fieldConfig.setFieldComment( genFieldConfig.getFieldComment() );
        fieldConfig.setIsShowInList( genFieldConfig.getIsShowInList() );
        fieldConfig.setIsShowInForm( genFieldConfig.getIsShowInForm() );
        fieldConfig.setIsShowInQuery( genFieldConfig.getIsShowInQuery() );
        fieldConfig.setIsRequired( genFieldConfig.getIsRequired() );
        if ( genFieldConfig.getMaxLength() != null ) {
            fieldConfig.setMaxLength( genFieldConfig.getMaxLength().intValue() );
        }
        fieldConfig.setFormType( genFieldConfig.getFormType() );
        fieldConfig.setQueryType( genFieldConfig.getQueryType() );
        fieldConfig.setDictType( genFieldConfig.getDictType() );

        return fieldConfig;
    }

    @Override
    public GenConfig toGenConfig(GenConfigForm formData) {
        if ( formData == null ) {
            return null;
        }

        GenConfig genConfig = new GenConfig();

        genConfig.setId( formData.getId() );
        genConfig.setTableName( formData.getTableName() );
        genConfig.setPackageName( formData.getPackageName() );
        genConfig.setModuleName( formData.getModuleName() );
        genConfig.setEntityName( formData.getEntityName() );
        genConfig.setBusinessName( formData.getBusinessName() );
        genConfig.setParentMenuId( formData.getParentMenuId() );
        genConfig.setAuthor( formData.getAuthor() );

        return genConfig;
    }

    @Override
    public List<GenFieldConfig> toGenFieldConfig(List<GenConfigForm.FieldConfig> fieldConfigs) {
        if ( fieldConfigs == null ) {
            return null;
        }

        List<GenFieldConfig> list = new ArrayList<GenFieldConfig>( fieldConfigs.size() );
        for ( GenConfigForm.FieldConfig fieldConfig : fieldConfigs ) {
            list.add( toGenFieldConfig( fieldConfig ) );
        }

        return list;
    }

    @Override
    public GenFieldConfig toGenFieldConfig(GenConfigForm.FieldConfig fieldConfig) {
        if ( fieldConfig == null ) {
            return null;
        }

        GenFieldConfig genFieldConfig = new GenFieldConfig();

        genFieldConfig.setId( fieldConfig.getId() );
        genFieldConfig.setColumnName( fieldConfig.getColumnName() );
        genFieldConfig.setColumnType( fieldConfig.getColumnType() );
        if ( fieldConfig.getMaxLength() != null ) {
            genFieldConfig.setMaxLength( fieldConfig.getMaxLength().longValue() );
        }
        genFieldConfig.setFieldName( fieldConfig.getFieldName() );
        genFieldConfig.setFieldSort( fieldConfig.getFieldSort() );
        genFieldConfig.setFieldType( fieldConfig.getFieldType() );
        genFieldConfig.setFieldComment( fieldConfig.getFieldComment() );
        genFieldConfig.setFormType( fieldConfig.getFormType() );
        genFieldConfig.setQueryType( fieldConfig.getQueryType() );
        genFieldConfig.setIsShowInList( fieldConfig.getIsShowInList() );
        genFieldConfig.setIsShowInForm( fieldConfig.getIsShowInForm() );
        genFieldConfig.setIsShowInQuery( fieldConfig.getIsShowInQuery() );
        genFieldConfig.setIsRequired( fieldConfig.getIsRequired() );
        genFieldConfig.setDictType( fieldConfig.getDictType() );

        return genFieldConfig;
    }
}
