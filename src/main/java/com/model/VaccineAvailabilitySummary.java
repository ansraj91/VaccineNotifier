package com.model;

import com.rest.response.Center;
import com.rest.response.Session;

public class VaccineAvailabilitySummary {

    private Center center;
    private Session session;

    public VaccineAvailabilitySummary(Center center,Session session) {
        this.center = center;
        this.session = session;
    }

    @Override
    public String toString() {
      return
                "date:"+session.getDate()+"\n"+
                "min_age_limit:"+session.getMinAgeLimit()+"\n"+
                "totalVaccines:"+session.getAvailableCapacity()+"\n"+
                "vaccine_type:"+ session.getVaccine()+"\n"+
                "name:"+ center.getName()+"\n"+
                "address:"+center.getAddress()+"\n"+
                "state_name:"+center.getStateName()+"\n"+
                "district_name:"+center.getDistrictName()+"\n"+
                "block_name:"+center.getBlockName()+"\n"+
                "pincode:"+center.getPincode()+"\n";

    }



    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }





}
