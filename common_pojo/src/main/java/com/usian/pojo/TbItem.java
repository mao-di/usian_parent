package com.usian.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
@ApiModel
public class TbItem implements Serializable {
    @ApiModelProperty(hidden = true)
    private Long id;
    @ApiModelProperty(value = "商品名称",required = true)
    private String title;
    @ApiModelProperty(value = "商品卖点",required = true)
    private String sellPoint;
    @ApiModelProperty(value = "商品价格",required = true)
    private Long price;
    @ApiModelProperty(value = "商品库存",required = true)
    private Integer num;
    @ApiModelProperty(value = "商品条形码",required = true)
    private String barcode;
    @ApiModelProperty(value = "商品图片",required = true)
    private String image;
    @ApiModelProperty(value = "商品描述模板id",required = true)
    private Long cid;
    @ApiModelProperty(value = "商品状态",required = true)
    private Byte status;
    @ApiModelProperty(value = "商品创建时间",required = true)
    private Date created;
    @ApiModelProperty(value = "商品修改时间",required = true)
    private Date updated;

    public TbItem() {
    }

    public TbItem(Long cid,String title ) {
        this.title = title;
        this.cid = cid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}