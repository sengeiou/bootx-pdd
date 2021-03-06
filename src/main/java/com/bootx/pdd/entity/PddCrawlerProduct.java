
package com.bootx.pdd.entity;

import com.bootx.common.BaseAttributeConverter;
import com.bootx.entity.BaseEntity;
import com.bootx.entity.CrawlerProduct;
import com.bootx.entity.Member;
import com.bootx.entity.ProductCategory;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.compress.utils.Lists;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Entity - 商品
 * 
 * @author IGOMALL  Team
 * @version 1.0
 */
@Entity(name = "pdd_CrawlerProduct")
public class PddCrawlerProduct extends BaseEntity<Long> {

	private static final long serialVersionUID = -6977025562650112419L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false,updatable = false)
	private CrawlerProduct crawlerProduct;

	@JsonView({PageView.class,EditView.class})
	private String sn;

	/**
	 * 名称
	 */
	@Length(max = 200)
	@JsonView({PageView.class,EditView.class})
	private String name;

	@JsonView({PageView.class,EditView.class})
	private String price;

	/**
	 * 商品图片
	 */
	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductImage crawlerProductImage;

	/**
	 * 介绍
	 */
	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductIntroduction crawlerProductIntroduction;

	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductIntroductionImage crawlerProductIntroductionImage;


	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductParameterValue crawlerProductParameterValue;

	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductSku crawlerProductSku;

	/**
	 * 规格项
	 */
	@OneToOne(mappedBy = "crawlerProduct",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonView({EditView.class})
	private PddCrawlerProductSpecification crawlerProductSpecification;
	/**
	 * 商品分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductCategory productCategory;

	@Column(length = 60)
	@Convert(converter = CrawlerProductCategoryIdConverter.class)
	@JsonView({EditView.class})
	private List<Long> productCategoryIds = new ArrayList<>();

	@Column(length = 300)
	@Convert(converter = CrawlerProductCategoryNamesConverter.class)
	private List<String> productCategoryNames = new ArrayList<>();


	@JsonView({EditView.class})
	private Long stock;

	@JsonView({PageView.class})
	@Column(updatable = false)
	private String pluginId;

	@NotEmpty
	@Column(length = 1000,nullable = false,updatable = false)
	@JsonView({PageView.class})
	private String url;

	/**
	 * 对url进行sha1加密，方便用来比较是否已经抓取过了
	 */
	@NotEmpty
	@Column(nullable = false,updatable = false)
	private String md5;

	@Column(length = 100)
	@Convert(converter = CrawlerProductStoreConverter.class)
	@JsonView({EditView.class})
	private PddCrawlerProductStore crawlerProductStore;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false,updatable = false)
	private Member member;

	@NotEmpty
	@Column(nullable = false,updatable = false)
	private String batchId;

	@Transient
	@JsonView({PageView.class})
	private List<Map<String, Object>> pddLogs = new ArrayList<>();

	@Lob
	private String content;

	public CrawlerProduct getCrawlerProduct() {
		return crawlerProduct;
	}

	public void setCrawlerProduct(CrawlerProduct crawlerProduct) {
		this.crawlerProduct = crawlerProduct;
	}



	/**
	 * 0：待采集
	 * 1：采集完成
	 * 2：采集失败
	 */
	@JsonView(PageView.class)
	private Integer status;

	/**
	 * 是否删除
	 */
	private Boolean isDeleted;

	/**
	 * 发布状态
	 * 10：待发布
	 * 11：发布中
	 * 12：发布成功
	 * 13：发布失败
	 * 14：草稿箱
	 */
	@JsonView(PageView.class)
	private Integer publishStatus;

	public PddCrawlerProduct() {
		init();
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public PddCrawlerProductImage getCrawlerProductImage() {
		return crawlerProductImage;
	}

	public void setCrawlerProductImage(PddCrawlerProductImage crawlerProductImage) {
		this.crawlerProductImage = crawlerProductImage;
	}

	public PddCrawlerProductIntroduction getCrawlerProductIntroduction() {
		return crawlerProductIntroduction;
	}

	public void setCrawlerProductIntroduction(PddCrawlerProductIntroduction crawlerProductIntroduction) {
		this.crawlerProductIntroduction = crawlerProductIntroduction;
	}

	public PddCrawlerProductIntroductionImage getCrawlerProductIntroductionImage() {
		return crawlerProductIntroductionImage;
	}

	public void setCrawlerProductIntroductionImage(PddCrawlerProductIntroductionImage crawlerProductIntroductionImage) {
		this.crawlerProductIntroductionImage = crawlerProductIntroductionImage;
	}

	public PddCrawlerProductParameterValue getCrawlerProductParameterValue() {
		return crawlerProductParameterValue;
	}

	public void setCrawlerProductParameterValue(PddCrawlerProductParameterValue crawlerProductParameterValue) {
		this.crawlerProductParameterValue = crawlerProductParameterValue;
	}

	public PddCrawlerProductSku getCrawlerProductSku() {
		return crawlerProductSku;
	}

	public void setCrawlerProductSku(PddCrawlerProductSku crawlerProductSku) {
		this.crawlerProductSku = crawlerProductSku;
	}

	public PddCrawlerProductSpecification getCrawlerProductSpecification() {
		return crawlerProductSpecification;
	}

	public void setCrawlerProductSpecification(PddCrawlerProductSpecification crawlerProductSpecification) {
		this.crawlerProductSpecification = crawlerProductSpecification;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public List<Long> getProductCategoryIds() {
		return productCategoryIds;
	}

	public void setProductCategoryIds(List<Long> productCategoryIds) {
		this.productCategoryIds = productCategoryIds;
	}

	public List<String> getProductCategoryNames() {
		return productCategoryNames;
	}

	public void setProductCategoryNames(List<String> productCategoryNames) {
		this.productCategoryNames = productCategoryNames;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getPluginId() {
		return pluginId;
	}

	public void setPluginId(String pluginId) {
		this.pluginId = pluginId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		if (url != null) {
			setMd5(DigestUtils.md5Hex(url));
		}
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public PddCrawlerProductStore getCrawlerProductStore() {
		return crawlerProductStore;
	}

	public void setCrawlerProductStore(PddCrawlerProductStore crawlerProductStore) {
		this.crawlerProductStore = crawlerProductStore;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Map<String, Object>> getPddLogs() {
		return pddLogs;
	}

	public void setPddLogs(List<Map<String, Object>> pddLogs) {
		this.pddLogs = pddLogs;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void init(){
		setStatus(0);
		setCrawlerProductImage(new PddCrawlerProductImage(this));
		setCrawlerProductIntroduction(new PddCrawlerProductIntroduction(this));
		setCrawlerProductIntroductionImage(new PddCrawlerProductIntroductionImage(this));
		setCrawlerProductParameterValue(new PddCrawlerProductParameterValue(this));
		setCrawlerProductSku(new PddCrawlerProductSku(this));
		setCrawlerProductSpecification(new PddCrawlerProductSpecification(this));
		setProductCategory(null);
		setProductCategoryIds(Lists.newArrayList());
		setProductCategoryNames(Lists.newArrayList());
		setStock(0L);
		setCrawlerProductStore(new PddCrawlerProductStore());
	}

	@Transient
	@JsonView({PageView.class})
	public String getImage(){
		try {
			return getCrawlerProductImage().getImages().get(0);
		}catch (Exception e){
			return null;
		}
	}

	@Transient
	@JsonView({PageView.class})
	public String getProductCategoryName(){
		if(productCategory!=null){
			return productCategory.getName();
		}
		return null;
	}

	@Converter
	public static class CrawlerProductCategoryIdConverter extends BaseAttributeConverter<List<Long>> {
	}
	@Converter
	public static class CrawlerProductCategoryNamesConverter extends BaseAttributeConverter<List<String>> {
	}
	@Converter
	public static class CrawlerProductStoreConverter extends BaseAttributeConverter<PddCrawlerProductStore> {
	}

}