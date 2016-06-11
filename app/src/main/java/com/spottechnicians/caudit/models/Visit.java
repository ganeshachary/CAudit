package com.spottechnicians.caudit.models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by onestech on 04/06/16.
 */
public class Visit implements Parcelable {
    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel in) {
            return new Visit(in);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };
    Bitmap bitmap[] = new Bitmap[3];
    Bitmap signature;
    String[] hk, ct, srm;
    private String viewPhotos,visitId,atmId,userId,location,bankName,customerName,
                    date,time,caretakeName,caretakerNumber,housekeeperName,
                    housekeeperNumber,srmName,SrmNumber,latitude,longitude;

    protected Visit(Parcel in) {
        viewPhotos = in.readString();
        visitId = in.readString();
        atmId = in.readString();
        userId = in.readString();
        location = in.readString();
        bankName = in.readString();
        customerName = in.readString();
        date = in.readString();
        time = in.readString();
        caretakeName = in.readString();
        caretakerNumber = in.readString();
        housekeeperName = in.readString();
        housekeeperNumber = in.readString();
        srmName = in.readString();
        SrmNumber = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        bitmap = in.createTypedArray(Bitmap.CREATOR);
        signature = in.readParcelable(Bitmap.class.getClassLoader());
        hk = in.createStringArray();
        ct = in.createStringArray();
        srm = in.createStringArray();
    }

    public Visit() {
    }

    public Bitmap getSignature() {
        return signature;
    }

    public void setSignature(Bitmap signature) {
        this.signature = signature;
    }

    public Bitmap[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap[] bitmap) {
        this.bitmap = bitmap;
    }



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

    public String getViewPhotos() {
        return viewPhotos;
    }

    public void setViewPhotos(String viewPhotos) {
        this.viewPhotos = viewPhotos;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCaretakeName() {
        return caretakeName;
    }

    public void setCaretakeName(String caretakeName) {
        this.caretakeName = caretakeName;
    }

    public String getCaretakerNumber() {
        return caretakerNumber;
    }

    public void setCaretakerNumber(String caretakerNumber) {
        this.caretakerNumber = caretakerNumber;
    }

    public String getHousekeeperName() {
        return housekeeperName;
    }

    public void setHousekeeperName(String housekeeperName) {
        this.housekeeperName = housekeeperName;
    }

    public String getHousekeeperNumber() {
        return housekeeperNumber;
    }

    public void setHousekeeperNumber(String housekeeperNumber) {
        this.housekeeperNumber = housekeeperNumber;
    }

    public String getSrmName() {
        return srmName;
    }

    public void setSrmName(String srmName) {
        this.srmName = srmName;
    }

    public String getSrmNumber() {
        return SrmNumber;
    }

    public void setSrmNumber(String srmNumber) {
        SrmNumber = srmNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(viewPhotos);
        dest.writeString(visitId);
        dest.writeString(atmId);
        dest.writeString(userId);
        dest.writeString(location);
        dest.writeString(bankName);
        dest.writeString(customerName);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(caretakeName);
        dest.writeString(caretakerNumber);
        dest.writeString(housekeeperName);
        dest.writeString(housekeeperNumber);
        dest.writeString(srmName);
        dest.writeString(SrmNumber);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeTypedArray(bitmap, flags);
        dest.writeParcelable(signature, flags);
        dest.writeStringArray(hk);
        dest.writeStringArray(ct);
        dest.writeStringArray(srm);
    }
}
