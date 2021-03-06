
package com.bootx.plugin.craw.pojo.tMall;

import com.bootx.common.BaseAttributeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Convert;
import javax.persistence.Converter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRootBean implements Serializable {

    private ValItemInfo valItemInfo;
    private ItemDO itemDO;
    @Convert(converter = PropertyPicsConverter.class)
    private Map<String, List<String>> propertyPics = new HashMap<>();


    @Convert(converter = ApiConverter.class)
    private Map<String, String> api = new HashMap<>();

    public ValItemInfo getValItemInfo() {
        return valItemInfo;
    }

    public void setValItemInfo(ValItemInfo valItemInfo) {
        this.valItemInfo = valItemInfo;
    }

    public ItemDO getItemDO() {
        return itemDO;
    }

    public void setItemDO(ItemDO itemDO) {
        this.itemDO = itemDO;
    }

    public Map<String, List<String>> getPropertyPics() {
        return propertyPics;
    }

    public void setPropertyPics(Map<String, List<String>> propertyPics) {
        this.propertyPics = propertyPics;
    }

    public Map<String, String> getApi() {
        return api;
    }

    public void setApi(Map<String, String> api) {
        this.api = api;
    }

    @Converter
    public static class PropertyPicsConverter extends BaseAttributeConverter<Map<String, List<String>>> {

    }
    @Converter
    public static class ApiConverter extends BaseAttributeConverter<Map<String, String>> {

    }
}