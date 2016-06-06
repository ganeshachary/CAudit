package com.spottechnicians.caudit.models;

/**
 * Created by onestech on 04/06/16.
 */
public class Visit {
    private String viewPhotos,visitId,atmId,userId,location,bankName,customerName,
                    date,time,caretakeName,caretakerNumber,housekeeperName,
                    housekeeperNumber,srmName,SrmNumber,latitude,longitude;
    String[]hk,ct,srm;
    public boolean checkCTComplete()
    {
        boolean status=true;
        for(int i=0;i<ct.length;i++)
        {
            if(ct[i]=="") {
                status = false;
            }
        }
        return status;
    }

    public String[] getHk() {
        return hk;
    }

    public void setHk(String[] hk) {
        this.hk = hk;
    }

    public String[] getCt() {
        return ct;
    }

    public void setCt(String[] ct) {
        this.ct = ct;
    }

    public String[] getSrm() {
        return srm;
    }

    public void setSrm(String[] srm) {
        this.srm = srm;
    }


}
