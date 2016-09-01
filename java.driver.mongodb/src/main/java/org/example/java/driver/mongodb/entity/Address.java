package org.example.java.driver.mongodb.entity;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

/**
 * Address embedded document in Customer.
 *
 * @author adam.bialas
 *
 */
public class Address implements IBaseEntity {

    private String street;
    private Integer buildingNumber;
    private String zipCode;
    private String city;

    public Address() {
    }

    public Address(final String street, final Integer buildingNumber, final String zipCode, final String city) {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the buildingNumber
     */
    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * @param buildingNumber the buildingNumber to set
     */
    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("Address[street=%s, buildingNumber='%d', zipCode='%s', city='%s']", street, buildingNumber,
                zipCode, city);
    }

    public Document toDocument() {
        final Document document = new Document();
        if (StringUtils.isNotEmpty(street)) {
            document.append("street", street);
        }
        if (buildingNumber != null) {
            document.append("buildingNumber", buildingNumber);
        }
        if (StringUtils.isNotEmpty(zipCode)) {
            document.append("zipCode", zipCode);
        }
        if (StringUtils.isNotEmpty(city)) {
            document.append("city", city);
        }
        return document;
    }
}