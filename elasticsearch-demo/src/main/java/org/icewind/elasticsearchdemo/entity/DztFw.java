package org.icewind.elasticsearchdemo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Ye Jianyu
 * @date 2018/9/30
 */
@Entity
@Table(name = "DZT_FW")
@Document(indexName = "dz2018", type = "fw")
public class DztFw implements Serializable {

    @Id
    @org.springframework.data.annotation.Id
    private String dzbm;
    private String dzmc;
    private String dzpy;
    private String sssqjcwhdm;
    private String zaglssjwzrqdm;
    private String sjgsdwdm;
    private String fwdzdm;
    private String ssjlxxqdm;
    private String gxsj;

    public String getDzbm() {
        return dzbm;
    }

    public void setDzbm(String dzbm) {
        this.dzbm = dzbm;
    }

    public String getDzmc() {
        return dzmc;
    }

    public void setDzmc(String dzmc) {
        this.dzmc = dzmc;
    }

    public String getDzpy() {
        return dzpy;
    }

    public void setDzpy(String dzpy) {
        this.dzpy = dzpy;
    }

    public String getSssqjcwhdm() {
        return sssqjcwhdm;
    }

    public void setSssqjcwhdm(String sssqjcwhdm) {
        this.sssqjcwhdm = sssqjcwhdm;
    }

    public String getZaglssjwzrqdm() {
        return zaglssjwzrqdm;
    }

    public void setZaglssjwzrqdm(String zaglssjwzrqdm) {
        this.zaglssjwzrqdm = zaglssjwzrqdm;
    }

    public String getSjgsdwdm() {
        return sjgsdwdm;
    }

    public void setSjgsdwdm(String sjgsdwdm) {
        this.sjgsdwdm = sjgsdwdm;
    }

    public String getFwdzdm() {
        return fwdzdm;
    }

    public void setFwdzdm(String fwdzdm) {
        this.fwdzdm = fwdzdm;
    }

    public String getSsjlxxqdm() {
        return ssjlxxqdm;
    }

    public void setSsjlxxqdm(String ssjlxxqdm) {
        this.ssjlxxqdm = ssjlxxqdm;
    }

    public String getGxsj() {
        return gxsj;
    }

    public void setGxsj(String gxsj) {
        this.gxsj = gxsj;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dzbm", dzbm)
                .append("dzmc", dzmc)
                .append("dzpy", dzpy)
                .append("sssqjcwhdm", sssqjcwhdm)
                .append("zaglssjwzrqdm", zaglssjwzrqdm)
                .append("sjgsdwdm", sjgsdwdm)
                .append("fwdzdm", fwdzdm)
                .append("ssjlxxqdm", ssjlxxqdm)
                .append("gxsj", gxsj)
                .toString();
    }
}
