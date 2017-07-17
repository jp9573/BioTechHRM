package com.example.jay.setpayscale;

/**
 * Created by Aseem on 02-Apr-17.
 */

public class DataForSearchEmp {
    String empe_name, inst_id, specialisation, dob, ret_year;

    public DataForSearchEmp(String empe_name, String inst_id, String specialisation, String dob, String ret_year) {
        setEmpe_name(empe_name);
        setInst_id(inst_id);
        setSpecialisation(specialisation);
        setDob(dob);
        setRet_year(ret_year);
    }

    public void setEmpe_name(String empe_name) {
        this.empe_name = empe_name;
    }

    public void setInst_id(String inst_id) {
        this.inst_id = inst_id;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setRet_year(String ret_year) {
        this.ret_year = ret_year;
    }

    public String getEmpe_name() {
        return empe_name;
    }

    public String getInst_id() {
        return inst_id;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public String getDob() {
        return dob;
    }

    public String getRet_year() {
        return ret_year;
    }
}
