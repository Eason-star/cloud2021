package com.eason.springcloud.po;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ElementValueDTO extends HashMap<String,Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final List<String> DATA_STRING = Lists.newArrayList("_date","_time");

    public static final List<String> SPECIAL_DATA_STRING = Lists.newArrayList("appro_data","open_data");

//    @ApiModelProperty("ID")
//    private Long id;
//    @ApiModelProperty("编码")
//    private String code;
//    @ApiModelProperty("名称")
//    private String name;
//    @ApiModelProperty("序号")
//    private Integer disp_order;
//    @ApiModelProperty("备注")
//    private String remark;
//    @ApiModelProperty("要素编码")
//    private String ele_code;
//    @ApiModelProperty("租户id")
//    private Long tenant_id;
//    @ApiModelProperty("账套id")
//    private Long acct_set_id;


//    @ApiModelProperty("启用时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
////    @JsonIgnore
//    private Date start_date;
//
//
//    @ApiModelProperty("停用时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
////    @JsonIgnore
//    private Date end_date;
//
//
//    @ApiModelProperty("更新时间")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
////    @JsonIgnore
//    private Date update_time;
//
//    @ApiModelProperty("上级ID")
//    private long parent_id = 0L;
//
//    @ApiModelProperty("预算年度")
//    private String fiscal_year;


//    @ApiModelProperty("存储关联数据")
//    List<ElementDTO> children = new ArrayList<ElementDTO>();

    public  String getId() {
        return this.get("id")!=null?this.get("id").toString():"0";
    }

    public void setId(String id) {
        this.put("id",id);
    }

    public String getCode() {
        return this.get("code")!=null?this.get("code").toString():null;
    }

    public void setCode(String code) {
        this.put("code",code);
    }

    public String getName() {
        return this.get("name")!=null?this.get("name").toString():null;
    }

    public void setName(String name) {
        this.put("name",name);
    }

    public Integer getDisp_order() {
        return this.get("disp_order")!=null?Integer.valueOf(this.get("disp_order").toString()):0;
    }

    public void setDisp_order(Integer dispOrder) {
        this.put("disp_order",dispOrder);
    }

    public String getRemark() {
        return this.get("remark")!=null?this.get("remark").toString():null;
    }

    public void setRemark(String remark) {
        this.put("remark",remark);
    }

    public String getEle_code() {
        return this.get("ele_code")!=null?this.get("ele_code").toString():null;
    }

    public void setEle_code(String eleCode) {
        this.put("ele_code",eleCode);
    }

    public Long getTenant_id() {
        return this.get("tenant_id")!=null?Long.valueOf(this.get("tenant_id").toString()):0;
    }

    public void setTenant_id(Long tenant_id) {
        this.put("tenant_id",tenant_id);
    }

    public long getAcct_set_id() {
        return this.get("acct_set_id")!=null?Long.valueOf(this.get("acct_set_id").toString()):0;
    }

    public void setAcct_set_id(long acct_set_id) {
        this.put("acct_set_id",acct_set_id);
    }

    public String getFiscal_year() {
        return this.get("fiscal_year")!=null?this.get("fiscal_year").toString():null;
    }

    public void setFiscal_year(String fiscal_year) {
        this.put("fiscal_year",fiscal_year);
    }


    /**
     * 重写map.get()
     * 处理其他日期格式
     * @param key
     * @return
     */
    @Override
    public Object get(Object key){
        Object obj = super.get(key);
        if (obj != null && (key instanceof String) && dateExist((String) key)){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
                Date startDate = null;
                if (obj instanceof Date){
                    startDate = (Date) obj;
                }else if(obj instanceof  Long){
                    startDate = new Date();
                    startDate.setTime(Long.valueOf(obj.toString()));
                }else{
                    String startDateStr = obj.toString();
                    startDate = sdf.parse(startDateStr);
                }
                return startDate;

            }catch (Exception e){
                e.printStackTrace();
                return obj;
            }
        }
        return obj;
    }
    public static boolean dateExist(String key){
        for (String element: DATA_STRING) {
            if (key.toLowerCase().endsWith(element)){
                return true;
            }
        }
        if (SPECIAL_DATA_STRING.contains(key.toLowerCase())){
            return true;
        }
        return false;
    }
    public Date getStart_date() {
        Object obj = this.get("start_date");
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            if(obj != null){
                Date startDate =null;
                if (obj instanceof Date){
                    startDate = (Date) obj;
                }else if(obj instanceof  Long){
                    startDate = new Date();
                    startDate.setTime(Long.valueOf(obj.toString()));
                }else{
                    String startDateStr = obj.toString();
                    startDate = sdf.parse(startDateStr);
                }
                return startDate;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public void setStart_date(Date start_date) {
        this.put("start_date",start_date);
    }

    public Date getEnd_date() {
        Object obj = this.get("end_date");
        String a = "sa ";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            if(obj != null){
                Date endDate =null;
                if (obj instanceof Date){
                    endDate = (Date) obj;
                }else if(obj instanceof  Long){
                    endDate = new Date();
                    endDate.setTime(Long.valueOf(obj.toString()));
                }else{
                    String endDateStr = obj.toString();
                    endDate = sdf.parse(endDateStr);
                }
                return endDate;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

   /* public List<ElementDTO> getChildren() {
        return children;
    }

    public void setChildren(List<ElementDTO> children) {
        this.children = children;
    }*/
}
