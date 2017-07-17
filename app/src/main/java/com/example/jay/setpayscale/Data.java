package com.example.jay.setpayscale;

public class Data {
    String empe_id,empe_name,empe_gender,inst_id,perf,ret_year;

    public Data(String empe_id, String empe_name, String empe_gender, String inst_id, String perf, String ret_year) {
        setEmpe_id(empe_id);
        setEmpe_name(empe_name);
        setEmpe_gender(empe_gender);
        setInst_id(inst_id);
        setPerf(perf);
        setRet_year(ret_year);
    }


    public void setEmpe_id(String empe_id) {
        this.empe_id = empe_id;
    }

    public void setEmpe_name(String empe_name) {
        this.empe_name = empe_name;
    }

    public void setEmpe_gender(String empe_gender) {
        this.empe_gender = empe_gender;
    }

    public void setInst_id(String inst_id) {
        this.inst_id = inst_id;
    }

    public void setPerf(String perf) {
        this.perf = perf;
    }

    public void setRet_year(String ret_year) {
        this.ret_year = ret_year;
    }

    public String getEmpe_id() {
        return empe_id;
    }

    public String getEmpe_name() {
        return empe_name;
    }

    public String getEmpe_gender() {
        return empe_gender;
    }

    public String getInst_id() {
        return inst_id;
    }

    public String getPerf() {
        return perf;
    }

    public String getRet_year() {
        return ret_year;
    }
}
